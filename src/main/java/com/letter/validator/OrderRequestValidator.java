package com.letter.validator;

import com.letter.helpers.ScanContentType;
import com.letter.model.OrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Set;


public class OrderRequestValidator implements Validator {

    private static final Logger log = LoggerFactory.getLogger(OrderRequestValidator.class);

    private static final String SCAN_FILE = "scanFile";
    private static final String DATE_STR = "dateStr";

    private Locale locale;


    public OrderRequestValidator(Locale locale){
        this.locale = locale;
    }

    public boolean supports(Class clazz) {
        return OrderRequest.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        OrderRequest order = (OrderRequest)obj;

        if (order.getScanFile() == null){
            e.rejectValue(SCAN_FILE, "scanFile.error.fileNotFound");
        } else {
            String contentType = order.getScanFile().get(0).getContentType();
            if (!(contentType.equalsIgnoreCase(ScanContentType.JPG.getValue()) || contentType.equalsIgnoreCase(ScanContentType.PDF.getValue()))) {
                e.rejectValue(SCAN_FILE, "scanFile.error.extension");
            } else {
                order.setScanType(contentType);
                try {
                    order.setScan(order.getScanFile().get(0).getBytes());
                } catch (Exception ex) {
                    log.error("OrderRequestValidator.validate.setScan", ex);
                }
            }
        }

        if (order.getDateStr().isEmpty()){
            e.rejectValue(DATE_STR, "dateStr.error.empty");
        } else {
            try {
                order.setDate(DateFormat.getDateInstance(DateFormat.MEDIUM, locale).parse(order.getDateStr()));
            } catch(ParseException pe) {
                e.rejectValue(DATE_STR, "dateStr.error.format");
            }
        }

        /* Other properties */
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<OrderRequest>> constraintViolations = validator.validate(order);
        for(ConstraintViolation<OrderRequest> constraintViolation : constraintViolations){
            e.rejectValue(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
    }
}
