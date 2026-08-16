package com.crimsonlogic.mutualfundinvestmentspringmvc.model.nav;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;

import java.time.LocalDate;
import java.util.Objects;

public class NAVHistory {

    private String historyId;

    private MutualFund mutualFund;

    private double oldNav;

    private double newNav;

    public NAVHistory(){

    }



    public NAVHistory(String historyId, MutualFund mutualFund, double oldNav, double newNav, LocalDate changeDate, String changedBy) {
        this.historyId = historyId;
        this.mutualFund = mutualFund;
        this.oldNav = oldNav;
        this.newNav = newNav;
        this.changeDate = changeDate;
        this.changedBy = changedBy;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public MutualFund getMutualFund() {
        return mutualFund;
    }

    public void setMutualFund(MutualFund mutualFund) {
        this.mutualFund = mutualFund;
    }

    public double getOldNav() {
        return oldNav;
    }

    public void setOldNav(double oldNav) {
        this.oldNav = oldNav;
    }

    public double getNewNav() {
        return newNav;
    }

    public void setNewNav(double newNav) {
        this.newNav = newNav;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    private LocalDate changeDate;

    private String changedBy;

    @Override
    public String toString() {
        return "NAVHistory{" +
                "historyId='" + historyId + '\'' +
                ", mutualFund=" + mutualFund +
                ", oldNav=" + oldNav +
                ", newNav=" + newNav +
                ", changeDate=" + changeDate +
                ", changedBy='" + changedBy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NAVHistory that = (NAVHistory) o;
        return Double.compare(oldNav, that.oldNav) == 0 && Double.compare(newNav, that.newNav) == 0 && Objects.equals(historyId, that.historyId) && Objects.equals(mutualFund, that.mutualFund) && Objects.equals(changeDate, that.changeDate) && Objects.equals(changedBy, that.changedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyId, mutualFund, oldNav, newNav, changeDate, changedBy);
    }
}
