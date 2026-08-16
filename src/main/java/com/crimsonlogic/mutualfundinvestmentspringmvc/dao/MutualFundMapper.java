package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MutualFundMapper {

    void insertFund(MutualFund fund);

    void updateFund(MutualFund fund);

    void deleteFund(
            @Param("fundId")
            String fundId);

    MutualFund getFundById(
            @Param("fundId")
            String fundId);
    MutualFund getFundByName(String fundName);

    List<MutualFund> getFundsByCategory(String category);

    List<MutualFund> getAllFunds();
}