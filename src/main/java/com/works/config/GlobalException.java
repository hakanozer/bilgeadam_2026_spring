package com.works.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        return e.getFieldErrors()
                .stream()
                .map(err -> {
                    Map<String,String> map = new HashMap<>();
                    map.put("field", err.getField());
                    map.put("message", err.getDefaultMessage());
                    map.put("rejectedValue", err.getRejectedValue().toString());
                    return map;
                }).toList();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleRuntime(RuntimeException e) {
        Map<String, String> map = new HashMap<>();
        map.put("error", e.getMessage());
        return map;
    }

}
