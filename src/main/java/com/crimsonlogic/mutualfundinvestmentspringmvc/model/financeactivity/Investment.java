package com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.FinancialActivity;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;

import java.util.Objects;

public class Investment extends FinancialActivity {



    private double assetGainPerYear;
    private double assetGainTotalInvestedYears;
    private double unitsPurchased;
    private int investmentYears;
    private String investmentId;
    private Investor investor;
    private MutualFund mutualFund;

    public MutualFund getMutualFund() {
        return mutualFund;
    }

    public void setMutualFund(MutualFund mutualFund) {
        this.mutualFund = mutualFund;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public String getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(String investmentId) {
        this.investmentId = investmentId;
    }

    public int getInvestmentYears() {
        return investmentYears;
    }

    public void setInvestmentYears(int investmentYears) {
        this.investmentYears = investmentYears;
    }

    public double getAssetGainPerYear() {
        return assetGainPerYear;
    }

    public void setAssetGainPerYear(double assetGainPerYear) {
        this.assetGainPerYear = assetGainPerYear;
    }

    public double getAssetGainTotalInvestedYears() {
        return assetGainTotalInvestedYears;
    }

    public void setAssetGainTotalInvestedYears(double assetGainTotalInvestedYears) {
        this.assetGainTotalInvestedYears = assetGainTotalInvestedYears;
    }

    public Investment() {
    }




    public double getUnitsPurchased() {
        return unitsPurchased;
    }

    public void setUnitsPurchased(double unitsPurchased) {
        this.unitsPurchased = unitsPurchased;
    }



//    public Investment(long activityId, double amount, LocalDate activityDate, long investorId, long fundId, double unitsPurchased) {
//        super(activityId, amount, activityDate);
//        this.investorId = investorId;
//        this.fundId = fundId;
//        this.unitsPurchased = unitsPurchased;
//    }


    @Override
    public String toString() {
        return "Investment{" +
                "assetGainPerYear=" + assetGainPerYear +
                ", assetGainTotalInvestedYears=" + assetGainTotalInvestedYears +
                ", unitsPurchased=" + unitsPurchased +
                ", investmentYears=" + investmentYears +
                ", investmentId='" + investmentId + '\'' +
                ", investor=" + investor +
                ", mutualFund=" + mutualFund +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Investment that = (Investment) o;
        return Double.compare(assetGainPerYear, that.assetGainPerYear) == 0 && Double.compare(assetGainTotalInvestedYears, that.assetGainTotalInvestedYears) == 0 && Double.compare(unitsPurchased, that.unitsPurchased) == 0 && investmentYears == that.investmentYears && Objects.equals(investmentId, that.investmentId) && Objects.equals(investor, that.investor) && Objects.equals(mutualFund, that.mutualFund);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetGainPerYear, assetGainTotalInvestedYears, unitsPurchased, investmentYears, investmentId, investor, mutualFund);
    }
}