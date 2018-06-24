package com.dvn.home.finance.expensemanager.repository;

import com.dvn.home.finance.expensemanager.business.domain.Total;
import com.dvn.home.finance.expensemanager.entity.Expense;
import com.dvn.home.finance.expensemanager.business.domain.ExpenseSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public class ExpenseRepositoryImpl implements ExpenseRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ExpenseSummary> groupByCategory(String year, String month) {
        MatchOperation matchOperation = getMatchingRecordsForMonth(year,month);
        GroupOperation groupByCategory = groupByCategory();
        ProjectionOperation projectionOperation = getCategotyProjection();

        return mongoTemplate.aggregate(
                Aggregation.newAggregation(
                        matchOperation,
                        groupByCategory,
                        projectionOperation,
                        sort(Sort.Direction.DESC,"totalAmount")
                ), Expense.class, ExpenseSummary.class).getMappedResults();
    }

    @Override
    public List<Total> groupByType(String year, String month, String type) {
        return mongoTemplate.aggregate(
                Aggregation.newAggregation(
                        getRecordByType(year,month,type),
                        groupByType(),
                        getTypeProjection(),
                        sort(Sort.Direction.DESC , "totalAmount")
                ),Expense.class,Total.class).getMappedResults();
    }

    private MatchOperation getMatchingRecordsForMonth(String year, String month) {
        Criteria amountCriteria = where("amount").gt(0).andOperator(where("date").gte(month+"/1/"+year).lte(month+"/31/"+year));
        return match(amountCriteria);
    }

    private MatchOperation getRecordByType(String year, String month, String type) {
        Criteria typeCriteria = where("type").is(type).andOperator(where("date").gte(month+"/1/"+year).lte(month+"/31/"+year));
        return match(typeCriteria);
    }

    private GroupOperation groupByCategory() {
        return group("category")
                .last("category").as("category")
                .first("type").as("type")
                .addToSet("id").as("ids")
                .avg("amount").as("averageAmount")
                .sum("amount").as("totalAmount");
    }

    private GroupOperation groupByType() {
        return group("type")
                .addToSet("id").as("ids")
                .sum("amount").as("totalAmount");
    }

    private ProjectionOperation getCategotyProjection() {
        return project("ids", "averageAmount", "totalAmount","type")
                  .and("category")
                  .previousOperation();
    }

    private ProjectionOperation getTypeProjection() {
        return project("ids", "totalAmount")
                .and("type")
                .previousOperation();
    }
}
