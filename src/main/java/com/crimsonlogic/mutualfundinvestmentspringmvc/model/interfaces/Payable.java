package com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces;


public interface Payable {

    //helps to implement various payment methods
    void processPayment(double amount);

}