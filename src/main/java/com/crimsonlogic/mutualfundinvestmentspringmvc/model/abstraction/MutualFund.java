package com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction;

public abstract class MutualFund implements Comparable<MutualFund>{

    private String fundId;
    private String fundName;
    private double nav;
    private String fundCategory;
    private  String fundHouse;
    private int minimumInvestment;

    private double sipGainPerYear;

    private double lumpSumGainPerYear;
    private String fundCode;

    public int getMinimumInvestment() {
        return minimumInvestment;
    }

    public double getSipGainPerYear() {
        return sipGainPerYear;
    }

    public double getLumpSumGainPerYear() {
        return lumpSumGainPerYear;
    }

    public void setMinimumInvestment(int minimumInvestment) {
        this.minimumInvestment = minimumInvestment;
    }

    public void setSipGainPerYear(double sipGainPerYear) {
        this.sipGainPerYear = sipGainPerYear;
    }

    public void setLumpSumGainPerYear(double lumpSumGainPerYear) {
        this.lumpSumGainPerYear = lumpSumGainPerYear;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundHouse() {
        return fundHouse;
    }

    public void setFundHouse(String fundHouse) {
        this.fundHouse = fundHouse;
    }

    private String riskLevel;



    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public MutualFund() {
    }

    public MutualFund(String fundId,
                      String fundName,
                      double nav,
                      String fundCategory) {
        this.fundId = fundId;
        this.fundName = fundName;
        this.nav = nav;
        this.fundCategory = fundCategory;
    }
    @Override
    public int compareTo(MutualFund other) {

        return this.getFundName()
                .compareToIgnoreCase(
                        other.getFundName());
    }



    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public double getNav() {
        return nav;
    }

    public void setNav(double nav) {
        this.nav = nav;
    }

    public String getFundCategory() {
        return fundCategory;
    }

    public void setFundCategory(String fundCategory) {
        this.fundCategory = fundCategory;
    }

   // public abstract int getMinimumInvestment();
//
//    public abstract double getSipGainPerYear();
//
//    public abstract double getLumpSumGainPerYear();


    @Override
    public String toString() {
        return "MutualFund{" +
                "fundId='" + fundId + '\'' +
                ", fundName='" + fundName + '\'' +
                ", nav=" + nav +
                ", fundCategory='" + fundCategory + '\'' +
                ", fundHouse='" + fundHouse + '\'' +
                ", minimumInvestment=" + minimumInvestment +
                ", sipGainPerYear=" + sipGainPerYear +
                ", lumpSumGainPerYear=" + lumpSumGainPerYear +
                ", fundCode='" + fundCode + '\'' +
                ", riskLevel='" + riskLevel + '\'' +
                '}';
    }
}