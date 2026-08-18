package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.Payment;

public interface PaymentMapper {

    void insertPayment(Payment payment);

    Payment getPaymentById(String paymentId);
}