package com.dvn.home.finance.expensemanager.repository;

import com.dvn.home.finance.expensemanager.business.domain.ExpenseSummary;
import com.dvn.home.finance.expensemanager.business.domain.Total;

import java.util.List;

public interface ExpenseRepositoryCustom {
    List<ExpenseSummary> groupByCategory(String year, String month,String type);
    List<Total> groupByType(String year, String month, String type);

}
