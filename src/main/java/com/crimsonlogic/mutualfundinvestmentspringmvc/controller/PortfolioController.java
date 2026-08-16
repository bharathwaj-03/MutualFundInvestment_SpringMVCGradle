package com.crimsonlogic.mutualfundinvestmentspringmvc.controller;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Holding;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.holding.I_HoldingService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio.I_PortfolioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/investor/portfolio")
public class PortfolioController {

    private final I_PortfolioService portfolioService;
    private final I_HoldingService holdingService;


    // =========================================================
    // CONSTRUCTOR INJECTION
    // =========================================================

    public PortfolioController(
            I_PortfolioService portfolioService,
            I_HoldingService holdingService) {

        this.portfolioService = portfolioService;
        this.holdingService = holdingService;
    }


    // =========================================================
    // VIEW PORTFOLIO
    // =========================================================

    @GetMapping
    public String viewPortfolio(
            HttpSession session,
            Model model) {

        Investor investor =
                (Investor) session.getAttribute(
                        "loggedInInvestor"
                );

        // Investor must be logged in
        if (investor == null) {

            return "redirect:/userlogin/investor";
        }


        // =====================================================
        // 1. Get investor portfolio
        // =====================================================

        Portfolio portfolio =
                portfolioService.getPortfolio(
                        investor.getUserId()
                );


        if (portfolio == null) {

            model.addAttribute(
                    "error",
                    "Portfolio not found."
            );

            return "investorviews/portfolio";
        }


        // =====================================================
        // 2. Get holdings
        // =====================================================

        List<Holding> holdings =
                holdingService.getHoldingsByPortfolio(
                        portfolio.getPortfolioId()
                );


        if (holdings == null) {

            holdings =
                    Collections.emptyList();
        }


        // =====================================================
        // 3. Calculate totals
        // =====================================================

        double totalInvested = 0.0;

        double currentPortfolioValue = 0.0;


        for (Holding holding : holdings) {

            totalInvested +=
                    holding.getInvestedAmount();


            if (holding.getMutualFund() != null) {

                currentPortfolioValue +=
                        holding.getUnitsOwned()
                                *
                                holding.getMutualFund()
                                        .getNav();
            }
        }


        double totalGain =
                currentPortfolioValue
                        - totalInvested;


        // =====================================================
        // 4. Add data to JSP
        // =====================================================

        model.addAttribute(
                "investor",
                investor
        );

        model.addAttribute(
                "portfolio",
                portfolio
        );

        model.addAttribute(
                "holdings",
                holdings
        );

        model.addAttribute(
                "totalInvested",
                totalInvested
        );

        model.addAttribute(
                "currentPortfolioValue",
                currentPortfolioValue
        );

        model.addAttribute(
                "totalGain",
                totalGain
        );


        return "investorviews/portfolio";
    }
}