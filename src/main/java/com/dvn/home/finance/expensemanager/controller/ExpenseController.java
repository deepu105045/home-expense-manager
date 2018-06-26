package com.dvn.home.finance.expensemanager.controller;

import com.dvn.home.finance.expensemanager.Exceptions.ExpenseNotFoundException;
import com.dvn.home.finance.expensemanager.business.domain.Total;
import com.dvn.home.finance.expensemanager.entity.Expense;
import com.dvn.home.finance.expensemanager.business.domain.ExpenseSummary;
import com.dvn.home.finance.expensemanager.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    @RequestMapping("/expenses")
    public ResponseEntity<List<Expense>> getExpenses(){
        List<Expense> expenses = expenseService.getAllExpense();
        return Optional.ofNullable(expenses)
                .map(question -> ResponseEntity.ok().body(expenses))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @RequestMapping("/expense/{expenseId}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("expenseId")  String expenseId){
        Expense expense = expenseService.getExpenseDetailsById(expenseId);
        return Optional.ofNullable(expense)
                .map(question -> ResponseEntity.ok().body(expense))
                .orElseThrow(()-> new ExpenseNotFoundException(expenseId));
    }

    @GetMapping
    @RequestMapping("/expense-summary")
    public ResponseEntity<List<ExpenseSummary>> getSummaryForMonth(@RequestParam("year") String year,
                                                   @RequestParam("month") String month,
                                                   @RequestParam("type") String type){
        List<ExpenseSummary> summary = expenseService.getSummary(year, month, type);
        return Optional.ofNullable(summary)
                .map(question -> ResponseEntity.ok().body(summary))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @RequestMapping("/totals")
    public ResponseEntity<List<Total>> getTypeTotalsForMonth(@RequestParam("year") String year,
                                             @RequestParam("month") String month,
                                             @RequestParam("type") String type){
        List<Total> totals = expenseService.getTotals(year, month, type);
        return Optional.ofNullable(totals)
                .map(question -> ResponseEntity.ok().body(totals))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @Transactional
    @RequestMapping(method = RequestMethod.POST, value= "/expense")
    public ResponseEntity<Expense> addTransaction(@RequestBody Expense expense){
        logger.info("Post request received from client is " + expense);
        Expense expenseCreated = expenseService.addTransaction(expense);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(expenseCreated.getExpenseId()).toUri();

        return Optional.ofNullable(expenseCreated)
                .map(question -> ResponseEntity.created(location).body(expenseCreated))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
