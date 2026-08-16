package com.crimsonlogic.mutualfundinvestmentspringmvc.comparators;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;

import java.util.Comparator;

public class TransactionDateComparator
        implements Comparator<Transaction> {

    @Override
    public int compare(
            Transaction transaction1,
            Transaction transaction2) {

        return transaction1.getTransactionDateTime()
                .compareTo(
                        transaction2.getTransactionDateTime());
    }
}