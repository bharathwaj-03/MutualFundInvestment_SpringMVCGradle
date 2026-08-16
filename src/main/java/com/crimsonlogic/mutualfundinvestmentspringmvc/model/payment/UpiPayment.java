package com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment;


import com.crimsonlogic.mutualfundinvestmentspringmvc.exception.UserDataValidationException;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.UserDataValidation;

import java.util.Objects;

public class UpiPayment implements Payable {

    private String upiId;

    public UpiPayment() {
    }


    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    public UserDataValidation upiValidate = (String str) -> {

        boolean isValid =
                str.matches("^[a-zA-Z0-9._]{3,}@[a-zA-Z]{2,20}$");

        if (!isValid) {

            throw new UserDataValidationException(
                    "Please enter a valid UPI ID. Ex: bharath@okaxis");
        }

        return str.toLowerCase();
    };

    @Override
    public void processPayment(double amount) {

        System.out.println(
                "Processing UPI Payment of ₹"
                        + amount);
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId)
            throws UserDataValidationException {

        this.upiId =
                upiValidate.validate(upiId);
    }

    @Override
    public String toString() {
        return "UpiPayment{" +
                "upiId='" + upiId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpiPayment that = (UpiPayment) o;
        return Objects.equals(upiId, that.upiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upiId);
    }
}