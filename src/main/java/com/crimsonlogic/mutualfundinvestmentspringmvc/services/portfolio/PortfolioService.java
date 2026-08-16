package com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio;

import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.InvestorMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.PortfolioMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio.I_PortfolioService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.DateUtil;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.IdGeneratorUtil;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService implements I_PortfolioService {

    private PortfolioMapper portfolioMapper;
    private InvestorMapper investorMapper;

    public void setPortfolioMapper(
            PortfolioMapper portfolioMapper) {

        this.portfolioMapper = portfolioMapper;
    }

    public void setInvestorMapper(
            InvestorMapper investorMapper) {

        this.investorMapper = investorMapper;
    }


    @Override
    public Portfolio createPortfolio(String userId) {

        // Prevent duplicate portfolio
        Portfolio existingPortfolio =
                portfolioMapper.getPortfolioByUserId(userId);

        if (existingPortfolio != null) {
            return existingPortfolio;
        }

        Investor investor =
                investorMapper.getInvestorByUserId(userId);

        if (investor == null) {
            return null;
        }

        Portfolio portfolio = new Portfolio();

        portfolio.setPortfolioId(
                IdGeneratorUtil.generatePortfolioId()
        );

        portfolio.setInvestor(investor);

        portfolio.setLastActivityDate(
                DateUtil.getCurrentDate()
        );

        portfolioMapper.insertPortfolio(portfolio);

        return portfolio;
    }


    @Override
    public Portfolio getPortfolio(String investorId) {

        return portfolioMapper
                .getPortfolioByUserId(investorId);
    }


    @Override
    public void updatePortfolioDate(
            Portfolio portfolio) {

        portfolio.setLastActivityDate(
                DateUtil.getCurrentDate()
        );

        portfolioMapper.updatePortfolio(portfolio);
    }


    @Override
    public double calculatePortfolioValue(
            String investorId) {

        return portfolioMapper
                .calculatePortfolioValue(investorId);
    }
}