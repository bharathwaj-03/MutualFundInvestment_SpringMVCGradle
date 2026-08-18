package com.crimsonlogic.mutualfundinvestmentspringmvc.services.sip;

import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.SIPMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.SIP;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.Payment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.transaction.SIPTransaction;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.holding.I_HoldingService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.investor.I_InvestorService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.mutualfund.I_MutualFundService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.payment.I_PaymentService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio.I_PortfolioService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.transaction.I_TransactionService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.DateUtil;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.IdGeneratorUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SIPService implements I_SIPService {

    private SIPMapper sipMapper;

    private I_InvestorService investorService;

    private I_MutualFundService mutualFundService;

    private I_HoldingService holdingService;

    private I_PortfolioService portfolioService;

    private I_TransactionService transactionService;

    private I_PaymentService paymentService;


    // =========================================================
    // SETTER INJECTION
    // =========================================================

    public void setSipMapper(
            SIPMapper sipMapper) {

        this.sipMapper = sipMapper;
    }


    public void setInvestorService(
            I_InvestorService investorService) {

        this.investorService = investorService;
    }


    public void setMutualFundService(
            I_MutualFundService mutualFundService) {

        this.mutualFundService =
                mutualFundService;
    }


    public void setHoldingService(
            I_HoldingService holdingService) {

        this.holdingService =
                holdingService;
    }


    public void setPortfolioService(
            I_PortfolioService portfolioService) {

        this.portfolioService =
                portfolioService;
    }


    public void setTransactionService(
            I_TransactionService transactionService) {

        this.transactionService =
                transactionService;
    }
    public void setPaymentService(
            I_PaymentService paymentService) {

       this.paymentService=paymentService;
    }


    // =========================================================
    // VALIDATE SIP
    // =========================================================

    public Map<String, String> validateSIP(
            String fundId,
            double monthlyAmount,
            int investmentYears,
            LocalDate startDate,
            String paymentType) {

        Map<String, String> errors =
                new HashMap<>();


        if (fundId == null ||
                fundId.trim().isEmpty()) {

            errors.put(
                    "fundId",
                    "Please select a mutual fund."
            );
        }


        if (monthlyAmount <= 0) {

            errors.put(
                    "monthlyAmount",
                    "Monthly SIP amount must be greater than 0."
            );
        }


        if (investmentYears <= 0) {

            errors.put(
                    "investmentYears",
                    "Please select a valid investment period."
            );
        }


        if (startDate == null) {

            errors.put(
                    "startDate",
                    "Please select a SIP start date."
            );
        }


        if (paymentType == null ||
                paymentType.trim().isEmpty()) {

            errors.put(
                    "paymentType",
                    "Please select a payment method."
            );
        }


        // Validate against fund minimum investment
        if (fundId != null &&
                !fundId.trim().isEmpty()) {

            MutualFund fund =
                    mutualFundService.getFundById(
                            fundId
                    );

            if (fund == null) {

                errors.put(
                        "fundId",
                        "Selected mutual fund does not exist."
                );

            } else if (
                    monthlyAmount <
                            fund.getMinimumInvestment()) {

                errors.put(
                        "monthlyAmount",
                        "Minimum monthly investment for "
                                + fund.getFundName()
                                + " is ₹"
                                + fund.getMinimumInvestment()
                );
            }
        }


        return errors;
    }


    // =========================================================
    // START SIP
    // =========================================================

    @Override
    @Transactional
    public SIP startSIP(
            String investorId,
            String fundId,
            double monthlyAmount,
            LocalDate startDate,
            int investmentYears,  Payable paymentMethod) {


        // =====================================================
        // 1. VALIDATE INVESTOR
        // =====================================================

        Investor investor =
                investorService.getInvestorByUserId(
                        investorId
                );

        if (investor == null) {

            throw new IllegalArgumentException(
                    "Investor not found."
            );
        }
        boolean paymentSuccessful =
                paymentService.processPayment(
                        paymentMethod,
                        monthlyAmount
                );

        if (!paymentSuccessful) {

            throw new IllegalArgumentException(
                    "Payment failed. SIP was not created."
            );
        }


// =====================================================
// SAVE PAYMENT DETAILS
// =====================================================

        Payment payment =
                paymentService.savePayment(
                        investorId,
                        paymentMethod,
                        monthlyAmount
                );


        // =====================================================
        // 2. VALIDATE FUND
        // =====================================================

        MutualFund mutualFund =
                mutualFundService.getFundById(
                        fundId
                );

        if (mutualFund == null) {

            throw new IllegalArgumentException(
                    "Selected mutual fund was not found."
            );
        }


        // =====================================================
        // 3. VALIDATE AMOUNT
        // =====================================================

        if (monthlyAmount <= 0) {

            throw new IllegalArgumentException(
                    "Monthly SIP amount must be greater than 0."
            );
        }


        if (monthlyAmount <
                mutualFund.getMinimumInvestment()) {

            throw new IllegalArgumentException(
                    "Minimum SIP amount for "
                            + mutualFund.getFundName()
                            + " is ₹"
                            + mutualFund.getMinimumInvestment()
            );
        }


        if (monthlyAmount > 100000) {

            throw new IllegalArgumentException(
                    "Maximum monthly SIP amount is ₹100000."
            );
        }


        // =====================================================
        // 4. VALIDATE YEARS
        // =====================================================

        if (investmentYears <= 0) {

            throw new IllegalArgumentException(
                    "Investment period must be greater than 0 years."
            );
        }


        // =====================================================
        // 5. VALIDATE START DATE
        // =====================================================

        if (startDate == null) {

            throw new IllegalArgumentException(
                    "SIP start date is required."
            );
        }


        if (startDate.isBefore(
                DateUtil.getCurrentDate().plusDays(1))) {

            throw new IllegalArgumentException(
                    "SIP start date must be tomorrow or later."
            );
        }


        // =====================================================
        // 6. CALCULATE FIRST INSTALLMENT UNITS
        // =====================================================

        double unitsPurchased =
                monthlyAmount /
                        mutualFund.getNav();


        // =====================================================
        // 7. CALCULATE EXPECTED GAINS
        // =====================================================

        double annualGain =
                monthlyAmount
                        * mutualFund.getSipGainPerYear()
                        / 100.0;


        double totalGain =
                annualGain * investmentYears;


        // =====================================================
        // 8. CREATE SIP
        // =====================================================

        SIP sip =
                new SIP();


        sip.setSipId(
                IdGeneratorUtil.generateSipId()
        );


        sip.setInvestor(
                investor
        );


        sip.setMutualFund(
                mutualFund
        );


        sip.setMonthlyAmount(
                monthlyAmount
        );


        sip.setUnitsPurchased(
                unitsPurchased
        );




        sip.setActivityDate(
                DateUtil.getCurrentDate()
        );


        sip.setStartDate(
                startDate
        );


        sip.setNextInstallmentDate(
                startDate.plusMonths(1)
        );


        sip.setInvestmentYears(
                investmentYears
        );


        sip.setAssetGainPerYear(
                annualGain
        );


        sip.setAssetGainTotalInvestedYears(
                totalGain
        );


        sip.setSipStatus(
                "ACTIVE"
        );


        // =====================================================
        // 9. INSERT SIP
        // =====================================================

        sipMapper.insertSIP(
                sip
        );


        // =====================================================
// 10. CREATE FIRST SIP TRANSACTION
// =====================================================

        SIPTransaction transaction =
                new SIPTransaction();

        transaction.setTransactionId(
                IdGeneratorUtil.generateTransactionId()
        );

        transaction.setInvestor(
                investor
        );

        transaction.setMutualFund(
                mutualFund
        );

        transaction.setAmount(
                monthlyAmount
        );

        transaction.setTransactionType(
                "SIP"
        );

        transaction.setTransactionStatus(
                "SUCCESS"
        );
        transaction.setPaymentId(
                payment.getPaymentId()
        );

        transaction.setTransactionDateTime(
                java.time.LocalDateTime.now()
        );

// Execute SIP transaction
        transaction.executeTransaction();


// Save transaction
        if (!transactionService.addTransaction(
                transaction)) {

            throw new IllegalStateException(
                    "SIP transaction could not be recorded."
            );
        }





        // =====================================================
        // 11. GET PORTFOLIO
        // =====================================================

        Portfolio portfolio =
                portfolioService.getPortfolio(
                        investorId
                );


        if (portfolio == null) {

            throw new IllegalStateException(
                    "Investor portfolio not found."
            );
        }


        // =====================================================
        // 12. FIND EXISTING HOLDING
        // =====================================================

        Holding existingHolding =
                holdingService
                        .getHoldingByPortfolioAndFund(
                                portfolio.getPortfolioId(),
                                mutualFund.getFundId()
                        );


        // =====================================================
        // 13. CREATE / UPDATE HOLDING
        // =====================================================

        if (existingHolding == null) {

            Holding holding =
                    new Holding();


            holding.setHoldingId(
                    IdGeneratorUtil.generateHoldingId()
            );


            holding.setPortfolio(
                    portfolio
            );


            holding.setMutualFund(
                    mutualFund
            );


            holding.setUnitsOwned(
                    unitsPurchased
            );


            holding.setInvestedAmount(
                    monthlyAmount
            );


            holding.setAverageNav(
                    mutualFund.getNav()
            );


            if (!holdingService.createHolding(
                    holding)) {

                throw new IllegalStateException(
                        "SIP holding could not be created."
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
                    oldAmount + monthlyAmount;


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
                        "SIP holding could not be updated."
                );
            }
        }


        // =====================================================
        // 14. UPDATE PORTFOLIO
        // =====================================================

        portfolioService.updatePortfolioDate(
                portfolio
        );


        return sip;
    }


    // =========================================================
    // GET SIP BY ID
    // =========================================================

    @Override
    public SIP getSIPById(
            String sipId) {

        return sipMapper.getSIPById(
                sipId
        );
    }


    // =========================================================
    // GET USER SIPS
    // =========================================================

    @Override
    public List<SIP> getSIPsByUser(
            String userId) {

        return sipMapper.getSIPsByUserId(
                userId
        );
    }


    // =========================================================
    // GET ALL SIPS
    // =========================================================

    @Override
    public List<SIP> getAllSIPs() {

        return sipMapper.getAllSIPs();
    }


    // =========================================================
    // UPDATE SIP
    // =========================================================

    @Override
    public boolean updateSIP(
            SIP sip) {

        try {

            if (sip == null ||
                    sip.getSipId() == null) {

                return false;
            }

            sipMapper.updateSIP(
                    sip
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CANCEL SIP
    // =========================================================

    @Override
    @Transactional
    public boolean cancelSIP(
            String sipId) {

        try {

            SIP sip =
                    sipMapper.getSIPById(
                            sipId
                    );

            if (sip == null) {
                return false;
            }


            if ("CANCELLED".equalsIgnoreCase(
                    sip.getSipStatus())) {

                return false;
            }


            sip.setSipStatus(
                    "CANCELLED"
            );


            sipMapper.updateSIP(
                    sip
            );


            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}