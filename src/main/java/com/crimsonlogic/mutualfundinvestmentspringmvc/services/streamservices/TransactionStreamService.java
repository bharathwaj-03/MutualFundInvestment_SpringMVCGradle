//package com.crimsonlogic.mutualfundinvestmentspringmvc.services.streamservices;
//
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//public class TransactionStreamService {
//
//
//    public void groupTransactionsByType(
//            List<Transaction> transactions) {
//
//        if (transactions == null || transactions.isEmpty()) {
//            System.out.println("\nNo transactions available.");
//            return;
//        }
//
//        System.out.println(
//                "\n========== TRANSACTIONS GROUPED BY TYPE ==========");
//
//        transactions.stream()
//                .collect(Collectors.groupingBy(
//                        Transaction::getTransactionType))
//                .forEach((type, list) -> {
//
//                    System.out.println(
//                            "\nTransaction Type : " + type);
//
//                    list.forEach(transaction -> {
//
//                        System.out.println("--------------------------------");
//
//                        System.out.println(
//                                "Transaction ID : "
//                                        + transaction.getTransactionId());
//
//                        System.out.println(
//                                "Amount         : ₹"
//                                        + transaction.getAmount());
//
//                        System.out.println(
//                                "Status         : "
//                                        + transaction.getTransactionStatus());
//
//                        System.out.println(
//                                "Date           : "
//                                        + transaction.getTransactionDateTime());
//                    });
//                });
//    }
//
//
//    public void getLatestTransaction(
//            List<Transaction> transactions) {
//
//        if (transactions == null || transactions.isEmpty()) {
//            System.out.println("\nNo transactions available.");
//            return;
//        }
//
//        System.out.println("\n========== LATEST TRANSACTION ==========");
//
//        transactions.stream()
//                .max(Comparator.comparing(
//                        Transaction::getTransactionDateTime))
//                .ifPresent(transaction -> {
//
//                    System.out.println("--------------------------------");
//                    System.out.println(
//                            "Transaction ID : "
//                                    + transaction.getTransactionId());
//
//                    System.out.println(
//                            "Investor ID    : "
//                                    + transaction.getInvestor().getUserId());
//
//                    System.out.println(
//                            "Fund           : "
//                                    + transaction.getMutualFund().getFundName());
//
//                    System.out.println(
//                            "Type           : "
//                                    + transaction.getTransactionType());
//
//                    System.out.println(
//                            "Amount         : ₹"
//                                    + String.format(
//                                    "%.2f",
//                                    transaction.getAmount()));
//
//                    System.out.println(
//                            "Status         : "
//                                    + transaction.getTransactionStatus());
//
//                    System.out.println(
//                            "Date           : "
//                                    + transaction.getTransactionDateTime());
//
//                    System.out.println("--------------------------------");
//                });
//    }
//
//
//    public void getEarliestTransaction(
//            List<Transaction> transactions) {
//
//        System.out.println(
//                "\n===== EARLIEST TRANSACTION =====");
//
//        if (transactions == null || transactions.isEmpty()) {
//            System.out.println("No transactions available.");
//            return;
//        }
//
//        transactions.stream()
//                .min(
//                        Comparator.comparing(
//                                Transaction::getTransactionDateTime))
//                .ifPresentOrElse(
//
//                        transaction -> {
//
//                            System.out.println(
//                                    "Transaction ID : "
//                                            + transaction.getTransactionId());
//
//                            System.out.println(
//                                    "Investor ID    : "
//                                            + (transaction.getInvestor() != null
//                                            ? transaction.getInvestor().getUserId()
//                                            : "N/A"));
//
//                            System.out.println(
//                                    "Fund           : "
//                                            + (transaction.getMutualFund() != null
//                                            ? transaction.getMutualFund().getFundName()
//                                            : "N/A"));
//
//                            System.out.println(
//                                    "Transaction Type : "
//                                            + transaction.getTransactionType());
//
//                            System.out.println(
//                                    "Amount         : ₹"
//                                            + String.format(
//                                            "%.2f",
//                                            transaction.getAmount()));
//
//                            System.out.println(
//                                    "Status         : "
//                                            + transaction.getTransactionStatus());
//
//                            System.out.println(
//                                    "Date           : "
//                                            + transaction.getTransactionDateTime());
//                        },
//
//                        () -> System.out.println(
//                                "No transactions available.")
//                );
//    }
//
//    public void hasFailedTransactions(
//            List<Transaction> transactions) {
//
//        if (transactions == null || transactions.isEmpty()) {
//            System.out.println("\nNo transactions available.");
//            return;
//        }
//
//        boolean failed =
//                transactions.stream()
//                        .anyMatch(transaction ->
//                                "FAILED".equalsIgnoreCase(
//                                        transaction.getTransactionStatus()));
//
//        System.out.println(
//                "\n========== FAILED TRANSACTIONS ==========");
//
//        if (failed) {
//            System.out.println(
//                    "Failed transactions exist.");
//        } else {
//            System.out.println(
//                    "No failed transactions found.");
//        }
//    }
//    public void displayTransactionsSortedByDate(
//            List<Transaction> transactions) {
//
//        if (transactions == null || transactions.isEmpty()) {
//
//            System.out.println("\nNo transactions available.");
//            return;
//        }
//
//        System.out.println(
//                "\n========== TRANSACTIONS SORTED BY DATE ==========");
//
//        transactions.stream()
//                .sorted(
//                        Comparator.comparing(
//                                Transaction::getTransactionDateTime))
//                .forEach(transaction -> {
//
//                    System.out.println("--------------------------------");
//
//                    System.out.println(
//                            "Transaction ID : "
//                                    + transaction.getTransactionId());
//
//                    System.out.println(
//                            "Investor ID    : "
//                                    + transaction.getInvestor().getUserId());
//
//                    System.out.println(
//                            "Fund           : "
//                                    + transaction.getMutualFund().getFundName());
//
//                    System.out.println(
//                            "Transaction Type : "
//                                    + transaction.getTransactionType());
//
//                    System.out.println(
//                            "Amount         : ₹"
//                                    + String.format(
//                                    "%.2f",
//                                    transaction.getAmount()));
//
//                    System.out.println(
//                            "Status         : "
//                                    + transaction.getTransactionStatus());
//
//                    System.out.println(
//                            "Date           : "
//                                    + transaction.getTransactionDateTime());
//                });
//
//        System.out.println("--------------------------------");
//    }
//
//
//    // =========================================================
//    // SORT TRANSACTIONS BY AMOUNT
//    // =========================================================
//
//    public void displayTransactionsSortedByAmount(
//            List<Transaction> transactions) {
//
//        if (transactions == null || transactions.isEmpty()) {
//
//            System.out.println("\nNo transactions available.");
//            return;
//        }
//
//        System.out.println(
//                "\n========== TRANSACTIONS SORTED BY AMOUNT ==========");
//
//        transactions.stream()
//                .sorted(
//                        Comparator.comparingDouble(
//                                Transaction::getAmount))
//                .forEach(transaction -> {
//
//                    System.out.println("--------------------------------");
//
//                    System.out.println(
//                            "Transaction ID : "
//                                    + transaction.getTransactionId());
//
//                    System.out.println(
//                            "Investor ID    : "
//                                    + transaction.getInvestor().getUserId());
//
//                    System.out.println(
//                            "Fund           : "
//                                    + transaction.getMutualFund().getFundName());
//
//                    System.out.println(
//                            "Transaction Type : "
//                                    + transaction.getTransactionType());
//
//                    System.out.println(
//                            "Amount         : ₹"
//                                    + String.format(
//                                    "%.2f",
//                                    transaction.getAmount()));
//
//                    System.out.println(
//                            "Status         : "
//                                    + transaction.getTransactionStatus());
//
//                    System.out.println(
//                            "Date           : "
//                                    + transaction.getTransactionDateTime());
//                });
//
//        System.out.println("--------------------------------");
//    }
//}