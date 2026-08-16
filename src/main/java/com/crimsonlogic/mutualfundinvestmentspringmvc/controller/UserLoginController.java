package com.crimsonlogic.mutualfundinvestmentspringmvc.controller;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.portfolio.Portfolio;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Nominee;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.admin.I_AdminService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.investor.I_InvestorService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.portfolio.I_PortfolioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

import java.util.Map;

@Controller
@RequestMapping("/userlogin")
public class UserLoginController {

    private final I_AdminService adminService;

    private final I_InvestorService investorService;

    private final I_PortfolioService portfolioService;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public UserLoginController(
            I_AdminService adminService,
            I_InvestorService investorService,
            I_PortfolioService portfolioService) {

        this.adminService = adminService;

        this.investorService = investorService;

        this.portfolioService = portfolioService;
    }


    // =========================================================
    // ROLE SELECTION
    // =========================================================

    @GetMapping
    public String userLogin() {

        return "userlogin";
    }


    // =========================================================
    // ADMIN LOGIN PAGE
    // =========================================================

    @GetMapping("/admin")
    public String adminLoginPage() {

        return "adminviews/admin-login";
    }


    // =========================================================
    // ADMIN LOGIN
    // =========================================================

    @PostMapping("/admin")
    public String adminLogin(
            @RequestParam String userId,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        boolean authenticated =
                adminService.authenticateAdmin(
                        userId,
                        password
                );

        if (authenticated) {

            // Store logged-in admin in session
            session.setAttribute(
                    "loggedInAdmin",
                    userId
            );

            // Redirect to admin dashboard
            return "redirect:/admin/dashboard";
        }

        model.addAttribute(
                "error",
                "Invalid Admin ID or Password."
        );

        return "adminviews/admin-login";
    }


    // =========================================================
    // INVESTOR LOGIN / DASHBOARD
    // =========================================================

    @GetMapping("/investor")
    public String investorPage(
            HttpSession session,
            Model model) {

        /*
         * If investor is already logged in,
         * show investor dashboard/success page.
         */

        Investor investor =
                (Investor) session.getAttribute(
                        "loggedInInvestor"
                );


        if (investor != null) {

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


        /*
         * Otherwise show login page.
         */

        return "investorviews/investor-login";
    }


    // =========================================================
    // INVESTOR LOGIN
    // =========================================================

    @PostMapping("/investor")
    public String investorLogin(
            @RequestParam String userId,
            @RequestParam String password,
            Model model,
            HttpSession session) {


        Investor investor =
                investorService.authenticateInvestor(
                        userId,
                        password
                );


        if (investor != null) {


            // ================================================
            // Store logged-in investor in session
            // ================================================

            session.setAttribute(
                    "loggedInInvestor",
                    investor
            );


            // ================================================
            // Get existing portfolio
            // ================================================

            Portfolio portfolio =
                    portfolioService.getPortfolio(
                            investor.getUserId()
                    );


            // ================================================
            // Add BOTH investor and portfolio to model
            // ================================================

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


        // =====================================================
        // LOGIN FAILED
        // =====================================================

        model.addAttribute(
                "error",
                "Invalid Investor ID or Password."
        );


        return "investorviews/investor-login";
    }


    // =========================================================
    // INVESTOR REGISTRATION PAGE
    // =========================================================

    @GetMapping("/investor/register")
    public String investorRegistration(
            Model model) {

        Investor investor =
                new Investor();

        investor.setNominee(
                new Nominee()
        );


        model.addAttribute(
                "investor",
                investor
        );


        return "investorviews/investor-register";
    }


    // =========================================================
    // INVESTOR REGISTRATION
    // =========================================================

    @PostMapping("/investor/register")
    public String registerInvestor(
            @ModelAttribute Investor investor,
            Model model) {


        Map<String, String> errors =
                investorService.validateInvestor(
                        investor
                );


        // =====================================================
        // VALIDATION FAILED
        // =====================================================

        if (!errors.isEmpty()) {

            model.addAttribute(
                    "errors",
                    errors
            );


            return "investorviews/investor-register";
        }


        // =====================================================
        // REGISTER INVESTOR
        // =====================================================

        boolean registered =
                investorService.registerInvestor(
                        investor
                );


        if (registered) {


            Portfolio portfolio =
                    portfolioService.getPortfolio(
                            investor.getUserId()
                    );


            model.addAttribute(
                    "investorId",
                    investor.getUserId()
            );


            model.addAttribute(
                    "portfolio",
                    portfolio
            );


            return "investorviews/registration-success";
        }


        // =====================================================
        // REGISTRATION FAILED
        // =====================================================

        model.addAttribute(
                "error",
                "Registration failed. Please try again."
        );


        return "investorviews/investor-register";
    }

}