package com.crimsonlogic.mutualfundinvestmentspringmvc.controller;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.Investment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.BankPayment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.CardPayment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.UpiPayment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.investment.I_InvestmentService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.mutualfund.I_MutualFundService;

import com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio.I_PortfolioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/investor/investment")
public class InvestmentController {

    private final I_InvestmentService investmentService;
    private final I_MutualFundService mutualFundService;
    private final I_PortfolioService portfolioService;


    public InvestmentController(
            I_InvestmentService investmentService,
            I_MutualFundService mutualFundService,I_PortfolioService portfolioService) {

        this.investmentService = investmentService;
        this.mutualFundService = mutualFundService;
        this.portfolioService=portfolioService;
    }


    // =========================================================
    // INVESTMENT PAGE
    // =========================================================

    @GetMapping
    public String investmentPage(
            HttpSession session,
            Model model) {

        Investor investor =
                (Investor) session.getAttribute(
                        "loggedInInvestor"
                );

        if (investor == null) {

            return "redirect:/userlogin/investor";
        }

        List<MutualFund> funds =
                mutualFundService.getAllFunds();

        model.addAttribute(
                "funds",
                funds
        );

        return "investorviews/investment";
    }



    // =========================================================
    // START INVESTMENT
    // =========================================================

    @PostMapping
    public String startInvestment(

            @RequestParam String fundId,
            @RequestParam double amount,
            @RequestParam int investmentYears,
            @RequestParam String paymentType,

            @RequestParam(required = false)
            String upiId,

            @RequestParam(required = false)
            String cardNumber,

            @RequestParam(required = false)
            String cardHolderName,

            @RequestParam(required = false)
            String bankName,

            @RequestParam(required = false)
            String accountNumber,

            HttpSession session,
            Model model) {

        Investor investor =
                (Investor) session.getAttribute(
                        "loggedInInvestor"
                );

        if (investor == null) {
            return "redirect:/userlogin/investor";
        }


        // =====================================================
        // 1. VALIDATE INVESTMENT FORM
        // =====================================================

        Map<String, String> errors =
                investmentService.validateInvestment(
                        fundId,
                        amount,
                        investmentYears,
                        paymentType
                );

        if (!errors.isEmpty()) {

            model.addAttribute(
                    "errors",
                    errors
            );

            model.addAttribute(
                    "funds",
                    mutualFundService.getAllFunds()
            );

            return "investorviews/investment";
        }


        // =====================================================
        // 2. CREATE PAYMENT METHOD
        // =====================================================

        try {

            Payable paymentMethod;


            if ("UPI".equalsIgnoreCase(paymentType)) {

                paymentMethod =
                        new UpiPayment(upiId);

            } else if ("CARD".equalsIgnoreCase(paymentType)) {

                paymentMethod =
                        new CardPayment(
                                cardNumber,
                                cardHolderName
                        );

            } else if ("BANK".equalsIgnoreCase(paymentType)) {

                paymentMethod =
                        new BankPayment(
                                bankName,
                                accountNumber
                        );

            } else {

                throw new IllegalArgumentException(
                        "Please select a payment method."
                );
            }


            // =================================================
            // 3. START COMPLETE INVESTMENT
            // =================================================

            Investment investment =
                    investmentService.startInvestment(

                            investor.getUserId(),

                            fundId,

                            amount,

                            investmentYears,

                            paymentMethod
                    );


            // =================================================
            // 4. SUCCESS
            // =================================================

            model.addAttribute(
                    "investment",
                    investment
            );

            return "investorviews/investment-success";


        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "selectedFund",
                    fundId
            );

            model.addAttribute(
                    "amount",
                    amount
            );

            model.addAttribute(
                    "investmentYears",
                    investmentYears
            );

            model.addAttribute(
                    "paymentType",
                    paymentType
            );

            model.addAttribute(
                    "funds",
                    mutualFundService.getAllFunds()
            );

            return "investorviews/investment";
        }
    }

    @GetMapping("/investor-success")
    public String investorSuccess(
            HttpSession session,
            Model model) {

        Investor investor =
                (Investor) session.getAttribute(
                        "loggedInInvestor"
                );

        if (investor == null) {

            return "redirect:/userlogin/investor";
        }

        Portfolio portfolio =
                portfolioService.getPortfolio(
                        investor.getUserId()
                );

        model.addAttribute(
                "investor",
                investor
        );

        model.addAttribute(
                "portfolio",
                portfolio
        );

        return "investorviews/investor-success";
    }
}