package com.dvn.home.finance.expensemanager.service;

import com.dvn.home.finance.expensemanager.entity.Category;
import com.dvn.home.finance.expensemanager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Set<String> getCategory(String token) {
        Set<String> categories = new HashSet<>();
        categoryRepository.findAllDistinctByCategoryIgnoreCaseStartingWith(token)
                .forEach(record -> categories.add(record.getCategory()));
        return categories;
    }

    public void addCategory(String category){
        List<Category> categoryPersisted = categoryRepository.findByCategoryIgnoreCase(category);
        if(categoryPersisted.size()<=0){
            Category category1= new Category();
            category1.setCategory(category);
            categoryRepository.save(category1);
        }
    }

}
