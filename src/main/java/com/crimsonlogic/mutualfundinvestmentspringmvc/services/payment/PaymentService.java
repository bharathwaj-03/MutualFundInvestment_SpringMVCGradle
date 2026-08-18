package com.crimsonlogic.mutualfundinvestmentspringmvc.services.payment;

import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.PaymentMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.exception.PaymentFailedException;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.BankPayment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.CardPayment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.Payment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.UpiPayment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.IdGeneratorUtil;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.security.EncryptionUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService
        implements I_PaymentService {


    private PaymentMapper paymentMapper;


    // =========================================================
    // SETTER INJECTION
    // =========================================================

    public void setPaymentMapper(
            PaymentMapper paymentMapper) {

        this.paymentMapper =
                paymentMapper;
    }


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
    // SAVE PAYMENT
    // =========================================================

    @Override
    public Payment savePayment(
            String investorId,
            Payable paymentMethod,
            double amount) {

        if (investorId == null ||
                investorId.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Investor ID cannot be empty."
            );
        }


        if (paymentMethod == null) {

            throw new IllegalArgumentException(
                    "Payment method cannot be null."
            );
        }


        Payment payment =
                new Payment();


        payment.setPaymentId(
                IdGeneratorUtil.generatePaymentId()
        );


        payment.setInvestorId(
                investorId
        );


        payment.setPaymentStatus(
                "SUCCESS"
        );


        payment.setPaymentDate(
                LocalDateTime.now()
        );


        // =====================================================
        // UPI
        // =====================================================

        if (paymentMethod instanceof UpiPayment) {

            UpiPayment upiPayment =
                    (UpiPayment) paymentMethod;


            payment.setPaymentMethod(
                    "UPI"
            );


            payment.setUpiId(
                    EncryptionUtil.encrypt(
                            upiPayment.getUpiId()
                    )
            );
        }


        // =====================================================
        // CARD
        // =====================================================

        else if (paymentMethod instanceof CardPayment) {

            CardPayment cardPayment =
                    (CardPayment) paymentMethod;


            payment.setPaymentMethod(
                    "CARD"
            );


            payment.setCardNumber(
                    EncryptionUtil.encrypt(
                            cardPayment.getCardNumber()
                    )
            );

            payment.setCardHolderName(
                    cardPayment.getCardHolderName()
            );
        }


        // =====================================================
        // BANK
        // =====================================================

        else if (paymentMethod instanceof BankPayment) {

            BankPayment bankPayment =
                    (BankPayment) paymentMethod;


            payment.setPaymentMethod(
                    "BANK"
            );


            payment.setAccountNumber(
                    EncryptionUtil.encrypt(
                            bankPayment.getAccountNumber()
                    )
            );

            payment.setBankName(
                    bankPayment.getBankName()
            );
        }


        else {

            throw new IllegalArgumentException(
                    "Unsupported payment method."
            );
        }


        // =====================================================
        // SAVE TO DATABASE
        // =====================================================

        try {

            paymentMapper.insertPayment(
                    payment
            );

            return payment;

        } catch (Exception e) {

            e.printStackTrace();

            throw new IllegalStateException(
                    "Unable to save payment details.",
                    e
            );
        }
    }


    // =========================================================
    // GET PAYMENT
    // =========================================================

    @Override
    public Payment getPaymentById(
            String paymentId) {

        return paymentMapper
                .getPaymentById(
                        paymentId
                );
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