package com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;

import java.util.Objects;

public class CardPayment implements Payable {

    private String cardNumber;
    private String cardHolderName;

    public CardPayment() {
    }

    public CardPayment(String cardNumber,
                       String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void processPayment(double amount) {

        System.out.println(
                "Processing Card Payment of ₹"
                        + amount);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @Override
    public String toString() {
        return "CardPayment{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardPayment that = (CardPayment) o;
        return Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cardHolderName, that.cardHolderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardHolderName);
    }
}