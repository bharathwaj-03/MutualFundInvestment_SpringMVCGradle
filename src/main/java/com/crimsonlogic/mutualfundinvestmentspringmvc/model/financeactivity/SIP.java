package com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;

import java.time.LocalDate;
import java.util.Objects;

public class SIP {

    private String sipId;

    // Associations
    private Investor investor;
    private MutualFund mutualFund;

    private double monthlyAmount;
    private double unitsPurchased;

    private LocalDate activityDate;
    private LocalDate startDate;
    private LocalDate nextInstallmentDate;

    private int investmentYears;

    private double assetGainPerYear;
    private double assetGainTotalInvestedYears;

    private String sipStatus;


    public SIP() {
    }


    public SIP(String sipId,
               Investor investor,
               MutualFund mutualFund,
               double monthlyAmount,
               double unitsPurchased,
               LocalDate activityDate,
               LocalDate startDate,
               LocalDate nextInstallmentDate,
               int investmentYears,
               double assetGainPerYear,
               double assetGainTotalInvestedYears,
               String sipStatus) {

        this.sipId = sipId;
        this.investor = investor;
        this.mutualFund = mutualFund;
        this.monthlyAmount = monthlyAmount;
        this.unitsPurchased = unitsPurchased;
        this.activityDate = activityDate;
        this.startDate = startDate;
        this.nextInstallmentDate = nextInstallmentDate;
        this.investmentYears = investmentYears;
        this.assetGainPerYear = assetGainPerYear;
        this.assetGainTotalInvestedYears =
                assetGainTotalInvestedYears;
        this.sipStatus = sipStatus;
    }


    public String getSipId() {
        return sipId;
    }

    public void setSipId(String sipId) {
        this.sipId = sipId;
    }


    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }


    public MutualFund getMutualFund() {
        return mutualFund;
    }

    public void setMutualFund(MutualFund mutualFund) {
        this.mutualFund = mutualFund;
    }


    public double getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(double monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }


    public double getUnitsPurchased() {
        return unitsPurchased;
    }

    public void setUnitsPurchased(double unitsPurchased) {
        this.unitsPurchased = unitsPurchased;
    }


    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public LocalDate getNextInstallmentDate() {
        return nextInstallmentDate;
    }

    public void setNextInstallmentDate(
            LocalDate nextInstallmentDate) {

        this.nextInstallmentDate =
                nextInstallmentDate;
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

    public void setAssetGainPerYear(
            double assetGainPerYear) {

        this.assetGainPerYear =
                assetGainPerYear;
    }


    public double getAssetGainTotalInvestedYears() {
        return assetGainTotalInvestedYears;
    }

    public void setAssetGainTotalInvestedYears(
            double assetGainTotalInvestedYears) {

        this.assetGainTotalInvestedYears =
                assetGainTotalInvestedYears;
    }


    public String getSipStatus() {
        return sipStatus;
    }

    public void setSipStatus(String sipStatus) {
        this.sipStatus = sipStatus;
    }


    @Override
    public String toString() {

        return "SIP{" +
                "sipId='" + sipId + '\'' +
                ", investor=" +
                (investor != null
                        ? investor.getUserId()
                        : null) +
                ", mutualFund=" +
                (mutualFund != null
                        ? mutualFund.getFundId()
                        : null) +
                ", monthlyAmount=" +
                monthlyAmount +
                ", unitsPurchased=" +
                unitsPurchased +
                ", activityDate=" +
                activityDate +
                ", startDate=" +
                startDate +
                ", nextInstallmentDate=" +
                nextInstallmentDate +
                ", investmentYears=" +
                investmentYears +
                ", assetGainPerYear=" +
                assetGainPerYear +
                ", assetGainTotalInvestedYears=" +
                assetGainTotalInvestedYears +
                ", sipStatus='" +
                sipStatus + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null ||
                getClass() != o.getClass())
            return false;

        SIP sip = (SIP) o;

        return Objects.equals(
                sipId,
                sip.sipId);
    }


    @Override
    public int hashCode() {

        return Objects.hash(sipId);
    }
}