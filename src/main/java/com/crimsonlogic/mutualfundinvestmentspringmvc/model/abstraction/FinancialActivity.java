package com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction;

import java.time.LocalDate;

public abstract class FinancialActivity {

    private long activityId;
    private double amount;
    private LocalDate activityDate;

    public FinancialActivity() {
    }

    public FinancialActivity(long activityId,
                             double amount,
                             LocalDate activityDate) {
        this.activityId = activityId;
        this.amount = amount;
        this.activityDate = activityDate;
    }



    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }
}