package com.crimsonlogic.mutualfundinvestmentspringmvc.utilities;

import java.util.concurrent.ThreadLocalRandom;

public class IdGeneratorUtil {

    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 1000000; // 10 Lakh

    public IdGeneratorUtil() {
    }

    /**
     * Generates a random integer between 1 and 1,000,000 (inclusive).
     */
    private static int getRandomNumber() {
        return ThreadLocalRandom.current().nextInt(MIN_VAL, MAX_VAL + 1);
    }

    public static String generateInvestorId() {
        return "INV" + getRandomNumber();
    }

    public static String generateFundId() {
        return "FND" + getRandomNumber();
    }

    public static String generatePortfolioId() {
        return "PORT" + getRandomNumber();
    }

    public static String generateTransactionId() {
        return "TXN" + getRandomNumber();
    }

    public static String generateHoldingId() {
        return "HLD" + getRandomNumber();
    }

    public static String generateSipId() {
        return "SIP" + getRandomNumber();
    }

    public static String generateInvestmentId() {
        return "INVT" + getRandomNumber();
    }

    public static String generateRedemptiontId() {
        return "RED" + getRandomNumber();
    }

    public static String generateDividendId() {
        return "DIV" + getRandomNumber();
    }

    public static String generateNavHistoryId() {
        return "NAV" + getRandomNumber();
    }

    public static String generateNomineeId() {
        return "NOM" + getRandomNumber();
    }
}