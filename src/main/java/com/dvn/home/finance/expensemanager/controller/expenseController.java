package com.dvn.home.finance.expensemanager.controller;

import com.dvn.home.finance.expensemanager.entity.Expense;
import com.dvn.home.finance.expensemanager.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class expenseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    @RequestMapping("/expense")
    public List<Expense> getExpenses(){
        return expenseService.getAllExpense();
    }

    @PostMapping
    @Transactional
    @RequestMapping(method = RequestMethod.POST, value= "/expense")
    public Expense addTransaction(@RequestBody Expense expense){
         return expenseService.addTransaction(expense);
    }

}
