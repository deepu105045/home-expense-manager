package com.dvn.home.finance.expensemanager.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(String expenseId){
        super("Could not find expense id '" + expenseId + "'.");
    }
}
