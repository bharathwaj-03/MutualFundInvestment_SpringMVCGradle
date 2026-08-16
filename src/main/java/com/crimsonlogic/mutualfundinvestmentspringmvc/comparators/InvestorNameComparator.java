package com.crimsonlogic.mutualfundinvestmentspringmvc.comparators;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;

import java.util.Comparator;

public class InvestorNameComparator
        implements Comparator<Investor> {

    @Override
    public int compare(
            Investor investor1,
            Investor investor2) {

        return investor1.getName()
                .compareToIgnoreCase(
                        investor2.getName());
    }
}