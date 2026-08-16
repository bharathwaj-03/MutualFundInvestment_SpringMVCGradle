//package com.crimsonlogic.mutualfundinvestmentspringmvc.services.redemption;
//
//import com.crimsonlogic.mutualfundinvestmentspringmvc.exception.InsufficientUnitsException;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.mybatisservices.HoldingDBService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.mybatisservices.MutualFundDBService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.mybatisservices.RedemptionDBService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.Redemption;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio.PortfolioService;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Scanner;
//import java.util.UUID;
//
//public class RedemptionService {
//
//    private final RedemptionDBService redemptionDBService =
//            new RedemptionDBService();
//
//    private final MutualFundDBService mutualFundDBService =
//            new MutualFundDBService();
//
//    private final HoldingDBService holdingDBService =
//            new HoldingDBService();
//
//    private final PortfolioService portfolioService;
//
//    private final Scanner sc = new Scanner(System.in);
//
//
//    public RedemptionService(
//            PortfolioService portfolioService) {
//
//        this.portfolioService = portfolioService;
//    }
//
//
//    // =========================================================
//    // REDEEM UNITS
//    // =========================================================
//
//    public void redeemUnits(String investorId) {
//
//        Portfolio portfolio =
//                portfolioService.getPortfolio(investorId);
//
//        if (portfolio == null) {
//
//            System.out.println("Portfolio not found.");
//
//            return;
//        }
//
//
//        List<Holding> holdings =
//                holdingDBService.getHoldingsByPortfolio(
//                        portfolio.getPortfolioId());
//
//
//        if (holdings == null || holdings.isEmpty()) {
//
//            System.out.println("No Holdings Available.");
//
//            return;
//        }
//
//
//        System.out.println(
//                "\n========== YOUR HOLDINGS ==========");
//
//
//        holdings.forEach(h -> {
//
//            System.out.println("--------------------------------");
//
//            System.out.println(
//                    "Holding ID : "
//                            + h.getHoldingId());
//
//            System.out.println(
//                    "Fund Name : "
//                            + h.getMutualFund().getFundName());
//
//            System.out.println(
//                    "Fund Category : "
//                            + h.getMutualFund().getFundCategory());
//
//            System.out.println(
//                    "Units Owned : "
//                            + h.getUnitsOwned());
//
//            System.out.println(
//                    "Average NAV : "
//                            + h.getAverageNav());
//        });
//
//
//        // =====================================================
//        // SELECT HOLDING
//        // =====================================================
//
//        System.out.print("\nEnter Holding ID : ");
//
//        String holdingId = sc.next();
//
//
//        Holding holding =
//                portfolioService.getHolding(
//                        investorId,
//                        holdingId);
//
//
//        if (holding == null) {
//
//            System.out.println(
//                    "Holding Not Found.");
//
//            return;
//        }
//
//
//        // =====================================================
//        // ENTER UNITS
//        // =====================================================
//
//        System.out.print(
//                "Enter Units To Redeem : ");
//
//        double units = sc.nextDouble();
//
//
//        try {
//
//            if (units <= 0) {
//
//                throw new InsufficientUnitsException(
//                        "Units to redeem must be greater than zero.");
//            }
//
//
//            if (units > holding.getUnitsOwned()) {
//
//                throw new InsufficientUnitsException(
//                        "Units to redeem cannot be greater than units owned.");
//            }
//
//        } catch (InsufficientUnitsException e) {
//
//            System.out.println(
//                    e.getMessage());
//
//            return;
//        }
//
//
//        // =====================================================
//        // FETCH CURRENT MUTUAL FUND FROM DATABASE
//        // =====================================================
//
//        MutualFund currentFund =
//                mutualFundDBService.getFundById(
//                        holding.getMutualFund().getFundId());
//
//
//        if (currentFund == null) {
//
//            System.out.println(
//                    "Mutual fund not found in database.");
//
//            return;
//        }
//
//
//        // =====================================================
//        // CURRENT NAV COMES FROM DATABASE
//        // =====================================================
//
//        double currentNav =
//                currentFund.getNav();
//
//
//        // =====================================================
//        // CALCULATE REDEMPTION AMOUNT
//        // =====================================================
//
//        double redemptionAmount =
//                units * currentNav;
//
//
//        // =====================================================
//        // UPDATE HOLDING
//        // =====================================================
//
//        double remainingUnits =
//                holding.getUnitsOwned() - units;
//
//
//        if (remainingUnits <= 0) {
//
//            holdingDBService.deleteHolding(
//                    holding.getHoldingId());
//
//        } else {
//
//            holding.setUnitsOwned(
//                    remainingUnits);
//
//            holdingDBService.updateHolding(
//                    holding);
//        }
//
//
//        // =====================================================
//        // UPDATE PORTFOLIO ACTIVITY DATE
//        // =====================================================
//
//        portfolio.setLastActivityDate(
//                LocalDate.now());
//
//
//        portfolioService.updatePortfolioDate(
//                portfolio);
//
//
//        // =====================================================
//        // CREATE REDEMPTION
//        // =====================================================
//
//        Redemption redemption =
//                new Redemption();
//
//
//        redemption.setRedemptionId(
//                generateRedemptionId());
//
//
//        redemption.setInvestorId(
//                investorId);
//
//
//        redemption.setFundId(
//                currentFund.getFundId());
//
//
//        redemption.setUnitsRedeemed(
//                units);
//
//
//        redemption.setAmount(
//                redemptionAmount);
//
//
//        redemption.setActivityDate(
//                LocalDate.now());
//
//
//        // =====================================================
//        // STORE REDEMPTION IN DATABASE
//        // =====================================================
//
//        redemptionDBService.insertRedemption(
//                redemption);
//
//
//        // =====================================================
//        // DISPLAY RESULT
//        // =====================================================
//
//        System.out.println();
//
//        System.out.println(
//                "========== REDEMPTION SUCCESSFUL ==========");
//
//        System.out.println(
//                "Redemption ID : "
//                        + redemption.getRedemptionId());
//
//        System.out.println(
//                "Fund Name : "
//                        + currentFund.getFundName());
//
//        System.out.println(
//                "Units Redeemed : "
//                        + units);
//
//        System.out.println(
//                "Current NAV : ₹"
//                        + currentNav);
//
//        System.out.println(
//                "Redemption Amount : ₹"
//                        + redemptionAmount);
//
//        System.out.println(
//                "Remaining Units : "
//                        + remainingUnits);
//
//        System.out.println();
//    }
//
//
//    // =========================================================
//    // GENERATE REDEMPTION ID
//    // =========================================================
//
//    private String generateRedemptionId() {
//
//        return "RED"
//                + UUID.randomUUID()
//                .toString()
//                .replace("-", "")
//                .substring(0, 17);
//    }
//
//
//    // =========================================================
//    // SEARCH REDEMPTION
//    // =========================================================
//
//    public Redemption searchRedemption(
//            String redemptionId) {
//
//        return redemptionDBService
//                .getRedemptionById(
//                        redemptionId);
//    }
//
//
//    // =========================================================
//    // VIEW USER REDEMPTIONS
//    // =========================================================
//
//    public void viewMyRedemptions(
//            String investorId) {
//
//        List<Redemption> redemptions =
//                redemptionDBService
//                        .getRedemptionsByUser(
//                                investorId);
//
//
//        if (redemptions == null
//                || redemptions.isEmpty()) {
//
//            System.out.println(
//                    "No redemptions found.");
//
//            return;
//        }
//
//
//        System.out.println(
//                "\n========== MY REDEMPTIONS ==========");
//
//
//        redemptions.forEach(
//                System.out::println);
//    }
//
//
//    // =========================================================
//    // VIEW ALL REDEMPTIONS
//    // ADMIN
//    // =========================================================
//
//    public void viewAllRedemptions() {
//
//        List<Redemption> redemptions =
//                redemptionDBService
//                        .getAllRedemptions();
//
//
//        if (redemptions == null
//                || redemptions.isEmpty()) {
//
//            System.out.println(
//                    "No redemptions found.");
//
//            return;
//        }
//
//
//        System.out.println(
//                "\n========== ALL REDEMPTIONS ==========");
//
//
//        redemptions.forEach(
//                System.out::println);
//    }
//}