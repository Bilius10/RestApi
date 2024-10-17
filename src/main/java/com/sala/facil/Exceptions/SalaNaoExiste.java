package com.sala.facil.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SalaNaoExiste extends Throwable {
    public SalaNaoExiste(String salaNãoExiste) {
        super(salaNãoExiste);
    }
}
