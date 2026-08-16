//package com.crimsonlogic.mutualfundinvestmentspringmvc.services.streamservices;
//
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class PortfolioStreamService {
//
//    // =========================================================
//    // 1. TOTAL AUM
//    // =========================================================
//
//    public void calculateTotalAUM(
//            List<Portfolio> portfolios,
//            List<Holding> holdings) {
//
//        if (holdings == null || holdings.isEmpty()) {
//
//            System.out.println(
//                    "\nNo portfolio holdings available.");
//
//            return;
//        }
//
//        double totalAUM =
//                holdings.stream()
//                        .mapToDouble(h ->
//                                h.getUnitsOwned()
//                                        * h.getMutualFund().getNav())
//                        .sum();
//
//        System.out.println(
//                "\n===== TOTAL AUM =====");
//
//        System.out.println(
//                "Total AUM : ₹"
//                        + String.format(
//                        "%.2f",
//                        totalAUM));
//    }
//
//
//    // =========================================================
//    // 2. AVERAGE PORTFOLIO RETURN
//    // =========================================================
//
//    public void displayAveragePortfolioReturn(
//            List<Portfolio> portfolios) {
//
//        if (portfolios == null || portfolios.isEmpty()) {
//
//            System.out.println(
//                    "\nNo portfolios available.");
//
//            return;
//        }
//
//        double averageReturn =
//                portfolios.stream()
//                        .mapToDouble(portfolio -> {
//
//                            double currentValue =
//                                    portfolio.getHoldings()
//                                            .stream()
//                                            .mapToDouble(holding ->
//                                                    holding.getUnitsOwned()
//                                                            * holding.getMutualFund()
//                                                            .getNav())
//                                            .sum();
//
//                            double investedAmount =
//                                    portfolio.getHoldings()
//                                            .stream()
//                                            .mapToDouble(
//                                                    Holding::getInvestedAmount)
//                                            .sum();
//
//                            return currentValue - investedAmount;
//                        })
//                        .average()
//                        .orElse(0.0);
//
//        System.out.println(
//                "\n===== AVERAGE PORTFOLIO RETURN =====");
//
//        System.out.println(
//                "Average Portfolio Return : ₹"
//                        + String.format(
//                        "%.2f",
//                        averageReturn));
//    }
//
//
//    // =========================================================
//    // 3. COUNT HOLDINGS PER PORTFOLIO
//    // =========================================================
//
//    public void displayCountHoldingsPerPortfolio(
//            List<Portfolio> portfolios) {
//
//        if (portfolios == null || portfolios.isEmpty()) {
//
//            System.out.println(
//                    "\nNo portfolios available.");
//
//            return;
//        }
//
//        System.out.println(
//                "\n===== HOLDINGS PER PORTFOLIO =====");
//
//        portfolios.stream()
//                .forEach(portfolio -> {
//
//                    System.out.println(
//                            "Portfolio ID : "
//                                    + portfolio.getPortfolioId());
//
//                    System.out.println(
//                            "Number of Holdings : "
//                                    + portfolio.getHoldings().size());
//
//                    System.out.println(
//                            "--------------------------------");
//                });
//    }
//
//
//    // =========================================================
//    // 4. HIGHEST PORTFOLIO VALUE
//    // =========================================================
//
//    public void getHighestPortfolioValue(
//            List<Portfolio> portfolios,
//            List<Holding> holdings) {
//
//        if (holdings == null || holdings.isEmpty()) {
//
//            System.out.println(
//                    "\nNo portfolio holdings available.");
//
//            return;
//        }
//
//        Map<String, Double> portfolioValues =
//                holdings.stream()
//                        .collect(
//                                Collectors.groupingBy(
//                                        h -> h.getPortfolio()
//                                                .getPortfolioId(),
//
//                                        Collectors.summingDouble(
//                                                h -> h.getUnitsOwned()
//                                                        * h.getMutualFund()
//                                                        .getNav()
//                                        )
//                                )
//                        );
//
//        if (portfolioValues.isEmpty()) {
//
//            System.out.println(
//                    "\nNo portfolio holdings available.");
//
//            return;
//        }
//
//        Map.Entry<String, Double> highest =
//                portfolioValues.entrySet()
//                        .stream()
//                        .max(
//                                Map.Entry.comparingByValue()
//                        )
//                        .orElse(null);
//
//        System.out.println(
//                "\n===== HIGHEST PORTFOLIO VALUE =====");
//
//        System.out.println(
//                "Portfolio ID : "
//                        + highest.getKey());
//
//        System.out.println(
//                "Portfolio Value : ₹"
//                        + String.format(
//                        "%.2f",
//                        highest.getValue()));
//    }
//
//
//    // =========================================================
//    // 5. INACTIVE PORTFOLIOS
//    // =========================================================
//
//    public void displayInactivePortfolios(
//            List<Portfolio> portfolios) {
//
//        if (portfolios == null || portfolios.isEmpty()) {
//
//            System.out.println(
//                    "\nNo portfolios available.");
//
//            return;
//        }
//
//        List<Portfolio> inactivePortfolios =
//                portfolios.stream()
//                        .filter(portfolio ->
//                                portfolio.getLastActivityDate()
//                                        .isBefore(
//                                                LocalDate.now()
//                                                        .minusMonths(6)))
//                        .toList();
//
//        System.out.println(
//                "\n===== INACTIVE PORTFOLIOS =====");
//
//        if (inactivePortfolios.isEmpty()) {
//
//            System.out.println(
//                    "No inactive portfolios found.");
//
//            return;
//        }
//
//        inactivePortfolios.forEach(portfolio -> {
//
//            System.out.println(
//                    "Portfolio ID : "
//                            + portfolio.getPortfolioId());
//
//            System.out.println(
//                    "Last Activity Date : "
//                            + portfolio.getLastActivityDate());
//
//            System.out.println(
//                    "--------------------------------");
//        });
//    }
//
//
//    // =========================================================
//    // 6. PROFIT / LOSS PARTITION
//    // =========================================================
//
//    public void displayProfitLossPortfolios(
//            List<Portfolio> portfolios) {
//
//        if (portfolios == null || portfolios.isEmpty()) {
//
//            System.out.println(
//                    "\nNo portfolios available.");
//
//            return;
//        }
//
//        Map<Boolean, List<Portfolio>> result =
//                portfolios.stream()
//                        .collect(
//                                Collectors.partitioningBy(
//                                        portfolio -> {
//
//                                            double currentValue =
//                                                    portfolio.getHoldings()
//                                                            .stream()
//                                                            .mapToDouble(
//                                                                    holding ->
//                                                                            holding.getUnitsOwned()
//                                                                                    * holding.getMutualFund()
//                                                                                    .getNav())
//                                                            .sum();
//
//                                            double invested =
//                                                    portfolio.getHoldings()
//                                                            .stream()
//                                                            .mapToDouble(
//                                                                    Holding::getInvestedAmount)
//                                                            .sum();
//
//                                            return currentValue > invested;
//                                        }
//                                )
//                        );
//
//        System.out.println(
//                "\n===== PROFIT PORTFOLIOS =====");
//
//        List<Portfolio> profitPortfolios =
//                result.get(true);
//
//        if (profitPortfolios.isEmpty()) {
//
//            System.out.println(
//                    "No portfolios currently in profit.");
//
//        } else {
//
//            profitPortfolios.forEach(portfolio ->
//                    System.out.println(
//                            "Portfolio ID : "
//                                    + portfolio.getPortfolioId()));
//        }
//
//
//        System.out.println(
//                "\n===== LOSS / NO PROFIT PORTFOLIOS =====");
//
//        List<Portfolio> lossPortfolios =
//                result.get(false);
//
//        if (lossPortfolios.isEmpty()) {
//
//            System.out.println(
//                    "No portfolios currently in loss.");
//
//        } else {
//
//            lossPortfolios.forEach(portfolio ->
//                    System.out.println(
//                            "Portfolio ID : "
//                                    + portfolio.getPortfolioId()));
//        }
//    }
//
//
//    // =========================================================
//    // 7. PORTFOLIOS SORTED BY VALUE
//    // =========================================================
//
//    public void displayPortfoliosSortedByValue(
//            List<Portfolio> portfolios,
//            List<Holding> holdings) {
//
//        if (holdings == null || holdings.isEmpty()) {
//
//            System.out.println(
//                    "\nNo portfolio holdings available.");
//
//            return;
//        }
//
//        Map<String, Double> portfolioValues =
//                holdings.stream()
//                        .collect(
//                                Collectors.groupingBy(
//                                        h -> h.getPortfolio()
//                                                .getPortfolioId(),
//
//                                        Collectors.summingDouble(
//                                                h -> h.getUnitsOwned()
//                                                        * h.getMutualFund()
//                                                        .getNav()
//                                        )
//                                )
//                        );
//
//        System.out.println(
//                "\n===== PORTFOLIOS SORTED BY VALUE =====");
//
//        portfolioValues.entrySet()
//                .stream()
//                .sorted(
//                        Map.Entry
//                                .<String, Double>comparingByValue()
//                                .reversed()
//                )
//                .forEach(entry -> {
//
//                    System.out.println(
//                            "Portfolio ID : "
//                                    + entry.getKey());
//
//                    System.out.println(
//                            "Portfolio Value : ₹"
//                                    + String.format(
//                                    "%.2f",
//                                    entry.getValue()));
//
//                    System.out.println(
//                            "--------------------------------");
//                });
//    }
//}