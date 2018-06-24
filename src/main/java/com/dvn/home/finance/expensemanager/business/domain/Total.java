package com.dvn.home.finance.expensemanager.business.domain;

public class Total {
    private String type;
    private String totalAmount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Total{" +
                "type='" + type + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}
