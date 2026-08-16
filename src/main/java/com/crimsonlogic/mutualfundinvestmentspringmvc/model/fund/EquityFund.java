package com.crimsonlogic.mutualfundinvestmentspringmvc.model.fund;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;

public class EquityFund extends MutualFund {





    @Override

    public String toString() {

        return "EquityFund{" +
                "fundId=" + getFundId() +
                ", fundName='" + getFundName() + '\'' +
                ", category='" + getFundCategory() + '\'' +
                ", nav=" + getNav() +
                ", minimumInvestment=" + getMinimumInvestment() +
                ", riskLevel='" + getRiskLevel() + '\'' +
                '}';
    }
}
