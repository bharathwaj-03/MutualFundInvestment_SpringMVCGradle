package com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;

import java.util.Objects;

public class Holding {

    private String holdingId;
    private MutualFund mutualFund;
    private double unitsOwned;
    private double investedAmount;
    private double averageNav;
    private Portfolio portfolio;
    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }






    public Holding() {
    }

    public Holding(String holdingId, MutualFund mutualFund,
                   double unitsOwned, double investedAmount,
                   double averageNav) {

        this.holdingId = holdingId;
        this.mutualFund = mutualFund;
        this.unitsOwned = unitsOwned;
        this.investedAmount = investedAmount;
        this.averageNav = averageNav;
    }

    public String getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(String holdingId) {
        this.holdingId = holdingId;
    }

    public MutualFund getMutualFund() {
        return mutualFund;
    }

    public void setMutualFund(MutualFund mutualFund) {
        this.mutualFund = mutualFund;
    }

    public double getUnitsOwned() {
        return unitsOwned;
    }

    public void setUnitsOwned(double unitsOwned) {
        this.unitsOwned = unitsOwned;
    }

    public double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(double investedAmount) {
        this.investedAmount = investedAmount;
    }

    public double getAverageNav() {
        return averageNav;
    }

    public void setAverageNav(double averageNav) {
        this.averageNav = averageNav;
    }

//    public String getPortfolioId() {
//        return portfolioId;
//    }
//
//    public void setPortfolioId(String portfolioId) {
//        this.portfolioId = portfolioId;
//    }
    @Override
    public String toString() {
        return "Holding{" +
                "holdingId=" + holdingId +
                ", mutualFund=" + mutualFund +
                ", unitsOwned=" + unitsOwned +
                ", investedAmount=" + investedAmount +
                ", averageNav=" + averageNav +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holding holding = (Holding) o;
        return holdingId == holding.holdingId && Double.compare(unitsOwned, holding.unitsOwned) == 0 && Double.compare(investedAmount, holding.investedAmount) == 0 && Double.compare(averageNav, holding.averageNav) == 0 && Objects.equals(mutualFund, holding.mutualFund);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holdingId, mutualFund, unitsOwned, investedAmount, averageNav);
    }
}