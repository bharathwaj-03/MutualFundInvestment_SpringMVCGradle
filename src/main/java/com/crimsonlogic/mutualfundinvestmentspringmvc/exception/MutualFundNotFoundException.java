package com.crimsonlogic.mutualfundinvestmentspringmvc.exception;

public class MutualFundNotFoundException
        extends RuntimeException {

    public MutualFundNotFoundException(String message) {
        super(message);
    }
}