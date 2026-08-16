package com.crimsonlogic.mutualfundinvestmentspringmvc.controller;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.mutualfund.I_MutualFundService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.IdGeneratorUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.fund.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final I_MutualFundService mutualFundService;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AdminController(
            I_MutualFundService mutualFundService) {

        this.mutualFundService =
                mutualFundService;
    }


    // =========================================================
    // ADMIN DASHBOARD
    // =========================================================

    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            Model model) {

        String adminId =
                (String) session.getAttribute(
                        "loggedInAdmin"
                );

        if (adminId == null) {

            return "redirect:/userlogin/admin";
        }

        model.addAttribute(
                "adminId",
                adminId
        );

        return "adminviews/admin-dashboard";
    }


    // =========================================================
    // VIEW ALL FUNDS
    // =========================================================

    @GetMapping("/funds")
    public String viewAllFunds(
            HttpSession session,
            Model model) {

        String adminId =
                (String) session.getAttribute(
                        "loggedInAdmin"
                );

        if (adminId == null) {

            return "redirect:/userlogin/admin";
        }


        List<MutualFund> funds =
                mutualFundService.getAllFunds();


        model.addAttribute(
                "funds",
                funds
        );


        model.addAttribute(
                "adminId",
                adminId
        );


        return "adminviews/funds";
    }


    // =========================================================
    // ADD FUND PAGE
    // =========================================================

    @GetMapping("/funds/add")
    public String addFundPage(
            HttpSession session,
            Model model) {

        String adminId =
                (String) session.getAttribute(
                        "loggedInAdmin"
                );

        if (adminId == null) {

            return "redirect:/userlogin/admin";
        }


        model.addAttribute(
                "adminId",
                adminId
        );


        return "adminviews/add-fund";
    }

    // =========================================================
// ADD FUND
// =========================================================

    @PostMapping("/funds/add")
    public String addFund(

            @RequestParam String fundCode,

            @RequestParam String fundName,

            @RequestParam String fundCategory,

            @RequestParam String fundHouse,

            @RequestParam String riskLevel,

            @RequestParam double nav,

            @RequestParam int minimumInvestment,

            @RequestParam double sipGainPerYear,

            @RequestParam double lumpSumGainPerYear,

            HttpSession session,

            Model model) {


        // =====================================================
        // CHECK ADMIN LOGIN
        // =====================================================

        String adminId =
                (String) session.getAttribute(
                        "loggedInAdmin"
                );


        if (adminId == null) {

            return "redirect:/userlogin/admin";
        }


        try {


            // =================================================
            // 1. GENERATE FUND ID
            // =================================================

            String fundId =
                    IdGeneratorUtil.generateFundId();


            if (fundId == null ||
                    fundId.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Unable to generate Fund ID."
                );
            }


            // =================================================
            // 2. VALIDATE FUND CODE
            // =================================================

            if (fundCode == null ||
                    fundCode.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund Code cannot be empty."
                );
            }


            // =================================================
            // 3. VALIDATE FUND NAME
            // =================================================

            if (fundName == null ||
                    fundName.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund Name cannot be empty."
                );
            }


            // =================================================
            // 4. VALIDATE CATEGORY
            // =================================================

            if (fundCategory == null ||
                    fundCategory.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund Category must be selected."
                );
            }


            // =================================================
            // 5. VALIDATE FUND HOUSE
            // =================================================

            if (fundHouse == null ||
                    fundHouse.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund House cannot be empty."
                );
            }


            // =================================================
            // 6. VALIDATE RISK LEVEL
            // =================================================

            if (riskLevel == null ||
                    riskLevel.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Risk Level must be selected."
                );
            }


            // =================================================
            // 7. VALIDATE NAV
            // =================================================

            if (nav <= 0) {

                throw new IllegalArgumentException(
                        "NAV must be greater than 0."
                );
            }


            // =================================================
            // 8. VALIDATE MINIMUM INVESTMENT
            // =================================================

            if (minimumInvestment <= 0) {

                throw new IllegalArgumentException(
                        "Minimum investment must be greater than 0."
                );
            }


            // =================================================
            // 9. VALIDATE SIP GAIN
            // =================================================

            if (sipGainPerYear < 0) {

                throw new IllegalArgumentException(
                        "SIP gain cannot be negative."
                );
            }


            // =================================================
            // 10. VALIDATE LUMP-SUM GAIN
            // =================================================

            if (lumpSumGainPerYear < 0) {

                throw new IllegalArgumentException(
                        "Lump-sum gain cannot be negative."
                );
            }


            // =================================================
            // 11. CHECK DUPLICATE FUND CODE
            // =================================================

            try {

                MutualFund existingFund =
                        mutualFundService.getFundByName(
                                fundName.trim()
                        );


                if (existingFund != null) {

                    throw new IllegalArgumentException(
                            "A mutual fund with this name already exists."
                    );
                }

            } catch (
                    com.crimsonlogic.mutualfundinvestmentspringmvc.exception.MutualFundNotFoundException ignored) {

                // Fund does not exist.
                // Continue with insertion.
            }


            // =================================================
            // 12. CREATE CORRECT FUND SUBCLASS
            // =================================================

            MutualFund fund;


            if ("Debt Fund".equalsIgnoreCase(
                    fundCategory)) {

                fund = new DebtFund();


            } else if ("Equity Fund".equalsIgnoreCase(
                    fundCategory)) {

                fund = new EquityFund();


            } else if ("Hybrid Fund".equalsIgnoreCase(
                    fundCategory)) {

                fund = new HybridFund();


            } else {

                throw new IllegalArgumentException(
                        "Invalid mutual fund category."
                );
            }


            // =================================================
            // 13. SET FUND DETAILS
            // =================================================

            fund.setFundId(
                    fundId.trim()
            );


            fund.setFundCode(
                    fundCode.trim()
            );


            fund.setFundName(
                    fundName.trim()
            );


            fund.setFundCategory(
                    fundCategory.trim()
            );


            fund.setFundHouse(
                    fundHouse.trim()
            );


            fund.setRiskLevel(
                    riskLevel.trim()
            );


            fund.setNav(
                    nav
            );


            fund.setMinimumInvestment(
                    minimumInvestment
            );


            fund.setSipGainPerYear(
                    sipGainPerYear
            );


            fund.setLumpSumGainPerYear(
                    lumpSumGainPerYear
            );


            // =================================================
            // 14. SAVE FUND
            // =================================================

            mutualFundService.addFund(
                    fund
            );


            // =================================================
            // 15. SUCCESS MESSAGE
            // =================================================

            model.addAttribute(
                    "success",
                    "Mutual Fund "
                            + fund.getFundName()
                            + " added successfully."
            );


            model.addAttribute(
                    "adminId",
                    adminId
            );


            // =================================================
            // 16. REFRESH FUND LIST
            // =================================================

            List<MutualFund> funds =
                    mutualFundService.getAllFunds();


            model.addAttribute(
                    "funds",
                    funds
            );


            // =================================================
            // 17. SHOW ALL FUNDS
            // =================================================

            return "adminviews/funds";


        } catch (Exception e) {


            // =================================================
            // ERROR HANDLING
            // =================================================

            model.addAttribute(
                    "error",
                    e.getMessage()
            );


            model.addAttribute(
                    "adminId",
                    adminId
            );


            // Preserve entered values
            model.addAttribute(
                    "fundCode",
                    fundCode
            );


            model.addAttribute(
                    "fundName",
                    fundName
            );


            model.addAttribute(
                    "fundCategory",
                    fundCategory
            );


            model.addAttribute(
                    "fundHouse",
                    fundHouse
            );


            model.addAttribute(
                    "riskLevel",
                    riskLevel
            );


            model.addAttribute(
                    "nav",
                    nav
            );


            model.addAttribute(
                    "minimumInvestment",
                    minimumInvestment
            );


            model.addAttribute(
                    "sipGainPerYear",
                    sipGainPerYear
            );


            model.addAttribute(
                    "lumpSumGainPerYear",
                    lumpSumGainPerYear
            );


            return "adminviews/add-fund";
        }
    }

    // =========================================================
// UPDATE NAV - SELECT FUND
// =========================================================

    @GetMapping("/funds/update-nav")
    public String updateNAVFunds(
            HttpSession session,
            Model model) {

        String adminId =
                (String) session.getAttribute(
                        "loggedInAdmin"
                );

        if (adminId == null) {

            return "redirect:/userlogin/admin";
        }

        List<MutualFund> funds =
                mutualFundService.getAllFunds();

        model.addAttribute(
                "funds",
                funds
        );

        model.addAttribute(
                "adminId",
                adminId
        );

        return "adminviews/update-nav-funds";
    }


// =========================================================
// UPDATE NAV - SELECTED FUND
// =========================================================

    @GetMapping("/funds/update-nav/{fundId}")
    public String updateNAVPage(
            @PathVariable String fundId,
            HttpSession session,
            Model model) {

        String adminId =
                (String) session.getAttribute(
                        "loggedInAdmin"
                );

        if (adminId == null) {

            return "redirect:/userlogin/admin";
        }

        try {

            MutualFund fund =
                    mutualFundService.getFundById(
                            fundId
                    );

            model.addAttribute(
                    "fund",
                    fund
            );

            model.addAttribute(
                    "adminId",
                    adminId
            );

            return "adminviews/update-nav";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "funds",
                    mutualFundService.getAllFunds()
            );

            return "adminviews/update-nav-funds";
        }
    }


// =========================================================
// UPDATE NAV - SUBMIT
// =========================================================

    @PostMapping("/funds/update-nav")
    public String updateNAV(
            @RequestParam String fundId,
            @RequestParam double newNAV,
            HttpSession session,
            Model model) {

        String adminId =
                (String) session.getAttribute(
                        "loggedInAdmin"
                );

        if (adminId == null) {

            return "redirect:/userlogin/admin";
        }

        try {

            // =============================================
            // VALIDATE NAV
            // =============================================

            if (newNAV <= 0) {

                throw new IllegalArgumentException(
                        "New NAV must be greater than 0."
                );
            }


            // =============================================
            // UPDATE NAV
            // =============================================

            mutualFundService.updateNAV(
                    fundId,
                    newNAV,
                    adminId
            );


            // =============================================
            // SUCCESS
            // =============================================

            model.addAttribute(
                    "success",
                    "NAV updated successfully."
            );

            model.addAttribute(
                    "adminId",
                    adminId
            );

            model.addAttribute(
                    "funds",
                    mutualFundService.getAllFunds()
            );

            return "adminviews/funds";


        } catch (Exception e) {

            // =============================================
            // ERROR
            // =============================================

            model.addAttribute(
                    "error",
                    e.getMessage()
            );


            try {

                MutualFund fund =
                        mutualFundService.getFundById(
                                fundId
                        );

                model.addAttribute(
                        "fund",
                        fund
                );

            } catch (Exception ignored) {
            }


            model.addAttribute(
                    "adminId",
                    adminId
            );

            return "adminviews/update-nav";
        }
    }

}