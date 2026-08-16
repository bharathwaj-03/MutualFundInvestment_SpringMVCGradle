package com.crimsonlogic.mutualfundinvestmentspringmvc.services.report;//package com.crimsonlogic.mutualfundinvestmentspringmvc.services.report;
//
//import com.crimsonlogic.mutualfundinvestmentspringmvc.comparators.FundNameComparator;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.comparators.PortfolioValueComparator;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.comparators.TransactionAmountComparator;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.comparators.TransactionDateComparator;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.comparators.InvestorNameComparator;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.SIP;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.services.investor.InvestorService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.services.mutualfund.MutualFundService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio.PortfolioService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.services.sip.SIPService;
//import com.crimsonlogic.mutualfundinvestmentspringmvc.services.transaction.TransactionService;
//
//import java.time.LocalDate;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class ReportStreamService {
//    InvestorService investorService;
//    MutualFundService mutualFundService;
//    PortfolioService portfolioService;
//    TransactionService transactionService;
//    SIPService sipService;
//
//    public ReportStreamService(InvestorService investorService, MutualFundService mutualFundService, PortfolioService portfolioService, TransactionService transactionService, SIPService sipService) {
//        this.investorService = investorService;
//        this.mutualFundService = mutualFundService;
//        this.portfolioService = portfolioService;
//        this.transactionService = transactionService;
//        this.sipService = sipService;
//    }
//    Scanner sc= new Scanner(System.in);
//
//    //INVESTOR ANALYTICS
//
//    public List<Investor> getActiveInvestors(
//            List<Investor> investors) {
//
//        return investors.stream()
//                .filter(Investor::isActive)
//                .toList();
//    }
//
//    public Map<String, List<Investor>>
//    groupInvestorsByRiskProfile(
//            List<Investor> investors) {
//
//        return investors.stream()
//                .collect(
//                        Collectors.groupingBy(
//                                investor ->
//                                        investor.getRiskProfile()
//
//                        )
//                );
//    }
//
//
//
//    public Optional<Investor>
//    findInvestorById(
//            List<Investor> investors,
//            String investorId) {
//
//        return investors.stream()
//                .filter(investor ->
//                        investor.getUserId()
//                                == investorId)
//                .findFirst();
//    }
//
//
//    //SIP ANALYTICS
//
//    public List<SIP> getOpenSIPs(
//            List<SIP> sipList) {
//
//        return sipList.stream()
//                .filter(sip ->
//                        "ACTIVE".equalsIgnoreCase(
//                                sip.getSipStatus()))
//                .toList();
//    }
//
//    public double calculateTotalSIPInvestments(
//            List<SIP> sipList) {
//
//        return sipList.stream()
//                .mapToDouble(SIP::getMonthlyAmount)
//                .sum();
//    }
//
//    public List<SIP>
//    getOverdueSIPs(
//            List<SIP> sipList) {
//
//        return sipList.stream()
//                .filter(sip ->
//                        sip.getNextInstallmentDate()
//                                .isBefore(LocalDate.now()))
//                .toList();
//    }
//
//    public boolean areAllSIPsActive(
//            List<SIP> sipList) {
//
//        return sipList.stream()
//                .allMatch(sip ->
//                        "ACTIVE".equalsIgnoreCase(
//                                sip.getSipStatus()
//                        ));
//    }
//
//
//    //MUTUAL FUNDS ANALYTICS
//
//    public List<MutualFund> filterFundsByCategory(
//            List<MutualFund> funds,
//            String category) {
//
//        return funds.stream()
//                .filter(fund ->
//                        fund.getFundCategory()
//                                .equalsIgnoreCase(category))
//                .toList();
//    }
//
//    public List<MutualFund> getHighRiskFunds(
//            List<MutualFund> funds) {
//
//        return funds.stream()
//                .filter(fund ->
//                        "HIGH".equalsIgnoreCase(
//                                fund.getRiskLevel()))
//                .toList();
//    }
//
//    public List<MutualFund> sortFundsByNAV(
//            List<MutualFund> funds) {
//
//        return funds.stream()
//                .sorted(
//                        Comparator.comparingDouble(
//                                MutualFund::getNav))
//                .toList();
//    }
//
//    public List<MutualFund> getTop5PerformingFunds(
//            List<MutualFund> funds) {
//
//        return funds.stream()
//                .sorted(
//                        Comparator.comparingDouble(
//                                        MutualFund::getNav)
//                                .reversed())
//                .limit(5)
//                .toList();
//    }
//
//    public Optional<MutualFund> getLowestPerformingFund(
//            List<MutualFund> funds) {
//
//        return funds.stream()
//                .min(
//                        Comparator.comparingDouble(
//                                MutualFund::getNav));
//    }
//
//    public Optional<MutualFund> getHighestNAVFund(
//            List<MutualFund> funds) {
//
//        return funds.stream()
//                .max(
//                        Comparator.comparingDouble(
//                                MutualFund::getNav));
//    }
//    public Map<String, List<MutualFund>>
//    groupFundsByCategory(
//            List<MutualFund> funds) {
//
//        return funds.stream()
//                .collect(
//                        Collectors.groupingBy(
//                                MutualFund::getFundCategory
//                        )
//                );
//    }
////    public List<String>
////    getDistinctFundHouses(
////            List<MutualFund> funds) {
////
////        return funds.stream()
////                .map(MutualFund::getFundHouse)
////                .distinct()
////                .toList();
////    }
//
//    public List<String>
//    getDistinctCategories(
//            List<MutualFund> funds) {
//
//        return funds.stream()
//                .map(MutualFund::getFundCategory)
//                .distinct()
//                .toList();
//    }
//
//    public String joinFundNames(
//            List<MutualFund> funds) {
//
//        return funds.stream()
//                .map(MutualFund::getFundName)
//                .collect(
//                        Collectors.joining(", ")
//                );
//    }
//
//    public List<MutualFund>
//    getImmutableFundList(
//            List<MutualFund> funds) {
//
//        return funds.stream()
//                .collect(
//                        Collectors.toUnmodifiableList()
//                );
//    }
//
//    public Map<String, Double>
//    getCategoryWiseAUM(
//            List<Portfolio> portfolios) {
//
//        return portfolios.stream()
//                .flatMap(
//                        portfolio ->
//                                portfolio.getHoldings()
//                                        .stream()
//                )
//                .collect(
//                        Collectors.groupingBy(
//                                holding ->
//                                        holding.getMutualFund()
//                                                .getFundCategory(),
//                                Collectors.summingDouble(
//                                        holding ->
//                                                holding.getUnitsOwned()
//                                                        * holding.getMutualFund()
//                                                        .getNav()
//                                )
//                        )
//                );
//    }
//
//    //PORTFOLIO ANALYTICS
////    public List<Map.Entry<Investor, Portfolio>>
////    sortInvestorsByPortfolioValue(
////            Map<Investor, Portfolio> data) {
////
////        return data.entrySet()
////                .stream()
////                .sorted(
////                        Comparator.comparingDouble(
////                                entry -> entry.getValue()
////                                        .getHoldings()
////                                        .stream()
////                                        .mapToDouble(
////                                                holding ->
////                                                        holding.getUnitsOwned()
////                                                                * holding.getMutualFund()
////                                                                .getNav())
////                                        .sum()
////                        ).reversed()
////                )
////                .toList();
////    }
//    public double calculateTotalAUM(
//            List<Portfolio> portfolios) {
//
//        return portfolios.stream()
//                .flatMap(
//                        portfolio ->
//                                portfolio.getHoldings()
//                                        .stream())
//                .mapToDouble(
//                        holding ->
//                                holding.getUnitsOwned()
//                                        * holding.getMutualFund()
//                                        .getNav())
//                .sum();
//    }
//    public double calculateAveragePortfolioReturn(
//            List<Portfolio> portfolios) {
//
//        return portfolios.stream()
//                .mapToDouble(portfolio -> {
//
//                    double currentValue =
//                            portfolio.getHoldings()
//                                    .stream()
//                                    .mapToDouble(holding ->
//                                            holding.getUnitsOwned()
//                                                    * holding.getMutualFund()
//                                                    .getNav())
//                                    .sum();
//
//                    double investedAmount =
//                            portfolio.getHoldings()
//                                    .stream()
//                                    .mapToDouble(Holding::getInvestedAmount)
//                                    .sum();
//
//                    return currentValue - investedAmount;
//                })
//                .average()
//                .orElse(0.0);
//    }
////    public Map<String, Long>
////    countSIPsPerInvestor(
////            List<SIP> sipList) {
////
////        return sipList.stream()
////                .collect(
////                        Collectors.groupingBy(
////                                SIP::getInvestorId,
////                                Collectors.counting()
////                        )
////                );
////    }
////    public Map<String, Long>
////    countHoldingsPerPortfolio(
////            List<Portfolio> portfolios) {
////
////        return portfolios.stream()
////                .collect(
////                        Collectors.toMap(
////                                Portfolio::getPortfolioId,
////                                portfolio ->
////                                        (long) portfolio.getHoldings()
////                                                .size()
////                        )
////                );
////    }
////
////    public Optional<Portfolio>
////    getHighestPortfolioValue(
////            List<Portfolio> portfolios) {
////
////        return portfolios.stream()
////                .max(
////                        Comparator.comparingDouble(
////                                portfolio ->
////                                        portfolio.getHoldings()
////                                                .stream()
////                                                .mapToDouble(
////                                                        holding ->
////                                                                holding.getUnitsOwned()
////                                                                        * holding.getMutualFund()
////                                                                        .getNav()
////                                                )
////                                                .sum()
////                        )
////                );
////    }
////
////    public List<Portfolio>
////    getInactivePortfolios(
////            List<Portfolio> portfolios) {
////
////        return portfolios.stream()
////                .filter(portfolio ->
////                        portfolio.getLastActivityDate()
////                                .isBefore(
////                                        LocalDate.now()
////                                                .minusMonths(6)
////                                )
////                )
////                .toList();
////    }
////
////    public Map<Boolean, List<Portfolio>>
////    partitionProfitLossPortfolios(
////            List<Portfolio> portfolios) {
////
////        return portfolios.stream()
////                .collect(
////                        Collectors.partitioningBy(
////                                portfolio -> {
////
////                                    double currentValue =
////                                            portfolio.getHoldings()
////                                                    .stream()
////                                                    .mapToDouble(
////                                                            holding ->
////                                                                    holding.getUnitsOwned()
////                                                                            * holding.getMutualFund()
////                                                                            .getNav())
////                                                    .sum();
////
////                                    double investedAmount =
////                                            portfolio.getHoldings()
////                                                    .stream()
////                                                    .mapToDouble(
////                                                            Holding::getInvestedAmount)
////                                                    .sum();
////
////                                    return currentValue > investedAmount;
////                                }
////                        )
////                );
////    }
////
////
////
//////TRANSACTION ANALYTICS
////
////    public Map<String, List<Transaction>>
////    groupTransactionsByType(
////            List<Transaction> transactions) {
////
////        return transactions.stream()
////                .collect(
////                        Collectors.groupingBy(
////                                transaction ->
////                                        transaction.getClass()
////                                                .getSimpleName()
////                        )
////                );
////    }
////
////    public Optional<Transaction>
////    getLatestTransaction(
////            List<Transaction> transactions) {
////
////        return transactions.stream()
////                .max(
////                        Comparator.comparing(
////                                Transaction::getTransactionDateTime
////                        )
////                );
////    }
////
////    public Optional<Transaction>
////    getEarliestInvestment(
////            List<Transaction> transactions) {
////
////        return transactions.stream()
////                .min(
////                        Comparator.comparing(
////                                Transaction::getTransactionDateTime
////                        )
////                );
////    }
////
////    public boolean hasFailedTransactions(
////            List<Transaction> transactions) {
////
////        return transactions.stream()
////                .anyMatch(transaction ->
////                        "FAILED".equalsIgnoreCase(
////                                transaction.getTransactionStatus()
////                        ));
////    }
////
////    //NAV ANALYTICS
////
////
////
////    public void displayActiveInvestors() {
////
////        List<Investor> activeInvestors =
////                getActiveInvestors(
////                        new ArrayList<>(
////                                investorService.getInvestorMap().values()));
////
////        if(activeInvestors.isEmpty()){
////
////            System.out.println("No Active Investors Found.");
////            return;
////        }
////
////        activeInvestors.forEach(System.out::println);
////    }
////
////    public void displayInvestorsByRiskProfile() {
////
////        Map<String,List<Investor>> groupedInvestors =
////                groupInvestorsByRiskProfile(
////                        new ArrayList<>(
////                                investorService.getInvestorMap().values()));
////
////        groupedInvestors.forEach((risk,list)->{
////
////            System.out.println("\nRisk Profile : " + risk);
////
////            list.forEach(System.out::println);
////        });
////    }
////
////    public void displayInvestorById() {
////
////        System.out.print("Enter Investor ID : ");
////
////        String investorId = sc.next();
////
////        findInvestorById(
////                new ArrayList<>(
////                        investorService.getInvestorMap().values()),
////                investorId)
////
////                .ifPresentOrElse(
////
////                        System.out::println,
////
////                        ()-> System.out.println("Investor Not Found.")
////                );
////    }
////    public void displayOpenSIPs() {
////
////        List<SIP> sipList =
////                getOpenSIPs(
////                        sipService.getSipList());
////
////        if(sipList.isEmpty()){
////
////            System.out.println("No Active SIPs.");
////            return;
////        }
////
////        sipList.forEach(System.out::println);
////    }
////    public void displayTotalSIPInvestment() {
////
////        double total =
////                calculateTotalSIPInvestments(
////                        sipService.getSipList());
////
////        System.out.println("Total Monthly SIP Investment : ₹" + total);
////    }
////    public void displayOverdueSIPs() {
////
////        List<SIP> overdue =
////                getOverdueSIPs(
////                        sipService.getSipList());
////
////        if(overdue.isEmpty()){
////
////            System.out.println("No Overdue SIPs.");
////            return;
////        }
////
////        overdue.forEach(System.out::println);
////    }
////    public void displayAllSIPsActive() {
////
////        boolean status =
////                areAllSIPsActive(
////                        sipService.getSipList());
////
////        if(status){
////
////            System.out.println("All SIPs are ACTIVE.");
////
////        }else{
////
////            System.out.println("Some SIPs are INACTIVE.");
////        }
////    }
////    public void displayFundsByCategory() {
////
////        System.out.println("1. Debt");
////
////        System.out.println("2. Equity");
////
////        System.out.println("3. Hybrid");
////
////        System.out.print("Choice : ");
////
////        int choice = sc.nextInt();
////
////        String category = switch (choice) {
////
////            case 1 -> "Debt Fund";
////
////            case 2 -> "Equity Fund";
////
////            case 3 -> "Hybrid Fund";
////
////            default -> "";
////        };
////
////        List<MutualFund> funds =
////                filterFundsByCategory(
////                        mutualFundService.getAllFunds(),
////                        category);
////
////        if (funds.isEmpty()) {
////
////            System.out.println("No Funds Found.");
////            return;
////        }
////
////        funds.forEach(System.out::println);
////    }
////    public void displayHighRiskFunds() {
////
////        List<MutualFund> funds =
////                getHighRiskFunds(
////                        mutualFundService.getAllFunds());
////
////        if (funds.isEmpty()) {
////
////            System.out.println("No High Risk Funds.");
////            return;
////        }
////
////        funds.forEach(System.out::println);
////    }
////    public void displayFundsSortedByNAV() {
////
////        List<MutualFund> funds =
////                sortFundsByNAV(
////                        mutualFundService.getAllFunds());
////
////        if (funds.isEmpty()) {
////
////            System.out.println("No Funds Available.");
////            return;
////        }
////
////        funds.forEach(System.out::println);
////    }
////
////    // =========================================
////// 11. TOP 5 PERFORMING FUNDS
////// =========================================
////
////    public void displayTop5Funds() {
////
////        List<MutualFund> funds =
////                getTop5PerformingFunds(
////                        mutualFundService.getAllFunds());
////
////        if (funds.isEmpty()) {
////
////            System.out.println("No Funds Available.");
////            return;
////        }
////
////        funds.forEach(System.out::println);
////    }
////
////// =========================================
////// 12. LOWEST PERFORMING FUND
////// =========================================
////
////    public void displayLowestFund() {
////
////        getLowestPerformingFund(
////                mutualFundService.getAllFunds())
////                .ifPresentOrElse(
////
////                        System.out::println,
////
////                        () -> System.out.println(
////                                "No Funds Available.")
////                );
////    }
////
////// =========================================
////// 13. HIGHEST NAV FUND
////// =========================================
////
////    public void displayHighestNAVFund() {
////
////        getHighestNAVFund(
////                mutualFundService.getAllFunds())
////                .ifPresentOrElse(
////
////                        System.out::println,
////
////                        () -> System.out.println(
////                                "No Funds Available.")
////                );
////    }
////
////// =========================================
////// 14. GROUP FUNDS BY CATEGORY
////// =========================================
////
////    public void displayFundsGroupedByCategory() {
////
////        Map<String, List<MutualFund>> groupedFunds =
////                groupFundsByCategory(
////                        mutualFundService.getAllFunds());
////
////        if (groupedFunds.isEmpty()) {
////
////            System.out.println("No Funds Available.");
////            return;
////        }
////
////        groupedFunds.forEach((category, funds) -> {
////
////            System.out.println("\n" + category);
////
////            funds.forEach(System.out::println);
////        });
////    }
////    public void displayDistinctCategories() {
////
////        List<String> categories =
////                getDistinctCategories(
////                        mutualFundService.getAllFunds());
////
////        if (categories.isEmpty()) {
////
////            System.out.println("No Categories Available.");
////            return;
////        }
////
////        categories.forEach(System.out::println);
////    }
////    public void displayJoinedFundNames() {
////
////        String names =
////                joinFundNames(
////                        mutualFundService.getAllFunds());
////
////        if (names.isBlank()) {
////
////            System.out.println("No Funds Available.");
////            return;
////        }
////
////        System.out.println(names);
////    }
////    public void displayTotalAUM() {
////
////        double totalAUM =
////                calculateTotalAUM(
////                        new ArrayList<>(
////                                portfolioService
////                                        .getPortfolioMap()
////                                        .values()));
////
////        System.out.println("Total AUM : ₹" + totalAUM);
////    }
////    public void displayHoldingCount() {
////
////        Map<String, Long> holdingCount =
////                countHoldingsPerPortfolio(
////                        new ArrayList<>(
////                                portfolioService
////                                        .getPortfolioMap()
////                                        .values()));
////
////        if (holdingCount.isEmpty()) {
////
////            System.out.println("No Portfolios Available.");
////            return;
////        }
////
////        holdingCount.forEach(
////                (portfolioId, count) ->
////                        System.out.println(
////                                "Portfolio ID : "
////                                        + portfolioId
////                                        + " -> Holdings : "
////                                        + count));
////    }
////    public void displayHighestPortfolio() {
////
////        getHighestPortfolioValue(
////                new ArrayList<>(
////                        portfolioService
////                                .getPortfolioMap()
////                                .values()))
////                .ifPresentOrElse(
////
////                        System.out::println,
////
////                        () -> System.out.println(
////                                "No Portfolios Available.")
////                );
////    }
////    public void displayTransactionsByType() {
////
////        Map<String, List<Transaction>> groupedTransactions =
////                groupTransactionsByType(
////                        transactionService
////                                .getTransactionList());
////
////        if (groupedTransactions.isEmpty()) {
////
////            System.out.println("No Transactions Available.");
////            return;
////        }
////
////        groupedTransactions.forEach((type, list) -> {
////
////            System.out.println("\n" + type);
////
////            list.forEach(System.out::println);
////        });
////    }
////    public void displayLatestTransaction() {
////
////        getLatestTransaction(
////                transactionService
////                        .getTransactionList())
////                .ifPresentOrElse(
////
////                        System.out::println,
////
////                        () -> System.out.println(
////                                "No Transactions Available.")
////                );
////    }
////    public void displayEarliestTransaction() {
////
////        getEarliestInvestment(
////                transactionService
////                        .getTransactionList())
////                .ifPresentOrElse(
////
////                        System.out::println,
////
////                        () -> System.out.println(
////                                "No Transactions Available.")
////                );
////    }
////    public void displayFailedTransactions() {
////
////        boolean failed =
////                hasFailedTransactions(
////                        transactionService
////                                .getTransactionList());
////
////        if (failed) {
////
////            System.out.println(
////                    "There are Failed Transactions.");
////        }
////
////        else {
////
////            System.out.println(
////                    "No Failed Transactions.");
////        }
////    }
////
////
////
////    //COMPARATORS
////    public void displayFundsSortedByNameComparator() {
////
////        List<MutualFund> funds =
////                mutualFundService.getAllFunds();
////
////        funds.sort(new FundNameComparator());
////
////        funds.forEach(System.out::println);
////    }
////    public void displayFundsSortedComparable() {
////
////        List<MutualFund> funds =
////                mutualFundService.getAllFunds();
////
////        Collections.sort(funds);
////
////        funds.forEach(System.out::println);
////    }
////    public void displayInvestorsSortedByNameComparator() {
////
////        List<Investor> investors =
////                new ArrayList<>(
////                        investorService
////                                .getInvestorMap()
////                                .values());
////
////        investors.sort(new InvestorNameComparator());
////
////        investors.forEach(System.out::println);
////    }
////    public void displayInvestorsSortedComparable() {
////
////        List<Investor> investors =
////                new ArrayList<>(
////                        investorService
////                                .getInvestorMap()
////                                .values());
////
////        Collections.sort(investors);
////
////        investors.forEach(System.out::println);
////    }
////    public void displayPortfoliosSortedByValue() {
////
////        List<Portfolio> portfolios =
////                new ArrayList<>(
////                        portfolioService
////                                .getPortfolioMap()
////                                .values());
////
////        if (portfolios.isEmpty()) {
////
////            System.out.println("No Portfolios Available.");
////            return;
////        }
////
////        portfolios.sort(
////                new PortfolioValueComparator()
////                        .reversed());
////
////        portfolios.forEach(System.out::println);
////    }
////    public void displayTransactionsSortedByAmount() {
////
////        List<Transaction> transactions =
////                new ArrayList<>(
////                        transactionService
////                                .getTransactionList());
////
////        if (transactions.isEmpty()) {
////
////            System.out.println("No Transactions Available.");
////            return;
////        }
////
////        transactions.sort(
////                new TransactionAmountComparator());
////
////        transactions.forEach(System.out::println);
////    }
////    public void displayTransactionsSortedByDate() {
////
////        List<Transaction> transactions =
////                new ArrayList<>(
////                        transactionService
////                                .getTransactionList());
////
////        if (transactions.isEmpty()) {
////
////            System.out.println("No Transactions Available.");
////            return;
////        }
////
////        transactions.sort(
////                new TransactionDateComparator());
////
////        transactions.forEach(System.out::println);
////    }
//}
