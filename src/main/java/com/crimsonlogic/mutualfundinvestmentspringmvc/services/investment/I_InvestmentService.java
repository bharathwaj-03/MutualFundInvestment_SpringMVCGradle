package com.crimsonlogic.mutualfundinvestmentspringmvc.services.investment;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.Investment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;

import java.util.List;
import java.util.Map;

public interface I_InvestmentService {

    Investment startInvestment(
            String investorId,
            String fundId,
            double amount,
            int investmentYears,
            Payable paymentMethod
    );

    Investment getInvestmentById(
            String investmentId
    );

    List<Investment> getInvestmentsByUser(
            String userId
    );

    List<Investment> getAllInvestments();
    Map<String, String> validateInvestment(
            String fundId,
            double amount,
            int investmentYears,
            String paymentType
    );
}