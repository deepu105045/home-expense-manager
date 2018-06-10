package com.dvn.home.finance.expensemanager.service;

import com.dvn.home.finance.expensemanager.entity.Expense;
import com.dvn.home.finance.expensemanager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpense(){
        List<Expense> expenseList = new ArrayList<>();
        expenseRepository.findAll().forEach(expense -> expenseList.add(expense));
        return expenseList;
    }

    public Expense addTransaction(Expense expense) {
        return expenseRepository.save(expense);
    }
}
