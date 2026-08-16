package com.crimsonlogic.mutualfundinvestmentspringmvc.controller;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.mutualfund.I_MutualFundService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.navhistory.I_NAVHistoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mutualfund")
public class MutualFundController {

    private final I_MutualFundService mutualFundService;

    private final I_NAVHistoryService navHistoryService;


    public MutualFundController(
            I_MutualFundService mutualFundService,
            I_NAVHistoryService navHistoryService) {

        this.mutualFundService =
                mutualFundService;

        this.navHistoryService =
                navHistoryService;
    }


    // =========================================================
    // INVESTOR - VIEW ALL FUNDS
    // =========================================================

    @GetMapping("/investor")
    public String investorFunds(
            Model model) {

        model.addAttribute(
                "funds",
                mutualFundService.getAllFunds()
        );

        return "investorviews/mutualfund-list";
    }


    // =========================================================
    // INVESTOR - FILTER CATEGORY
    // =========================================================

    @GetMapping("/investor/category")
    public String investorFundsByCategory(
            @RequestParam String category,
            Model model) {

        try {

            model.addAttribute(
                    "funds",
                    mutualFundService
                            .getFundsByCategory(
                                    category
                            )
            );

            model.addAttribute(
                    "selectedCategory",
                    category
            );

            return "investorviews/mutualfund-list";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "investorviews/mutualfund-list";
        }
    }


    // =========================================================
    // ADMIN - VIEW ALL FUNDS
    // =========================================================

    @GetMapping("/admin")
    public String adminFunds(
            Model model) {

        model.addAttribute(
                "funds",
                mutualFundService.getAllFunds()
        );

        return "adminviews/mutualfund-list";
    }


    // =========================================================
// ADMIN - ADD FUND PAGE
// =========================================================

    @GetMapping("/admin/add")
    public String addFundPage(Model model) {

        model.addAttribute(
                "fund",
                new com.crimsonlogic.mutualfundinvestmentspringmvc.model.fund.EquityFund()
        );

        return "adminviews/mutualfund-add";
    }


// =========================================================
// ADMIN - ADD FUND
// =========================================================

    @PostMapping("/admin/add")
    public String addFund(
            @RequestParam String fundCategory,
            @RequestParam String fundCode,
            @RequestParam String fundName,
            @RequestParam String fundHouse,
            @RequestParam String riskLevel,
            @RequestParam double nav,
            @RequestParam int minimumInvestment,
            @RequestParam double sipGainPerYear,
            @RequestParam double lumpSumGainPerYear,
            Model model) {

        try {

            MutualFund fund;

            // Create correct subclass
            switch (fundCategory) {

                case "Debt Fund":
                    fund = new com.crimsonlogic.mutualfundinvestmentspringmvc.model.fund.DebtFund();
                    break;

                case "Equity Fund":
                    fund = new com.crimsonlogic.mutualfundinvestmentspringmvc.model.fund.EquityFund();
                    break;

                case "Hybrid Fund":
                    fund = new com.crimsonlogic.mutualfundinvestmentspringmvc.model.fund.HybridFund();
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Invalid mutual fund category."
                    );
            }


            fund.setFundCode(fundCode);
            fund.setFundName(fundName);
            fund.setFundCategory(fundCategory);
            fund.setFundHouse(fundHouse);
            fund.setRiskLevel(riskLevel);
            fund.setNav(nav);
            fund.setMinimumInvestment(minimumInvestment);
            fund.setSipGainPerYear(sipGainPerYear);
            fund.setLumpSumGainPerYear(lumpSumGainPerYear);


            mutualFundService.addFund(fund);

            return "redirect:/mutualfund/admin";


        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "adminviews/mutualfund-add";
        }
    }


    // =========================================================
    // ADMIN - EDIT PAGE
    // =========================================================

    @GetMapping("/admin/edit/{fundId}")
    public String editFundPage(
            @PathVariable String fundId,
            Model model) {

        try {

            MutualFund fund =
                    mutualFundService.getFundById(
                            fundId
                    );

            model.addAttribute(
                    "fund",
                    fund
            );

            return "adminviews/mutualfund-edit";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "adminviews/mutualfund-list";
        }
    }


    // =========================================================
    // ADMIN - EDIT FUND
    // =========================================================

    @PostMapping("/admin/edit")
    public String editFund(
            @ModelAttribute("fund") MutualFund fund,
            Model model) {

        try {

            mutualFundService.updateFund(
                    fund
            );

            return "redirect:/mutualfund/admin";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "adminviews/mutualfund-edit";
        }
    }


    // =========================================================
    // ADMIN - DELETE FUND
    // =========================================================

    @PostMapping("/admin/delete/{fundId}")
    public String deleteFund(
            @PathVariable String fundId,
            Model model) {

        try {

            mutualFundService.deleteFund(
                    fundId
            );

            return "redirect:/mutualfund/admin";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "funds",
                    mutualFundService.getAllFunds()
            );

            return "adminviews/mutualfund-list";
        }
    }


    // =========================================================
    // ADMIN - NAV UPDATE PAGE
    // =========================================================

    @GetMapping("/admin/nav/{fundId}")
    public String navUpdatePage(
            @PathVariable String fundId,
            Model model) {

        try {

            MutualFund fund =
                    mutualFundService.getFundById(
                            fundId
                    );

            model.addAttribute(
                    "fund",
                    fund
            );

            return "adminviews/mutualfund-nav-update";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "adminviews/mutualfund-list";
        }
    }


    // =========================================================
    // ADMIN - UPDATE NAV
    // =========================================================

    @PostMapping("/admin/nav")
    public String updateNAV(
            @RequestParam String fundId,
            @RequestParam double newNAV,
            @RequestParam String adminId,
            Model model) {

        try {

            mutualFundService.updateNAV(
                    fundId,
                    newNAV,
                    adminId
            );

            return "redirect:/mutualfund/admin";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            MutualFund fund =
                    mutualFundService.getFundById(
                            fundId
                    );

            model.addAttribute(
                    "fund",
                    fund
            );

            return "adminviews/mutualfund-nav-update";
        }
    }


    // =========================================================
    // ADMIN - ALL NAV HISTORY
    // =========================================================

    @GetMapping("/admin/nav-history")
    public String allNAVHistory(
            Model model) {

        try {

            model.addAttribute(
                    "history",
                    navHistoryService
                            .getAllNAVHistory()
            );

            return "adminviews/nav-history";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "adminviews/nav-history";
        }
    }


    // =========================================================
    // ADMIN - NAV HISTORY FOR FUND
    // =========================================================

    @GetMapping("/admin/nav-history/{fundId}")
    public String fundNAVHistory(
            @PathVariable String fundId,
            Model model) {

        try {

            model.addAttribute(
                    "history",
                    navHistoryService
                            .getNAVHistoryByFundId(
                                    fundId
                            )
            );

            model.addAttribute(
                    "fundId",
                    fundId
            );

            return "adminviews/nav-history";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "adminviews/nav-history";
        }
    }
}