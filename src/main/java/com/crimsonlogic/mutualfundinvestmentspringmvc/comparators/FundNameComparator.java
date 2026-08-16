package com.crimsonlogic.mutualfundinvestmentspringmvc.comparators;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;

import java.util.Comparator;

public class FundNameComparator
        implements Comparator<MutualFund> {

    @Override
    public int compare(
            MutualFund fund1,
            MutualFund fund2) {

        return fund1.getFundName()
                .compareToIgnoreCase(
                        fund2.getFundName());
    }
}