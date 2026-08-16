package com.crimsonlogic.mutualfundinvestmentspringmvc.services.holding;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;

import java.util.List;

public interface I_HoldingService {

    boolean createHolding(Holding holding);

    Holding getHoldingById(String holdingId);

    List<Holding> getHoldingsByPortfolio(String portfolioId);

    Holding getHoldingByPortfolioAndFund(
            String portfolioId,
            String fundId
    );

    boolean updateHolding(Holding holding);

    boolean deleteHolding(String holdingId);

    List<Holding> getAllHoldings();
}