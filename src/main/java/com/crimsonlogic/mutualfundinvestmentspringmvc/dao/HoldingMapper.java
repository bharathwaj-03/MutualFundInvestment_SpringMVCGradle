package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HoldingMapper {

    void insertHolding(Holding holding);

    Holding getHoldingById(
            @Param("holdingId")
            String holdingId);

    List<Holding> getHoldingsByPortfolio(
            @Param("portfolioId")
            String portfolioId);

    Holding getHoldingByPortfolioAndFund(
            @Param("portfolioId")
            String portfolioId,

            @Param("fundId")
            String fundId);

    void updateHolding(Holding holding);

    void deleteHolding(
            @Param("holdingId")
            String holdingId);

    List<Holding> getAllHoldings();
}