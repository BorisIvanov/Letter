package com.letter.controllers;

import com.letter.domain.Order;
import com.letter.helpers.JspHelper;
import com.letter.model.JsonResponse;
import com.letter.model.OrderRequest;
import com.letter.service.OrderService;
import com.letter.validator.OrderRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;



@Controller
@RequestMapping(value = { "/", "/letter" })
public class LetterController {


    private static final Logger log = LoggerFactory.getLogger(LetterController.class);

    @Autowired
    private List<ViewResolver> viewResolver;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("date_pattern", ((SimpleDateFormat)DateFormat.getDateInstance(DateFormat.MEDIUM, RequestContextUtils.getLocaleResolver(request).resolveLocale(request))).toPattern());
        model.put("orderList", orderService.getAll());
        return new ModelAndView ( "letter/list", model );
    }

    private Locale getLocale(HttpServletRequest request) {
        return RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
    }

    @InitBinder
    private void initBinder(WebDataBinder binder, final Locale locale) {
        binder.setValidator(new OrderRequestValidator(locale));
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody JsonResponse upload(
            @Valid OrderRequest orderRequest,
            BindingResult bindingResult,
            MultipartHttpServletRequest request, HttpServletResponse response) {

        if(bindingResult.hasErrors()) {
            return new JsonResponse(
                    bindingResult,
                    getLocale(request),
                    messageSource);
        }

        orderService.add(orderRequest.getOrder());
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("orderList", orderService.getAll());
        return new JsonResponse(new JspHelper().toString(new ModelAndView("letter/_tablePartial", model), request, response, viewResolver.get(0)));
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResponse publish(@RequestParam("id") long id) {
        orderService.publish(id);
        return new JsonResponse();
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") long id, HttpServletResponse response) throws Exception {
        Order order = orderService.get(id);

        response.setContentType(order.getScanType());
        response.setContentLength(order.getOrderScan().getScan().length);

        response.setHeader("content-Disposition",
                String.format("attachment; filename=file_%s.%s", order.getId(), order.getExt())
        );

        try {
            response.getOutputStream().write(order.getOrderScan().getScan());
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("download", e);
            throw (e);
        }
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public
    @ResponseBody
    JsonResponse handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex, HttpServletRequest request) {
        JsonResponse response = new JsonResponse();
        response.addError("scanFile", messageSource.getMessage("scanFile.error.tooMach", new Object[]{}, getLocale(request)));
        return response;

    }

}
