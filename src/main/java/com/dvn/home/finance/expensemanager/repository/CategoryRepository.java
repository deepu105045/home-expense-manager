package com.dvn.home.finance.expensemanager.repository;

import com.dvn.home.finance.expensemanager.entity.Category;
import com.dvn.home.finance.expensemanager.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {
    List<Category> findAllDistinctByCategoryIgnoreCaseStartingWith(String category);
    List<Category> findByCategoryIgnoreCase(String token);
}
