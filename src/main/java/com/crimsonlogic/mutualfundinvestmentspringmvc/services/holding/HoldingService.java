package com.crimsonlogic.mutualfundinvestmentspringmvc.services.holding;

import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.HoldingMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.IdGeneratorUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoldingService implements I_HoldingService {

    private HoldingMapper holdingMapper;


    // =========================================================
    // SETTER INJECTION
    // =========================================================

    public void setHoldingMapper(
            HoldingMapper holdingMapper) {

        this.holdingMapper = holdingMapper;
    }


    // =========================================================
    // CREATE HOLDING
    // =========================================================

    @Override
    public boolean createHolding(Holding holding) {

        try {

            if (holding == null) {
                return false;
            }

            if (holding.getHoldingId() == null ||
                    holding.getHoldingId().trim().isEmpty()) {

                holding.setHoldingId(
                        IdGeneratorUtil.generateHoldingId()
                );
            }

            holdingMapper.insertHolding(holding);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET HOLDING BY ID
    // =========================================================

    @Override
    public Holding getHoldingById(
            String holdingId) {

        return holdingMapper.getHoldingById(
                holdingId
        );
    }


    // =========================================================
    // GET HOLDINGS BY PORTFOLIO
    // =========================================================

    @Override
    public List<Holding> getHoldingsByPortfolio(
            String portfolioId) {

        return holdingMapper
                .getHoldingsByPortfolio(
                        portfolioId
                );
    }


    // =========================================================
    // GET PARTICULAR FUND HOLDING
    // =========================================================

    @Override
    public Holding getHoldingByPortfolioAndFund(
            String portfolioId,
            String fundId) {

        return holdingMapper
                .getHoldingByPortfolioAndFund(
                        portfolioId,
                        fundId
                );
    }


    // =========================================================
    // UPDATE HOLDING
    // =========================================================

    @Override
    public boolean updateHolding(
            Holding holding) {

        try {

            holdingMapper.updateHolding(
                    holding
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE HOLDING
    // =========================================================

    @Override
    public boolean deleteHolding(
            String holdingId) {

        try {

            holdingMapper.deleteHolding(
                    holdingId
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET ALL HOLDINGS
    // =========================================================

    @Override
    public List<Holding> getAllHoldings() {

        return holdingMapper.getAllHoldings();
    }
}