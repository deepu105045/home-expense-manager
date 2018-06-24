package com.dvn.home.finance.expensemanager.repository;

import com.dvn.home.finance.expensemanager.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense,String>, ExpenseRepositoryCustom{
}
