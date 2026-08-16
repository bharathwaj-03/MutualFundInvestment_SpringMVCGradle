package com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;

import java.util.Objects;

public class BankPayment implements Payable {

    private String bankName;
    private String accountNumber;

    public BankPayment() {
    }

    public BankPayment(String bankName,
                       String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    @Override
    public void processPayment(double amount) {

        System.out.println(
                "Processing Bank Transfer Payment of ₹"
                        + amount);
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "BankPayment{" +
                "bankName='" + bankName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankPayment that = (BankPayment) o;
        return Objects.equals(bankName, that.bankName) && Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, accountNumber);
    }
}