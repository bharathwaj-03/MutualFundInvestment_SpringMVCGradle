package com.crimsonlogic.mutualfundinvestmentspringmvc.services.payment;

import com.crimsonlogic.mutualfundinvestmentspringmvc.exception.PaymentFailedException;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService
        implements I_PaymentService {


    // =========================================================
    // PROCESS PAYMENT
    // =========================================================

    @Override
    public boolean processPayment(
            Payable paymentMethod,
            double amount) {

        if (!validatePayment(amount)) {

            throw new IllegalArgumentException(
                    "Payment amount must be greater than 0."
            );
        }

        if (paymentMethod == null) {

            throw new IllegalArgumentException(
                    "Please select a payment method."
            );
        }

        try {

            paymentMethod.processPayment(amount);

            return true;

        } catch (Exception e) {

            throw new IllegalStateException(
                    "Payment processing failed.",
                    e
            );
        }
    }


    // =========================================================
    // VALIDATE PAYMENT
    // =========================================================

    @Override
    public boolean validatePayment(
            double amount) {

        return amount > 0;
    }


    // =========================================================
    // GENERATE RECEIPT
    // =========================================================

    @Override
    public void generateReceipt(
            double amount,
            String paymentType) {

        System.out.println(
                "\n===== PAYMENT RECEIPT ====="
        );

        System.out.println(
                "Payment Mode : "
                        + paymentType
        );

        System.out.println(
                "Amount : ₹"
                        + String.format(
                        "%.2f",
                        amount
                )
        );

        System.out.println(
                "Status : SUCCESS"
        );
    }
}