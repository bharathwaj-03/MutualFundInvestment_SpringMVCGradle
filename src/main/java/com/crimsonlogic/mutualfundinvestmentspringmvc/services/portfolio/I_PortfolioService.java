package com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;

public interface I_PortfolioService {

    Portfolio createPortfolio(String userId);

    Portfolio getPortfolio(String investorId);

    void updatePortfolioDate(Portfolio portfolio);

    double calculatePortfolioValue(String investorId);
}