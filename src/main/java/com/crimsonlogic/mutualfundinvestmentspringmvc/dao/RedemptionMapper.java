package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.Redemption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RedemptionMapper {

    void insertRedemption(
            Redemption redemption);

    Redemption getRedemptionById(
            @Param("redemptionId")
            String redemptionId);

    List<Redemption> getRedemptionsByUser(
            @Param("userId")
            String userId);

    List<Redemption> getAllRedemptions();
}