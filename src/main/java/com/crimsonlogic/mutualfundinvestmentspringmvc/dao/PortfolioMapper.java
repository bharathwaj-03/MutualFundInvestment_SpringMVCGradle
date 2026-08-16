package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
import org.apache.ibatis.annotations.Param;

public interface PortfolioMapper {

    void insertPortfolio(
            Portfolio portfolio
    );

    Portfolio getPortfolioByUserId(
            String userId
    );

    void updatePortfolio(
            Portfolio portfolio
    );

    double calculatePortfolioValue(
            String portfolioId
    );
}