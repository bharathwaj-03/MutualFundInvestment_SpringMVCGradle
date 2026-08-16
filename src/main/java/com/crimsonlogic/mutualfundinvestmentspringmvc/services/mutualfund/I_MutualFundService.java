package com.crimsonlogic.mutualfundinvestmentspringmvc.services.mutualfund;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;

import java.util.List;

public interface I_MutualFundService {

    void addFund(MutualFund fund);

    void updateFund(MutualFund fund);

    void deleteFund(String fundId);

    MutualFund getFundById(String fundId);

    MutualFund getFundByName(String fundName);

    List<MutualFund> getFundsByCategory(String category);

    List<MutualFund> getAllFunds();

    void updateNAV(
            String fundId,
            double newNAV,
            String adminId
    );

    double getCurrentNav(String fundId);
}