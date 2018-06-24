package com.dvn.home.finance.expensemanager.business.domain;

import java.util.List;

public class ExpenseSummary {
    private String category;
    private List<String> ids;
    private float averageAmount;
    private float totalAmount;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public float getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(float averageAmount) {
        this.averageAmount = averageAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "ExpenseSummary{" +
                "category='" + category + '\'' +
                ", ids=" + ids +
                ", averageAmount=" + averageAmount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
