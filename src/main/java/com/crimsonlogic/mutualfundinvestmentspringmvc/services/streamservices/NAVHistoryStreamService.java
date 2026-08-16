//package com.crimsonlogic.mutualfundinvestmentspringmvc.services.streamservices;
//
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.nav.NAVHistory;
//
//import java.util.Comparator;
//import java.util.DoubleSummaryStatistics;
//import java.util.List;
//import java.util.Optional;
//
//public class NAVHistoryStreamService {
//
//    // =========================================================
//    // 1. NEW NAV SUMMARY STATISTICS
//    // =========================================================
//
//    public void displayNewNAVSummaryStatistics(
//            List<NAVHistory> historyList) {
//
//        if (historyList == null || historyList.isEmpty()) {
//            System.out.println("\nNo NAV history available.");
//            return;
//        }
//
//        DoubleSummaryStatistics stats =
//                historyList.stream()
//                        .mapToDouble(NAVHistory::getNewNav)
//                        .summaryStatistics();
//
//        System.out.println("\n========== NEW NAV SUMMARY STATISTICS ==========");
//
//        System.out.println("Count   : " + stats.getCount());
//        System.out.println("Sum     : ₹" + String.format("%.2f", stats.getSum()));
//        System.out.println("Average : ₹" + String.format("%.2f", stats.getAverage()));
//        System.out.println("Minimum : ₹" + String.format("%.2f", stats.getMin()));
//        System.out.println("Maximum : ₹" + String.format("%.2f", stats.getMax()));
//    }
//
//
//    // =========================================================
//    // 2. LATEST NAV CHANGE
//    // =========================================================
//
//    public void displayLatestNAVChange(
//            List<NAVHistory> historyList) {
//
//        if (historyList == null || historyList.isEmpty()) {
//            System.out.println("\nNo NAV history available.");
//            return;
//        }
//
//        Optional<NAVHistory> latestNAV =
//                historyList.stream()
//                        .max(
//                                Comparator.comparing(
//                                        NAVHistory::getChangeDate)
//                        );
//
//        System.out.println("\n========== LATEST NAV CHANGE ==========");
//
//        latestNAV.ifPresent(history -> {
//
//            System.out.println("History ID  : "
//                    + history.getHistoryId());
//
//            System.out.println("Fund ID     : "
//                    + history.getMutualFund().getFundId());
//
//            System.out.println("Old NAV     : ₹"
//                    + String.format(
//                    "%.2f",
//                    history.getOldNav()));
//
//            System.out.println("New NAV     : ₹"
//                    + String.format(
//                    "%.2f",
//                    history.getNewNav()));
//
//            System.out.println("Change Date : "
//                    + history.getChangeDate());
//
//            System.out.println("Changed By  : "
//                    + history.getChangedBy());
//        });
//    }
//
//
//    // =========================================================
//    // 3. NAV INCREASES
//    // =========================================================
//
//    public void displayNAVIncreases(
//            List<NAVHistory> historyList) {
//
//        if (historyList == null || historyList.isEmpty()) {
//            System.out.println("\nNo NAV history available.");
//            return;
//        }
//
//        List<NAVHistory> increases =
//                historyList.stream()
//                        .filter(history ->
//                                history.getNewNav()
//                                        > history.getOldNav())
//                        .toList();
//
//        System.out.println("\n========== NAV INCREASES ==========");
//
//        if (increases.isEmpty()) {
//            System.out.println("No NAV increases found.");
//            return;
//        }
//
//        increases.forEach(history -> {
//
//            System.out.println("--------------------------------");
//
//            System.out.println("History ID : "
//                    + history.getHistoryId());
//
//            System.out.println("Fund ID    : "
//                    + history.getMutualFund().getFundId());
//
//            System.out.println("Old NAV    : ₹"
//                    + String.format(
//                    "%.2f",
//                    history.getOldNav()));
//
//            System.out.println("New NAV    : ₹"
//                    + String.format(
//                    "%.2f",
//                    history.getNewNav()));
//
//            System.out.println("Increase   : ₹"
//                    + String.format(
//                    "%.2f",
//                    history.getNewNav()
//                            - history.getOldNav()));
//
//            System.out.println("Date       : "
//                    + history.getChangeDate());
//        });
//
//        System.out.println("--------------------------------");
//    }
//
//
//    // =========================================================
//    // 4. NAV DECREASES
//    // =========================================================
//
//    public void displayNAVDecreases(
//            List<NAVHistory> historyList) {
//
//        if (historyList == null || historyList.isEmpty()) {
//            System.out.println("\nNo NAV history available.");
//            return;
//        }
//
//        List<NAVHistory> decreases =
//                historyList.stream()
//                        .filter(history ->
//                                history.getNewNav()
//                                        < history.getOldNav())
//                        .toList();
//
//        System.out.println("\n========== NAV DECREASES ==========");
//
//        if (decreases.isEmpty()) {
//            System.out.println("No NAV decreases found.");
//            return;
//        }
//
//        decreases.forEach(history -> {
//
//            System.out.println("--------------------------------");
//
//            System.out.println("History ID : "
//                    + history.getHistoryId());
//
//            System.out.println("Fund ID    : "
//                    + history.getMutualFund().getFundId());
//
//            System.out.println("Old NAV    : ₹"
//                    + String.format(
//                    "%.2f",
//                    history.getOldNav()));
//
//            System.out.println("New NAV    : ₹"
//                    + String.format(
//                    "%.2f",
//                    history.getNewNav()));
//
//            System.out.println("Decrease   : ₹"
//                    + String.format(
//                    "%.2f",
//                    history.getOldNav()
//                            - history.getNewNav()));
//
//            System.out.println("Date       : "
//                    + history.getChangeDate());
//        });
//
//        System.out.println("--------------------------------");
//    }
//}