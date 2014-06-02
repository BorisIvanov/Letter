package com.letter.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;


@Component
public class JspHelper {

    private static final Logger log = LoggerFactory.getLogger(JspHelper.class);

    public static String toString(ModelAndView model, HttpServletRequest request, HttpServletResponse response, ViewResolver viewResolver) {

        for (Map.Entry<String, Object> entry : model.getModel().entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        request.setAttribute("locale", RequestContextUtils.getLocaleResolver(request).resolveLocale(request).getLanguage());

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
            private final StringWriter stringWriter = new StringWriter();

            @Override
            public PrintWriter getWriter() throws IOException {
                return new PrintWriter(stringWriter);
            }

            @Override
            public String toString() {
                return stringWriter.toString();
            }
        };

         try {
            View view = viewResolver.resolveViewName(model.getViewName(), null);
            request.getRequestDispatcher(((JstlView) view).getUrl()).include(request, responseWrapper);
        } catch (Exception e) {
            log.error("JspHelper.toString", e);
        }
        return responseWrapper.toString();
    }
}
