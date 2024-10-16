package com.sala.facil.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataAtingiuPrazo extends Throwable {
    public DataAtingiuPrazo(String s) {
        super(s);
    }
}
