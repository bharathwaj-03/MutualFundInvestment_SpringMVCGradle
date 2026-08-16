//package com.crimsonlogic.mutualfundinvestmentspringmvc.services.report;
//
//import com.crimsonlogic.mutualfundinvestmentspringmvc.mybatisservices.HoldingDBService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.mybatisservices.InvestmentDBService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.services.payment.PaymentService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio.PortfolioService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.Investment;
//
//
//import java.util.List;
//
//import java.util.*;
//
//public class ReportService {
//
//    private final PortfolioService portfolioService;
//    private final PaymentService paymentService;
//    private final HoldingDBService holdingDBService =
//            new HoldingDBService();
//    private final InvestmentDBService investmentDBService =
//            new InvestmentDBService();
//
//    public ReportService(PortfolioService portfolioService,
//                         PaymentService paymentService) {
//
//        this.portfolioService = portfolioService;
//        this.paymentService = paymentService;
//
//
//    }
//
//    public void viewPortfolioPerformance(String investorId) {
//
//        Portfolio portfolio =
//                portfolioService.getPortfolio(investorId);
//
//        if (portfolio == null) {
//
//            System.out.println("Portfolio not found.");
//            return;
//        }
//
//        List<Holding> holdings =
//                holdingDBService.getHoldingsByPortfolio(
//                        portfolio.getPortfolioId());
//
//        System.out.println("\n========== PORTFOLIO PERFORMANCE ==========");
//
//        if (holdings.isEmpty()) {
//
//            System.out.println("No holdings available.");
//            return;
//        }
//
//        double totalValue = 0;
//
//        for (Holding holding : holdings) {
//
//            MutualFund fund =
//                    holding.getMutualFund();
//
//            double currentValue =
//                    holding.getUnitsOwned()
//                            * fund.getNav();
//
//            totalValue += currentValue;
//
//            System.out.println("--------------------------------");
//            System.out.println("Holding ID    : "
//                    + holding.getHoldingId());
//
//            System.out.println("Fund          : "
//                    + fund.getFundName());
//
//            System.out.println("Category      : "
//                    + fund.getFundCategory());
//
//            System.out.println("Units         : "
//                    + String.format("%.4f",
//                    holding.getUnitsOwned()));
//
//            System.out.println("Current NAV   : ₹"
//                    + String.format("%.2f",
//                    fund.getNav()));
//
//            System.out.println("Current Value : ₹"
//                    + String.format("%.2f",
//                    currentValue));
//        }
//
//        System.out.println("--------------------------------");
//        System.out.println("Total Portfolio Value : ₹"
//                + String.format("%.2f", totalValue));
//    }
//
//    // ===============================
//    // Capital Gain Report
//    // ===============================
//
//    public void generateCapitalGainReport(String investorId) {
//
//        Portfolio portfolio =
//                portfolioService.getPortfolio(investorId);
//
//        if (portfolio == null) {
//
//            System.out.println("Portfolio not found.");
//            return;
//        }
//
//        List<Holding> holdings =
//                holdingDBService.getHoldingsByPortfolio(
//                        portfolio.getPortfolioId());
//
//        System.out.println("\n========== CAPITAL GAIN REPORT ==========");
//
//        if (holdings.isEmpty()) {
//
//            System.out.println("No holdings available.");
//            return;
//        }
//
//        double totalGain = 0;
//
//        for (Holding holding : holdings) {
//
//            MutualFund fund =
//                    holding.getMutualFund();
//
//            double gain =
//                    (fund.getNav()
//                            - holding.getAverageNav())
//                            * holding.getUnitsOwned();
//
//            totalGain += gain;
//
//            System.out.println("--------------------------------");
//
//            System.out.println("Holding ID    : "
//                    + holding.getHoldingId());
//
//            System.out.println("Fund          : "
//                    + fund.getFundName());
//
//            System.out.println("Units         : "
//                    + String.format("%.4f",
//                    holding.getUnitsOwned()));
//
//            System.out.println("Purchase NAV  : ₹"
//                    + String.format("%.2f",
//                    holding.getAverageNav()));
//
//            System.out.println("Current NAV   : ₹"
//                    + String.format("%.2f",
//                    fund.getNav()));
//
//            System.out.println("Capital Gain  : ₹"
//                    + String.format("%.2f", gain));
//        }
//
//        System.out.println("--------------------------------");
//        System.out.println("Total Capital Gain : ₹"
//                + String.format("%.2f", totalGain));
//    }
//
//    // ===============================
//    // Investment Report
//    // ===============================
//
//    public void generateInvestmentReport(String investorId) {
//
//        Portfolio portfolio =
//                portfolioService.getPortfolio(investorId);
//
//        if (portfolio == null) {
//
//            System.out.println("Portfolio not found.");
//            return;
//        }
//
//        List<Investment> investments =
//                investmentDBService
//                        .getInvestmentsByUser(investorId);
//
//        System.out.println("\n========== INVESTMENT REPORT ==========");
//
//        System.out.println("Investor ID : "
//                + investorId);
//
//        System.out.println("Portfolio ID : "
//                + portfolio.getPortfolioId());
//
//        if (investments.isEmpty()) {
//
//            System.out.println("\nNo investments found.");
//            return;
//        }
//
//        System.out.println("Number of Investments : "
//                + investments.size());
//
//        for (Investment investment : investments) {
//
//            MutualFund fund =
//                    investment.getMutualFund();
//
//            System.out.println("\n--------------------------------");
//
//            System.out.println("Investment ID : "
//                    + investment.getInvestmentId());
//
//            System.out.println("Fund          : "
//                    + fund.getFundName());
//
//            System.out.println("Category      : "
//                    + fund.getFundCategory());
//
//            System.out.println("Amount        : ₹"
//                    + String.format("%.2f",
//                    investment.getAmount()));
//
//            System.out.println("Units         : "
//                    + String.format("%.4f",
//                    investment.getUnitsPurchased()));
//
//            System.out.println("Investment Period : "
//                    + investment.getInvestmentYears()
//                    + " Years");
//
//            System.out.println("Activity Date : "
//                    + investment.getActivityDate());
//
//            System.out.println("Annual Gain   : ₹"
//                    + String.format("%.2f",
//                    investment.getAssetGainPerYear()));
//
//            System.out.println("Total Gain    : ₹"
//                    + String.format("%.2f",
//                    investment.getAssetGainTotalInvestedYears()));
//        }
//
//        System.out.println("--------------------------------");
//
//        System.out.println("Current Portfolio Value : ₹"
//                + String.format("%.2f",
//                portfolioService.calculatePortfolioValue(
//                        investorId)));
//    }
//
//
//
//
//    public void generatePortfolioReport(Portfolio portfolio) {
//
//        System.out.println("\n===== PORTFOLIO REPORT =====");
//
//        System.out.println(
//                "Portfolio Id : "
//                        + portfolio.getPortfolioId()
//        );
//
//        portfolio.getHoldings()
//                .forEach(System.out::println);
//
//        double portfolioValue =
//                calculatePortfolioValue(portfolio);
//
//        System.out.println(
//                "Current Portfolio Value : "
//                        + portfolioValue
//        );
//    }
//
//    public double calculatePortfolioValue(
//            Portfolio portfolio) {
//
//        return portfolio.getHoldings()
//                .stream()
//                .mapToDouble(holding ->
//                        holding.getUnitsOwned()
//                                * holding.getMutualFund()
//                                .getNav())
//                .sum();
//    }
//
//    public double calculateProfitLoss(
//            Portfolio portfolio) {
//
//        double currentValue =
//                calculatePortfolioValue(
//                        portfolio);
//
//        double totalInvested =
//                portfolio.getHoldings()
//                        .stream()
//                        .mapToDouble(
//                                Holding::getInvestedAmount)
//                        .sum();
//
//        return currentValue - totalInvested;
//    }
//
//    public void generateCapitalGainReport(
//            Portfolio portfolio) {
//
//        System.out.println(
//                "\n===== CAPITAL GAIN REPORT =====");
//
//        double gain =
//                calculateProfitLoss(
//                        portfolio);
//
//        System.out.println(
//                "Capital Gain/Loss : "
//                        + gain);
//    }
//
//    public void displayTopHolding(
//            Portfolio portfolio) {
//
//        Optional<Holding> holdingOptional =
//                portfolio.getHoldings()
//                        .stream()
//                        .max(
//                                Comparator.comparingDouble(
//                                        Holding::getInvestedAmount
//                                )
//                        );
//
//        holdingOptional.ifPresent(holding -> {
//
//            System.out.println(
//                    "\nTop Holding : ");
//
//            System.out.println(
//                    holding);
//        });
//    }
//
//    public void displayHighestNAVFund(
//            List<Holding> holdings) {
//
//        Optional<Holding> holdingOptional =
//                holdings.stream()
//                        .max(
//                                Comparator.comparingDouble(
//                                        h -> h.getMutualFund()
//                                                .getNav()
//                                )
//                        );
//
//        holdingOptional.ifPresent(holding -> {
//
//            System.out.println(
//                    "\nHighest NAV Fund");
//
//            System.out.println(
//                    holding.getMutualFund()
//            );
//        });
//    }
//
//    public void displayLowestNAVFund(
//            List<Holding> holdings) {
//
//        Optional<Holding> holdingOptional =
//                holdings.stream()
//                        .min(
//                                Comparator.comparingDouble(
//                                        h -> h.getMutualFund()
//                                                .getNav()
//                                )
//                        );
//
//        holdingOptional.ifPresent(holding -> {
//
//            System.out.println(
//                    "\nLowest NAV Fund");
//
//            System.out.println(
//                    holding.getMutualFund()
//            );
//        });
//    }
//
//    public void generateInvestorReport(
//            Investor investor,
//            Portfolio portfolio) {
//
//        System.out.println(
//                "\n===== INVESTOR REPORT =====");
//
//        System.out.println(
//                "Investor Id : "
//                        + investor.getUserId());
//
//        System.out.println(
//                "Investor Name : "
//                        + investor.getName());
//
//        System.out.println(
//                "Email : "
//                        + investor.getEmail());
//
//        System.out.println(
//                "Portfolio Value : "
//                        + calculatePortfolioValue(
//                        portfolio));
//
//        System.out.println(
//                "Profit/Loss : "
//                        + calculateProfitLoss(
//                        portfolio));
//    }
//
//    public long getTotalFundsInvested(
//            Portfolio portfolio) {
//
//        return portfolio.getHoldings()
//                .stream()
//                .count();
//    }
//
//    public double getTotalInvestmentAmount(
//            Portfolio portfolio) {
//
//        return portfolio.getHoldings()
//                .stream()
//                .mapToDouble(
//                        Holding::getInvestedAmount)
//                .sum();
//    }
//
//    public void displayPortfolioSummary(
//            Portfolio portfolio) {
//
//        System.out.println(
//                "\n===== PORTFOLIO SUMMARY =====");
//
//        System.out.println(
//                "Total Funds : "
//                        + getTotalFundsInvested(
//                        portfolio));
//
//        System.out.println(
//                "Invested Amount : "
//                        + getTotalInvestmentAmount(
//                        portfolio));
//
//        System.out.println(
//                "Current Value : "
//                        + calculatePortfolioValue(
//                        portfolio));
//
//        System.out.println(
//                "Profit/Loss : "
//                        + calculateProfitLoss(
//                        portfolio));
//    }
//    public void generateRiskProfileReport(
//            List<Investor> investors) {
//
//        System.out.println(
//                "\n===== RISK PROFILE REPORT =====");
//
//        if (investors == null || investors.isEmpty()) {
//
//            System.out.println(
//                    "No investors available."
//            );
//
//            return;
//        }
//
//        investors.forEach(investor -> {
//
//            System.out.println(
//                    "\nInvestor Id : "
//                            + investor.getUserId());
//
//            System.out.println(
//                    "Investor Name : "
//                            + investor.getName());
//
//            System.out.println(
//                    "Risk Profile : "
//                            + investor.getRiskProfile());
//        });
//    }
//
//
//}
//
//
//
