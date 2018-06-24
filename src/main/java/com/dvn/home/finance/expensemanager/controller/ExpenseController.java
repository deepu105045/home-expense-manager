package com.dvn.home.finance.expensemanager.controller;

import com.dvn.home.finance.expensemanager.business.domain.Total;
import com.dvn.home.finance.expensemanager.entity.Expense;
import com.dvn.home.finance.expensemanager.business.domain.ExpenseSummary;
import com.dvn.home.finance.expensemanager.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    @RequestMapping("/expense")
    public List<Expense> getExpenses(){
        return expenseService.getAllExpense();
    }

    @GetMapping
    @RequestMapping("/expense-summary")
    public List<ExpenseSummary> getSummaryForMonth(@RequestParam("year") String year,
                                                   @RequestParam("month") String month){
        return expenseService.getSummary(year,month);
    }

    @GetMapping
    @RequestMapping("/totals")
    public List<Total> getTypeTotalsForMonth(@RequestParam("year") String year,
                                             @RequestParam("month") String month,
                                             @RequestParam("type") String type){
        return expenseService.getTotals(year,month,type);
    }

    @PostMapping
    @Transactional
    @RequestMapping(method = RequestMethod.POST, value= "/expense")
    public Expense addTransaction(@RequestBody Expense expense){
        logger.info("Post request received from client is " + expense);
        return expenseService.addTransaction(expense);
    }

}
