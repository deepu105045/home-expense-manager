package com.dvn.home.finance.expensemanager.service;

import com.dvn.home.finance.expensemanager.business.domain.Total;
import com.dvn.home.finance.expensemanager.entity.Expense;
import com.dvn.home.finance.expensemanager.business.domain.ExpenseSummary;
import com.dvn.home.finance.expensemanager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CategoryService categoryService;

    public List<Expense> getAllExpense(){
        List<Expense> expenseList = new ArrayList<>();
        expenseRepository.findAll().forEach(expense -> expenseList.add(expense));
        return expenseList;
    }
    public List<ExpenseSummary> getSummary(String year, String month) {
        return expenseRepository.groupByCategory(year,month);
    }

    public List<Total> getTotals(String year, String month, String type){
        return  expenseRepository.groupByType(year,month,type);
    }

    public Expense addTransaction(Expense expense) {
        String category=expense.getCategory();
        categoryService.addCategory(category);
        return expenseRepository.save(expense);
    }

}
