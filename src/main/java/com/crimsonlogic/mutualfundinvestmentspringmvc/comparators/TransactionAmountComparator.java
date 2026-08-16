package com.crimsonlogic.mutualfundinvestmentspringmvc.comparators;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;

import java.util.Comparator;

public class TransactionAmountComparator
        implements Comparator<Transaction> {

    @Override
    public int compare(
            Transaction transaction1,
            Transaction transaction2) {

        return Double.compare(
                transaction1.getAmount(),
                transaction2.getAmount());
    }
}