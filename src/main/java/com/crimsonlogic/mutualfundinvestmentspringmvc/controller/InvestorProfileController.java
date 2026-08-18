package com.crimsonlogic.mutualfundinvestmentspringmvc.controller;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.investor.I_InvestorService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.security.EncryptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/investor/profile")
public class InvestorProfileController {


    private final I_InvestorService investorService;


    public InvestorProfileController(
            I_InvestorService investorService) {

        this.investorService =
                investorService;
    }


    // =========================================================
    // OPEN EDIT PROFILE
    // =========================================================

    @GetMapping("/edit")
    public String editProfile(
            HttpSession session,
            Model model) {

        Investor loggedInInvestor =
                (Investor) session.getAttribute(
                        "loggedInInvestor"
                );


        if (loggedInInvestor == null) {

            return "redirect:/userlogin/investor";
        }


        Investor investor =
                investorService.getInvestorByUserId(
                        loggedInInvestor.getUserId()
                );


        if (investor == null) {

            model.addAttribute(
                    "error",
                    "Investor profile not found."
            );

            return "investorviews/investor-success";
        }


        // =====================================================
        // DECRYPT SENSITIVE INFORMATION FOR EDIT FORM
        // =====================================================

        try {

            if (investor.getPanNumber() != null) {

                investor.setPanNumber(
                        EncryptionUtil.decrypt(
                                investor.getPanNumber()
                        )
                );
            }


            if (investor.getAccountNumber() != null) {

                investor.setAccountNumber(
                        EncryptionUtil.decrypt(
                                investor.getAccountNumber()
                        )
                );
            }


            if (investor.getNominee() != null &&
                    investor.getNominee()
                            .getAccountNumber() != null) {

                investor.getNominee()
                        .setAccountNumber(
                                EncryptionUtil.decrypt(
                                        investor.getNominee()
                                                .getAccountNumber()
                                )
                        );
            }

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    "Unable to load sensitive profile information."
            );

            return "investorviews/investor-success";
        }


        model.addAttribute(
                "investor",
                investor
        );


        return "investorviews/edit-profile";
    }


    // =========================================================
    // SAVE PROFILE
    // =========================================================

    @PostMapping("/edit")
    public String updateProfile(
            @ModelAttribute Investor investor,
            HttpSession session,
            Model model) {

        Investor loggedInInvestor =
                (Investor) session.getAttribute("loggedInInvestor");

        if (loggedInInvestor == null) {
            return "redirect:/userlogin/investor";
        }

        // Never trust the ID coming from the form
        investor.setUserId(loggedInInvestor.getUserId());

        boolean updated =
                investorService.updateInvestorProfile(investor);

        if (!updated) {

            model.addAttribute(
                    "error",
                    "Unable to update profile."
            );

            model.addAttribute(
                    "investor",
                    investor
            );

            return "investorviews/edit-profile";
        }

        // Fetch the freshly updated data
        Investor updatedInvestor =
                investorService.getInvestorByUserId(
                        loggedInInvestor.getUserId()
                );

        // Update session
        session.setAttribute(
                "loggedInInvestor",
                updatedInvestor
        );

        // Put updated data back into the form
        model.addAttribute(
                "investor",
                updatedInvestor
        );

        // Tell JSP to show success message
        model.addAttribute(
                "success",
                "Profile updated successfully!"
        );

        return "investorviews/edit-profile";
    }
}