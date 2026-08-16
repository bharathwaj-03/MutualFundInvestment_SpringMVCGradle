package com.crimsonlogic.mutualfundinvestmentspringmvc.services.payment;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;

public interface I_PaymentService {

    boolean processPayment(
            Payable paymentMethod,
            double amount
    );

    boolean validatePayment(
            double amount
    );

    void generateReceipt(
            double amount,
            String paymentType
    );
}