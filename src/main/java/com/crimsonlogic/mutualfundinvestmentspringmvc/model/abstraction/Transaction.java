package com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;

import java.time.LocalDateTime;

public abstract class Transaction {
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

    private String transactionId;
    private double amount;
    private LocalDateTime transactionDateTime;
    private Investor investor;
    private String paymentId;


    private MutualFund mutualFund;
    private String transactionStatus;

    private String transactionType;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Transaction() {
    }

    public Transaction(String transactionId,
                       double amount,
                       LocalDateTime transactionDateTime) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
    }

    public abstract void executeTransaction();

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", investor=" + (investor != null ? investor.getUserId() : null) +
                ", mutualFund=" + (mutualFund != null ? mutualFund.getFundId() : null) +
                ", amount=" + amount +
                ", transactionDateTime=" + transactionDateTime +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}