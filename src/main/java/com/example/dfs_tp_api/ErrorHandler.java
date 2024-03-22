package com.example.dfs_tp_api;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {

        Map<String, Object> listeErreur = new HashMap<>();

        for (ObjectError erreur : ex.getBindingResult().getAllErrors()) {
            String nomChamps = ((FieldError) erreur).getField();
            String messageErreur = erreur.getDefaultMessage();
            listeErreur.put(nomChamps, messageErreur);
        }

        return listeErreur;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Map<String , Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return Map.of("erreur" , "Une contrainte d'intégrité n'a pas été respectée");
    }

}
