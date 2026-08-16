//package com.crimsonlogic.mutualfundinvestmentspringmvc.services.streamservices;
//
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class MutualFundStreamService {
//
//    // =========================================================
//    // 1. FILTER FUNDS BY CATEGORY
//    // =========================================================
//
//    // =========================================================
//// 1. FILTER FUNDS BY CATEGORY
//// =========================================================
//
//    public void filterFundsByCategory(
//            List<MutualFund> funds,
//            String category) {
//
//        System.out.println(
//                "\n===== FUNDS BY CATEGORY =====");
//
//        if (funds == null || funds.isEmpty()) {
//            System.out.println("No funds available.");
//            return;
//        }
//
//        if (category == null || category.trim().isEmpty()) {
//            System.out.println("Invalid category.");
//            return;
//        }
//
//        String searchCategory = category.trim();
//
//        List<MutualFund> result =
//                funds.stream()
//                        .filter(fund ->
//                                fund.getFundCategory() != null
//                                        &&
//                                        fund.getFundCategory()
//                                                .trim()
//                                                .equalsIgnoreCase(searchCategory))
//                        .toList();
//
//        if (result.isEmpty()) {
//
//            System.out.println(
//                    "No funds found for category: "
//                            + searchCategory);
//
//            return;
//        }
//
//        result.forEach(fund -> {
//
//            System.out.println(
//                    "Fund ID  : "
//                            + fund.getFundId());
//
//            System.out.println(
//                    "Name     : "
//                            + fund.getFundName());
//
//            System.out.println(
//                    "Category : "
//                            + fund.getFundCategory());
//
//            System.out.println(
//                    "NAV      : ₹"
//                            + String.format(
//                            "%.2f",
//                            fund.getNav()));
//
//            System.out.println("--------------------------------");
//        });
//    }
//
//
//    // =========================================================
//    // 2. HIGH RISK FUNDS
//    // =========================================================
//
//    public void getHighRiskFunds(
//            List<MutualFund> funds) {
//
//        System.out.println(
//                "\n===== HIGH RISK FUNDS =====");
//
//        List<MutualFund> result =
//                funds.stream()
//                        .filter(fund ->
//                                "HIGH".equalsIgnoreCase(
//                                        fund.getRiskLevel()))
//                        .toList();
//
//        if (result.isEmpty()) {
//            System.out.println("No high risk funds found.");
//            return;
//        }
//
//        result.forEach(fund ->
//                System.out.println(
//                        fund.getFundName()
//                                + " | Risk : "
//                                + fund.getRiskLevel()
//                                + " | NAV : ₹"
//                                + fund.getNav()));
//    }
//
//
//    // =========================================================
//    // 3. SORT FUNDS BY NAV
//    // =========================================================
//
//    public void sortFundsByNAV(
//            List<MutualFund> funds) {
//
//        System.out.println(
//                "\n===== FUNDS SORTED BY NAV =====");
//
//        funds.stream()
//                .sorted(
//                        Comparator.comparingDouble(
//                                MutualFund::getNav))
//                .forEach(fund ->
//                        System.out.println(
//                                fund.getFundName()
//                                        + " | NAV : ₹"
//                                        + fund.getNav()));
//    }
//
//
//    // =========================================================
//    // 4. TOP 5 PERFORMING FUNDS
//    // =========================================================
//
//    public void getTop5PerformingFunds(
//            List<MutualFund> funds) {
//
//        System.out.println(
//                "\n===== TOP 5 PERFORMING FUNDS =====");
//
//        funds.stream()
//                .sorted(
//                        Comparator.comparingDouble(
//                                        MutualFund::getNav)
//                                .reversed())
//                .limit(5)
//                .forEach(fund ->
//                        System.out.println(
//                                fund.getFundName()
//                                        + " | NAV : ₹"
//                                        + fund.getNav()));
//    }
//
//
//    // =========================================================
//    // 5. LOWEST NAV FUND
//    // =========================================================
//
//    public void getLowestPerformingFund(
//            List<MutualFund> funds) {
//
//        System.out.println(
//                "\n===== LOWEST NAV FUND =====");
//
//        funds.stream()
//                .min(
//                        Comparator.comparingDouble(
//                                MutualFund::getNav))
//                .ifPresentOrElse(
//
//                        fund -> System.out.println(
//                                "Fund : "
//                                        + fund.getFundName()
//                                        + "\nNAV  : ₹"
//                                        + fund.getNav()),
//
//                        () -> System.out.println(
//                                "No funds available.")
//                );
//    }
//
//
//    // =========================================================
//    // 6. HIGHEST NAV FUND
//    // =========================================================
//
//    public void getHighestNAVFund(
//            List<MutualFund> funds) {
//
//        System.out.println(
//                "\n===== HIGHEST NAV FUND =====");
//
//        funds.stream()
//                .max(
//                        Comparator.comparingDouble(
//                                MutualFund::getNav))
//                .ifPresentOrElse(
//
//                        fund -> System.out.println(
//                                "Fund : "
//                                        + fund.getFundName()
//                                        + "\nNAV  : ₹"
//                                        + fund.getNav()),
//
//                        () -> System.out.println(
//                                "No funds available.")
//                );
//    }
//
//
//    // =========================================================
//    // 7. GROUP BY CATEGORY
//    // =========================================================
//
//    public void groupFundsByCategory(
//            List<MutualFund> funds) {
//
//        System.out.println(
//                "\n===== FUNDS GROUPED BY CATEGORY =====");
//
//        Map<String, List<MutualFund>> grouped =
//                funds.stream()
//                        .collect(
//                                Collectors.groupingBy(
//                                        MutualFund::getFundCategory));
//
//        grouped.forEach((category, list) -> {
//
//            System.out.println(
//                    "\nCategory : " + category);
//
//            list.forEach(fund ->
//                    System.out.println(
//                            "  - "
//                                    + fund.getFundName()
//                                    + " | NAV : ₹"
//                                    + fund.getNav()));
//        });
//    }
//
//
//    // =========================================================
//    // 8. DISTINCT CATEGORIES
//    // =========================================================
//
//    public void getDistinctCategories(
//            List<MutualFund> funds) {
//
//        System.out.println(
//                "\n===== DISTINCT FUND CATEGORIES =====");
//
//        funds.stream()
//                .map(MutualFund::getFundCategory)
//                .distinct()
//                .forEach(System.out::println);
//    }
//
//
//    // =========================================================
//    // 9. JOIN FUND NAMES
//    // =========================================================
//
//    public void joinFundNames(
//            List<MutualFund> funds) {
//
//        String result =
//                funds.stream()
//                        .map(MutualFund::getFundName)
//                        .collect(
//                                Collectors.joining(", "));
//
//        System.out.println(
//                "\n===== ALL FUND NAMES =====");
//
//        System.out.println(result);
//    }
//
//
//    // =========================================================
//    // 10. IMMUTABLE FUND LIST
//    // =========================================================
//
//    public void getImmutableFundList(
//            List<MutualFund> funds) {
//
//        List<MutualFund> immutableFunds =
//                funds.stream()
//                        .collect(
//                                Collectors.toUnmodifiableList());
//
//        System.out.println(
//                "\n===== IMMUTABLE FUND LIST =====");
//
//        immutableFunds.forEach(fund ->
//                System.out.println(
//                        fund.getFundName()
//                                + " | NAV : ₹"
//                                + fund.getNav()));
//    }
//
//
//    // =========================================================
//    // 11. SORT BY NAME USING COMPARATOR
//    // =========================================================
//
//    public void displayFundsSortedByNameComparator(
//            List<MutualFund> funds) {
//
//        System.out.println(
//                "\n===== FUNDS SORTED BY NAME (COMPARATOR) =====");
//
//        funds.stream()
//                .sorted((f1, f2) ->
//                        f1.getFundName()
//                                .compareToIgnoreCase(
//                                        f2.getFundName()))
//                .forEach(f ->
//                        System.out.println(
//                                f.getFundName()));
//    }
//
//
//    // =========================================================
//    // 12. SORT USING COMPARABLE
//    // =========================================================
//
//    public void displayFundsSortedComparable(
//            List<MutualFund> funds) {
//
//        System.out.println(
//                "\n===== FUNDS SORTED (COMPARABLE) =====");
//
//        funds.stream()
//                .sorted()
//                .forEach(f ->
//                        System.out.println(
//                                f.getFundName()));
//    }
//}