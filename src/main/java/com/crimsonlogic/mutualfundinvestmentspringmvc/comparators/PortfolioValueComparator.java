package com.crimsonlogic.mutualfundinvestmentspringmvc.comparators;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;

import java.util.Comparator;

public class PortfolioValueComparator
        implements Comparator<Portfolio> {

    @Override
    public int compare(
            Portfolio portfolio1,
            Portfolio portfolio2) {

        double value1 =
                portfolio1.getHoldings()
                        .stream()
                        .mapToDouble(holding ->
                                holding.getUnitsOwned()
                                        * holding.getMutualFund()
                                        .getNav())
                        .sum();

        double value2 =
                portfolio2.getHoldings()
                        .stream()
                        .mapToDouble(holding ->
                                holding.getUnitsOwned()
                                        * holding.getMutualFund()
                                        .getNav())
                        .sum();

        return Double.compare(
                value1,
                value2);
    }
}