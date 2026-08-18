package com.crimsonlogic.mutualfundinvestmentspringmvc.services.payment;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.Payment;

public interface I_PaymentService {

    boolean processPayment(
            Payable paymentMethod,
            double amount
    );

    Payment savePayment(
            String investorId,
            Payable paymentMethod,
            double amount
    );

    Payment getPaymentById(
            String paymentId
    );

    boolean validatePayment(
            double amount
    );

    void generateReceipt(
            double amount,
            String paymentType
    );
}