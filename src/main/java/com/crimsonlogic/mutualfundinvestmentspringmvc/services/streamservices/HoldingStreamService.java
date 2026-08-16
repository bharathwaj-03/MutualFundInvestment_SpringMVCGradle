//package com.crimsonlogic.mutualfundinvestmentspringmvc.services.streamservices;
//
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class HoldingStreamService {
//
//    public void displayHoldingCount(
//            List<Holding> holdings) {
//
//        System.out.println(
//                "\n===== HOLDING COUNT PER PORTFOLIO =====");
//
//        Map<String, Long> count =
//                holdings.stream()
//                        .collect(Collectors.groupingBy(
//                                h -> h.getPortfolio()
//                                        .getPortfolioId(),
//                                Collectors.counting()
//                        ));
//
//        count.forEach((portfolioId, total) -> {
//
//            System.out.println(
//                    "Portfolio ID : " + portfolioId);
//
//            System.out.println(
//                    "Number of Holdings : " + total);
//
//            System.out.println("--------------------------------");
//        });
//    }
//}