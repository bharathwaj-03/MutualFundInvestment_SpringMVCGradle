package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;

import java.util.List;

public interface TransactionMapper {

    void insertTransaction(
            Transaction transaction
    );

    Transaction getTransactionById(
            String transactionId
    );

    void deleteTransaction(
            String transactionId
    );

    List<Transaction> getAllTransactions();

    Transaction getLatestTransaction();
}