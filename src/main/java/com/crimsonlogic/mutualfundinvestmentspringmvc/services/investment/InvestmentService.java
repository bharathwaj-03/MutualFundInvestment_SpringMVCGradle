package com.crimsonlogic.mutualfundinvestmentspringmvc.services.investment;

import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.InvestmentMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.Transaction;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.Investment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.transaction.BuyTransaction;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.holding.I_HoldingService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.mutualfund.I_MutualFundService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.payment.I_PaymentService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio.I_PortfolioService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.transaction.I_TransactionService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.investor.I_InvestorService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.DateUtil;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.IdGeneratorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class InvestmentService
        implements I_InvestmentService {

    private InvestmentMapper investmentMapper;

    private I_InvestorService investorService;

    private I_MutualFundService mutualFundService;

    private I_PaymentService paymentService;

    private I_TransactionService transactionService;

    private I_HoldingService holdingService;

    private I_PortfolioService portfolioService;


    // =========================================================
    // SETTER INJECTION
    // =========================================================

    public void setInvestmentMapper(
            InvestmentMapper investmentMapper) {

        this.investmentMapper = investmentMapper;
    }

    public void setInvestorService(
            I_InvestorService investorService) {

        this.investorService = investorService;
    }

    public void setMutualFundService(
            I_MutualFundService mutualFundService) {

        this.mutualFundService = mutualFundService;
    }

    public void setPaymentService(
            I_PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    public void setTransactionService(
            I_TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    public void setHoldingService(
            I_HoldingService holdingService) {

        this.holdingService = holdingService;
    }

    public void setPortfolioService(
            I_PortfolioService portfolioService) {

        this.portfolioService = portfolioService;
    }

    public Map<String, String> validateInvestment(
            String fundId,
            double amount,
            int investmentYears,
            String paymentType) {

        Map<String, String> errors =
                new HashMap<>();

        // Fund validation
        if (fundId == null ||
                fundId.trim().isEmpty()) {

            errors.put(
                    "fundId",
                    "Please select a mutual fund."
            );
        }

        // Amount validation
        if (amount <= 0) {

            errors.put(
                    "amount",
                    "Investment amount must be greater than 0."
            );
        }

        // Investment period validation
        if (investmentYears <= 0) {

            errors.put(
                    "investmentYears",
                    "Please select a valid investment period."
            );
        }

        // Payment validation
        if (paymentType == null ||
                paymentType.trim().isEmpty()) {

            errors.put(
                    "paymentType",
                    "Please select a payment method."
            );
        }


        // Minimum investment validation
        if (fundId != null &&
                !fundId.trim().isEmpty()) {

            MutualFund fund =
                    mutualFundService.getFundById(
                            fundId
                    );

            if (fund != null &&
                    amount < fund.getMinimumInvestment()) {

                errors.put(
                        "amount",
                        "Minimum investment for "
                                + fund.getFundName()
                                + " is ₹"
                                + fund.getMinimumInvestment()
                );
            }
        }

        return errors;
    }

    // =========================================================
    // START LUMP SUM INVESTMENT
    // =========================================================

    @Override
    @Transactional
    public Investment startInvestment(
            String investorId,
            String fundId,
            double amount,
            int investmentYears,
            Payable paymentMethod) {

        // 1. Validate amount

        if (amount <= 0) {

            throw new IllegalArgumentException(
                    "Investment amount must be greater than 0."
            );
        }


        // 2. Validate years

        if (investmentYears <= 0) {

            throw new IllegalArgumentException(
                    "Investment period must be greater than 0 years."
            );
        }


        // 3. Get investor

        Investor investor =
                investorService.getInvestorByUserId(
                        investorId
                );

        if (investor == null) {

            throw new IllegalArgumentException(
                    "Investor not found."
            );
        }


        // 4. Get fund

        MutualFund mutualFund =
                mutualFundService.getFundById(
                        fundId
                );

        if (mutualFund == null) {

            throw new IllegalArgumentException(
                    "Selected mutual fund was not found."
            );
        }


        // 5. Minimum investment

        if (amount < mutualFund.getMinimumInvestment()) {

            throw new IllegalArgumentException(
                    "Minimum investment for "
                            + mutualFund.getFundName()
                            + " is ₹"
                            + mutualFund.getMinimumInvestment()
            );
        }


        // 6. Calculate units

        double unitsPurchased =
                amount / mutualFund.getNav();


        // 7. Calculate expected gain

        double annualGain =
                amount
                        * mutualFund.getLumpSumGainPerYear()
                        / 100.0;

        double totalGain =
                annualGain * investmentYears;


        // 8. Payment

        boolean paymentSuccessful =
                paymentService.processPayment(
                        paymentMethod,
                        amount
                );

        if (!paymentSuccessful) {

            throw new IllegalArgumentException(
                    "Payment failed. Investment was not created."
            );
        }


        // 9. Create investment

        Investment investment =
                new Investment();

        investment.setInvestmentId(
                IdGeneratorUtil.generateInvestmentId()
        );

        investment.setInvestor(investor);

        investment.setMutualFund(mutualFund);

        investment.setAmount(amount);

        investment.setUnitsPurchased(
                unitsPurchased
        );

        investment.setActivityDate(
                DateUtil.getCurrentDate()
        );

        investment.setInvestmentYears(
                investmentYears
        );

        investment.setAssetGainPerYear(
                annualGain
        );

        investment.setAssetGainTotalInvestedYears(
                totalGain
        );


        // 10. Insert investment

        investmentMapper.insertInvestment(
                investment
        );


        // 11. Create transaction

        Transaction transaction =
                new BuyTransaction();

        transaction.setTransactionId(
                IdGeneratorUtil.generateTransactionId()
        );

        transaction.setInvestor(investor);

        transaction.setMutualFund(mutualFund);

        transaction.setAmount(amount);

        transaction.setTransactionType(
                "LUMP_SUM"
        );

        transaction.setTransactionStatus(
                "SUCCESS"
        );

        transaction.setTransactionDateTime(
                java.time.LocalDateTime.now()
        );

        transaction.executeTransaction();


        if (!transactionService.addTransaction(
                transaction)) {

            throw new IllegalStateException(
                    "Transaction could not be recorded."
            );
        }


        // 12. Get portfolio

        Portfolio portfolio =
                portfolioService.getPortfolio(
                        investorId
                );

        if (portfolio == null) {

            throw new IllegalStateException(
                    "Investor portfolio not found."
            );
        }


        // 13. Find existing holding

        Holding existingHolding =
                holdingService
                        .getHoldingByPortfolioAndFund(
                                portfolio.getPortfolioId(),
                                mutualFund.getFundId()
                        );


        // 14. Create or update holding

        if (existingHolding == null) {

            Holding holding =
                    new Holding();

            holding.setHoldingId(
                    IdGeneratorUtil.generateHoldingId()
            );

            holding.setPortfolio(portfolio);

            holding.setMutualFund(mutualFund);

            holding.setUnitsOwned(
                    unitsPurchased
            );

            holding.setInvestedAmount(
                    amount
            );

            holding.setAverageNav(
                    mutualFund.getNav()
            );

            if (!holdingService.createHolding(holding)) {

                throw new IllegalStateException(
                        "Holding could not be created."
                );
            }

        } else {

            double oldUnits =
                    existingHolding.getUnitsOwned();

            double oldAmount =
                    existingHolding.getInvestedAmount();

            double newUnits =
                    oldUnits + unitsPurchased;

            double newAmount =
                    oldAmount + amount;

            double newAverageNav =
                    newAmount / newUnits;

            existingHolding.setUnitsOwned(
                    newUnits
            );

            existingHolding.setInvestedAmount(
                    newAmount
            );

            existingHolding.setAverageNav(
                    newAverageNav
            );

            if (!holdingService.updateHolding(
                    existingHolding)) {

                throw new IllegalStateException(
                        "Holding could not be updated."
                );
            }
        }


        // 15. Update portfolio activity

        portfolioService.updatePortfolioDate(
                portfolio
        );


        return investment;
    }


    // =========================================================
    // GET INVESTMENT BY ID
    // =========================================================

    @Override
    public Investment getInvestmentById(
            String investmentId) {

        return investmentMapper
                .getInvestmentById(
                        investmentId
                );
    }


    // =========================================================
    // GET INVESTMENTS BY USER
    // =========================================================

    @Override
    public List<Investment> getInvestmentsByUser(
            String userId) {

        return investmentMapper
                .getInvestmentsByUser(
                        userId
                );
    }


    // =========================================================
    // GET ALL INVESTMENTS
    // =========================================================

    @Override
    public List<Investment> getAllInvestments() {

        return investmentMapper
                .getAllInvestments();
    }
}