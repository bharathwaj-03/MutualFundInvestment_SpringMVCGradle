//package com.crimsonlogic.mutualfundinvestmentspringmvc.services.streamservices;
//
//import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.SIP;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class SIPStreamService {
//
//    // =========================================================
//    // 1. GET ACTIVE SIPS
//    // =========================================================
//
//    public void displayActiveSIPs(
//            List<SIP> sipList) {
//
//        if (sipList == null || sipList.isEmpty()) {
//
//            System.out.println(
//                    "\nNo SIPs available.");
//
//            return;
//        }
//
//        List<SIP> activeSIPs =
//                sipList.stream()
//                        .filter(sip ->
//                                "ACTIVE".equalsIgnoreCase(
//                                        sip.getSipStatus()))
//                        .toList();
//
//        System.out.println(
//                "\n========== ACTIVE SIPS ==========");
//
//        if (activeSIPs.isEmpty()) {
//
//            System.out.println(
//                    "No active SIPs found.");
//
//            return;
//        }
//
//        activeSIPs.forEach(sip -> {
//
//            System.out.println("--------------------------------");
//
//            System.out.println(
//                    "SIP ID             : "
//                            + sip.getSipId());
//
//            System.out.println(
//                    "Investor ID        : "
//                            + sip.getInvestor().getUserId());
//
//            System.out.println(
//                    "Fund               : "
//                            + sip.getMutualFund().getFundName());
//
//            System.out.println(
//                    "Monthly Amount     : ₹"
//                            + String.format(
//                            "%.2f",
//                            sip.getMonthlyAmount()));
//
//            System.out.println(
//                    "Start Date         : "
//                            + sip.getStartDate());
//
//            System.out.println(
//                    "Next Installment   : "
//                            + sip.getNextInstallmentDate());
//
//            System.out.println(
//                    "Status             : "
//                            + sip.getSipStatus());
//        });
//
//        System.out.println("--------------------------------");
//    }
//
//
//    // =========================================================
//    // 2. TOTAL SIP INVESTMENT
//    // =========================================================
//
//    public void displayTotalSIPInvestments(
//            List<SIP> sipList) {
//
//        if (sipList == null || sipList.isEmpty()) {
//
//            System.out.println(
//                    "\nNo SIPs available.");
//
//            return;
//        }
//
//        double totalInvestment =
//                sipList.stream()
//                        .mapToDouble(
//                                SIP::getMonthlyAmount)
//                        .sum();
//
//        System.out.println(
//                "\n===== TOTAL SIP INVESTMENT =====");
//
//        System.out.println(
//                "Total Monthly SIP Investment : ₹"
//                        + String.format(
//                        "%.2f",
//                        totalInvestment));
//    }
//
//
//    // =========================================================
//    // 3. OVERDUE SIPS
//    // =========================================================
//
//    public void displayOverdueSIPs(
//            List<SIP> sipList) {
//
//        if (sipList == null || sipList.isEmpty()) {
//
//            System.out.println(
//                    "\nNo SIPs available.");
//
//            return;
//        }
//
//        List<SIP> overdueSIPs =
//                sipList.stream()
//                        .filter(sip ->
//                                sip.getNextInstallmentDate()
//                                        .isBefore(
//                                                LocalDate.now()))
//                        .toList();
//
//        System.out.println(
//                "\n========== OVERDUE SIPS ==========");
//
//        if (overdueSIPs.isEmpty()) {
//
//            System.out.println(
//                    "No overdue SIPs found.");
//
//            return;
//        }
//
//        overdueSIPs.forEach(sip -> {
//
//            System.out.println("--------------------------------");
//
//            System.out.println(
//                    "SIP ID           : "
//                            + sip.getSipId());
//
//            System.out.println(
//                    "Investor ID      : "
//                            + sip.getInvestor().getUserId());
//
//            System.out.println(
//                    "Fund             : "
//                            + sip.getMutualFund().getFundName());
//
//            System.out.println(
//                    "Monthly Amount   : ₹"
//                            + String.format(
//                            "%.2f",
//                            sip.getMonthlyAmount()));
//
//            System.out.println(
//                    "Next Installment : "
//                            + sip.getNextInstallmentDate());
//
//            System.out.println(
//                    "Status           : "
//                            + sip.getSipStatus());
//        });
//
//        System.out.println("--------------------------------");
//    }
//
//
//    // =========================================================
//    // 4. CHECK WHETHER ALL SIPS ARE ACTIVE
//    // =========================================================
//
//    public void displayAreAllSIPsActive(
//            List<SIP> sipList) {
//
//        if (sipList == null || sipList.isEmpty()) {
//
//            System.out.println(
//                    "\nNo SIPs available.");
//
//            return;
//        }
//
//        boolean allActive =
//                sipList.stream()
//                        .allMatch(sip ->
//                                "ACTIVE".equalsIgnoreCase(
//                                        sip.getSipStatus()));
//
//        System.out.println(
//                "\n===== SIP ACTIVE STATUS =====");
//
//        if (allActive) {
//
//            System.out.println(
//                    "Are all SIPs active? : YES");
//
//        } else {
//
//            System.out.println(
//                    "Are all SIPs active? : NO");
//        }
//    }
//}