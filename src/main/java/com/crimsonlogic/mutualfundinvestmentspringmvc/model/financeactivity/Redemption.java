package com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.FinancialActivity;

import java.util.Objects;

public class Redemption extends FinancialActivity {


    private String investorId;
    private String fundId;
    private String redemptionId;

    private double unitsRedeemed;


    public Redemption() {
    }

    public String getRedemptionId() {
        return redemptionId;
    }

    public void setRedemptionId(String redemptionId) {
        this.redemptionId = redemptionId;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public double getUnitsRedeemed() {
        return unitsRedeemed;
    }

    public void setUnitsRedeemed(double unitsRedeemed) {
        this.unitsRedeemed = unitsRedeemed;
    }

    public Redemption(String investorId, String fundId, double unitsRedeemed) {
        this.investorId = investorId;
        this.fundId = fundId;
        this.unitsRedeemed = unitsRedeemed;
    }

//    public Redemption(long activityId, double amount, LocalDate activityDate, long investorId, long fundId, double unitsRedeemed) {
//        super(activityId, amount, activityDate);
//        this.investorId = investorId;
//        this.fundId = fundId;
//        this.unitsRedeemed = unitsRedeemed;
//    }

    @Override
    public String toString() {
        return "Redemption{" +
                "investorId=" + investorId +
                ", fundId=" + fundId +
                ", unitsRedeemed=" + unitsRedeemed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Redemption that = (Redemption) o;
        return investorId == that.investorId && fundId == that.fundId && Double.compare(unitsRedeemed, that.unitsRedeemed) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(investorId, fundId, unitsRedeemed);
    }
}