//package com.crimsonlogic.mutualfundinvestmentspringmvc.services.streamservices;
//
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//public class InvestorStreamService {
//
//// 1. Get Active Investors
//
//    public void getActiveInvestors(
//            List<Investor> investors) {
//
//        System.out.println("\n========== ACTIVE INVESTORS ==========");
//
//        List<Investor> activeInvestors =
//                investors.stream()
//                        .filter(Investor::isActive)
//                        .toList();
//
//        if (activeInvestors.isEmpty()) {
//            System.out.println("No active investors found.");
//            return;
//        }
//
//        activeInvestors.forEach(investor -> {
//
//            System.out.println("--------------------------------");
//
//            System.out.println(
//                    "Investor ID : "
//                            + investor.getUserId());
//
//            System.out.println(
//                    "Name        : "
//                            + investor.getName());
//
//            System.out.println(
//                    "Email       : "
//                            + investor.getEmail());
//
//            System.out.println(
//                    "Risk Profile: "
//                            + investor.getRiskProfile());
//
//            System.out.println(
//                    "Active      : "
//                            + investor.isActive());
//        });
//
//        System.out.println("--------------------------------");
//    }
//
//
//// 2. Group by Risk Profile
//
//    public void groupInvestorsByRiskProfile(
//            List<Investor> investors) {
//
//        System.out.println(
//                "\n========== INVESTORS BY RISK PROFILE ==========");
//
//        Map<String, List<Investor>> grouped =
//                investors.stream()
//                        .collect(
//                                Collectors.groupingBy(
//                                        Investor::getRiskProfile
//                                )
//                        );
//
//        grouped.forEach((riskProfile, list) -> {
//
//            System.out.println(
//                    "\nRisk Profile : " + riskProfile);
//
//            list.forEach(investor -> {
//
//                System.out.println("--------------------------------");
//
//                System.out.println(
//                        "Investor ID : "
//                                + investor.getUserId());
//
//                System.out.println(
//                        "Name        : "
//                                + investor.getName());
//
//                System.out.println(
//                        "Email       : "
//                                + investor.getEmail());
//            });
//        });
//    }
//
//
//// 3. Find Investor by ID
//
//    public void findInvestorById(
//            List<Investor> investors,
//            String investorId) {
//
//        System.out.println(
//                "\n========== FIND INVESTOR ==========");
//
//        investors.stream()
//                .filter(investor ->
//                        investor.getUserId()
//                                .equals(investorId))
//                .findFirst()
//                .ifPresentOrElse(
//
//                        investor -> {
//
//                            System.out.println(
//                                    "Investor ID : "
//                                            + investor.getUserId());
//
//                            System.out.println(
//                                    "Name        : "
//                                            + investor.getName());
//
//                            System.out.println(
//                                    "Email       : "
//                                            + investor.getEmail());
//
//                            System.out.println(
//                                    "Phone       : "
//                                            + investor.getPhoneNumber());
//
//                            System.out.println(
//                                    "PAN         : "
//                                            + investor.getPanNumber());
//
//                            System.out.println(
//                                    "Risk Profile: "
//                                            + investor.getRiskProfile());
//
//                            System.out.println(
//                                    "Active      : "
//                                            + investor.isActive());
//                        },
//
//                        () -> System.out.println(
//                                "Investor not found.")
//                );
//    }
//    public void displayInvestorsSortedByNameComparator(
//            List<Investor> investors) {
//
//        System.out.println(
//                "\n===== INVESTORS SORTED BY NAME (COMPARATOR) =====");
//
//        investors.stream()
//                .sorted((i1, i2) ->
//                        i1.getName()
//                                .compareToIgnoreCase(
//                                        i2.getName()))
//                .forEach(i ->
//                        System.out.println(
//                                i.getName()));
//    }
//
//    public void displayInvestorsSortedComparable(
//            List<Investor> investors) {
//
//        System.out.println(
//                "\n===== INVESTORS SORTED (COMPARABLE) =====");
//
//        investors.stream()
//                .sorted()
//                .forEach(i ->
//                        System.out.println(
//                                i.getName()));
//    }
//}