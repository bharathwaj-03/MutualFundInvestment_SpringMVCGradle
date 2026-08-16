package com.crimsonlogic.mutualfundinvestmentspringmvc.services.transaction;

import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.TransactionMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TransactionService
        implements I_TransactionService {

    private TransactionMapper transactionMapper;


    // =========================================================
    // SETTER INJECTION
    // =========================================================

    public void setTransactionMapper(
            TransactionMapper transactionMapper) {

        this.transactionMapper = transactionMapper;
    }


    // =========================================================
    // ADD TRANSACTION
    // =========================================================

    @Override
    public boolean addTransaction(
            Transaction transaction) {

        try {

            if (transaction == null) {
                return false;
            }

            transactionMapper.insertTransaction(
                    transaction
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // SEARCH TRANSACTION
    // =========================================================

    @Override
    public Transaction getTransactionById(
            String transactionId) {

        return transactionMapper
                .getTransactionById(
                        transactionId
                );
    }


    // =========================================================
    // DELETE TRANSACTION
    // =========================================================

    @Override
    public boolean deleteTransaction(
            String transactionId) {

        try {

            Transaction transaction =
                    transactionMapper
                            .getTransactionById(
                                    transactionId
                            );

            if (transaction == null) {
                return false;
            }

            transactionMapper.deleteTransaction(
                    transactionId
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET ALL TRANSACTIONS
    // =========================================================

    @Override
    public List<Transaction> getAllTransactions() {

        return transactionMapper
                .getAllTransactions();
    }


    // =========================================================
    // GET LATEST TRANSACTION
    // =========================================================

    @Override
    public Transaction getLatestTransaction() {

        return transactionMapper
                .getLatestTransaction();
    }


    // =========================================================
    // TOTAL TRANSACTION AMOUNT
    // =========================================================

    @Override
    public double getTotalTransactionAmount() {

        List<Transaction> transactions =
                transactionMapper
                        .getAllTransactions();

        if (transactions == null) {
            return 0.0;
        }

        return transactions.stream()
                .mapToDouble(
                        Transaction::getAmount
                )
                .sum();
    }


    // =========================================================
    // TRANSACTION COUNT
    // =========================================================

    @Override
    public long getTransactionCount() {

        List<Transaction> transactions =
                transactionMapper
                        .getAllTransactions();

        if (transactions == null) {
            return 0;
        }

        return transactions.size();
    }


    // =========================================================
    // SORT TRANSACTIONS BY AMOUNT
    // =========================================================

    @Override
    public List<Transaction> getTransactionsByAmount() {

        List<Transaction> transactions =
                transactionMapper
                        .getAllTransactions();

        if (transactions == null) {
            return List.of();
        }

        return transactions.stream()
                .sorted(
                        Comparator.comparingDouble(
                                Transaction::getAmount
                        )
                )
                .toList();
    }
}