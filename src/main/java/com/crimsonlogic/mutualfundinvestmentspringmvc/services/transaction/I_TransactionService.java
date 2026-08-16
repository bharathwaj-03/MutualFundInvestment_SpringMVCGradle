package com.crimsonlogic.mutualfundinvestmentspringmvc.services.transaction;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;

import java.util.List;

public interface I_TransactionService {

    boolean addTransaction(Transaction transaction);

    Transaction getTransactionById(String transactionId);

    boolean deleteTransaction(String transactionId);

    List<Transaction> getAllTransactions();

    Transaction getLatestTransaction();

    double getTotalTransactionAmount();

    long getTransactionCount();

    List<Transaction> getTransactionsByAmount();
}