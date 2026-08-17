package com.crimsonlogic.mutualfundinvestmentspringmvc.model.user;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.User;

import java.time.LocalDate;
import java.util.Objects;

public class Investor extends User implements Comparable<Investor> {

    private Nominee nominee;

    private String panNumber;

    private String accountNumber;

    private LocalDate registrationDate;

    private String riskProfile;

    private boolean active;


    public Investor() {
    }


    public Investor(
            String panNumber,
            LocalDate registrationDate,
            String riskProfile,
            boolean active,
            String investorName) {

        this.panNumber = panNumber;
        this.registrationDate = registrationDate;
        this.riskProfile = riskProfile;
        this.active = active;
    }


    public Investor(String panNumber) {

        this.panNumber = panNumber;
    }


    @Override
    public int compareTo(Investor other) {

        return this.getUserId()
                .compareTo(other.getUserId());
    }


    // =========================================================
    // PAN
    // =========================================================

    public String getPanNumber() {

        return panNumber;
    }

    public void setPanNumber(String panNumber) {

        this.panNumber = panNumber;
    }


    // =========================================================
    // ACCOUNT NUMBER
    // =========================================================

    public String getAccountNumber() {

        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {

        this.accountNumber = accountNumber;
    }


    // =========================================================
    // REGISTRATION DATE
    // =========================================================

    public LocalDate getRegistrationDate() {

        return registrationDate;
    }

    public void setRegistrationDate(
            LocalDate registrationDate) {

        this.registrationDate = registrationDate;
    }


    // =========================================================
    // RISK PROFILE
    // =========================================================

    public String getRiskProfile() {

        return riskProfile;
    }

    public void setRiskProfile(String riskProfile) {

        this.riskProfile = riskProfile;
    }


    // =========================================================
    // ACTIVE
    // =========================================================

    public boolean isActive() {

        return active;
    }

    public void setActive(boolean active) {

        this.active = active;
    }


    // =========================================================
    // NOMINEE
    // =========================================================

    public Nominee getNominee() {

        return nominee;
    }

    public void setNominee(Nominee nominee) {

        this.nominee = nominee;
    }


    // =========================================================
    // TO STRING
    // =========================================================

    @Override
    public String toString() {

        return "Investor{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" +
                getPhoneNumber() + '\'' +
                ", panNumber='" +
                panNumber + '\'' +
                ", accountNumber='" +
                accountNumber + '\'' +
                ", registrationDate=" +
                registrationDate +
                ", riskProfile='" +
                riskProfile + '\'' +
                ", active=" + active +
                '}';
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null ||
                getClass() != o.getClass())
            return false;

        Investor investor =
                (Investor) o;

        return active == investor.active
                && Objects.equals(
                panNumber,
                investor.panNumber)
                && Objects.equals(
                registrationDate,
                investor.registrationDate)
                && Objects.equals(
                riskProfile,
                investor.riskProfile);
    }


    @Override
    public int hashCode() {

        return Objects.hash(
                panNumber,
                registrationDate,
                riskProfile,
                active);
    }
}