package com.letter.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Locale;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class JsonResponse {


    public JsonResponse() {

    }

    public JsonResponse(String content) {
        this.content = content;
    }

    public JsonResponse(BindingResult bindingResult, Locale locale, MessageSource messageSource) {
        for (Object object : bindingResult.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                String message = messageSource.getMessage(fieldError, locale);
                addError(fieldError.getField(), message);
            }
        }
    }

    private HashMap<String, String> errors = new HashMap<String, String>();

    private String content;

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public void addError(String field, String error) {
        this.errors.put(field, error);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
