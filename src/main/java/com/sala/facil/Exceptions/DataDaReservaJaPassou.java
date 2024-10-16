package com.sala.facil.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataDaReservaJaPassou extends Throwable {
    public DataDaReservaJaPassou(String s) {
        super(s);
    }
}
