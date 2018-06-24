package com.dvn.home.finance.expensemanager.controller;

import com.dvn.home.finance.expensemanager.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @RequestMapping("/category/startsWith/{name}")
    public Set<String> getCategory(@PathVariable("name") String token){
        logger.info("Request from client to fetch categories starting with " + token);
        return categoryService.getCategory(token);
    }
}
