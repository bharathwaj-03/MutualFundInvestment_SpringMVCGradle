package com.crimsonlogic.mutualfundinvestmentspringmvc.controller;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.SIP;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.BankPayment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.CardPayment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.payment.UpiPayment;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;

import com.crimsonlogic.mutualfundinvestmentspringmvc.services.mutualfund.I_MutualFundService;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.sip.I_SIPService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/investor/sip")
public class SIPController {

    private final I_SIPService sipService;

    private final I_MutualFundService mutualFundService;


    // =========================================================
    // CONSTRUCTOR INJECTION
    // =========================================================

    public SIPController(
            I_SIPService sipService,
            I_MutualFundService mutualFundService) {

        this.sipService =
                sipService;

        this.mutualFundService =
                mutualFundService;
    }


    // =========================================================
    // SIP PAGE
    // =========================================================

    @GetMapping
    public String sipPage(
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


        return "investorviews/sip";
    }


    // =========================================================
    // START SIP
    // =========================================================

    @PostMapping
    public String startSIP(

            @RequestParam String fundId,

            @RequestParam double monthlyAmount,

            @RequestParam int investmentYears,

            @RequestParam
            @org.springframework.format.annotation.DateTimeFormat(
                    pattern = "yyyy-MM-dd"
            )
            LocalDate startDate,

            @RequestParam String paymentType,


            // ============================
            // UPI
            // ============================

            @RequestParam(required = false)
            String upiId,


            // ============================
            // CARD
            // ============================

            @RequestParam(required = false)
            String cardNumber,

            @RequestParam(required = false)
            String cardHolderName,


            // ============================
            // BANK
            // ============================

            @RequestParam(required = false)
            String bankName,

            @RequestParam(required = false)
            String accountNumber,


            HttpSession session,

            Model model) {


        // =====================================================
        // 1. GET LOGGED-IN INVESTOR
        // =====================================================

        Investor investor =
                (Investor) session.getAttribute(
                        "loggedInInvestor"
                );


        if (investor == null) {

            return "redirect:/userlogin/investor";
        }


        // =====================================================
        // 2. VALIDATE SIP FORM
        // =====================================================

        Map<String, String> errors =
                sipService.validateSIP(

                        fundId,

                        monthlyAmount,

                        investmentYears,

                        startDate,

                        paymentType
                );


        if (!errors.isEmpty()) {

            model.addAttribute(
                    "errors",
                    errors
            );


            // Preserve entered values

            model.addAttribute(
                    "selectedFund",
                    fundId
            );

            model.addAttribute(
                    "monthlyAmount",
                    monthlyAmount
            );

            model.addAttribute(
                    "investmentYears",
                    investmentYears
            );

            model.addAttribute(
                    "startDate",
                    startDate
            );

            model.addAttribute(
                    "paymentType",
                    paymentType
            );


            // Reload funds

            model.addAttribute(
                    "funds",
                    mutualFundService.getAllFunds()
            );


            return "investorviews/sip";
        }


        // =====================================================
        // 3. CREATE PAYMENT METHOD
        // =====================================================

        try {

            Payable paymentMethod;


            // =================================================
            // UPI
            // =================================================

            if ("UPI".equalsIgnoreCase(
                    paymentType)) {

                paymentMethod =
                        new UpiPayment(
                                upiId
                        );
            }


            // =================================================
            // CARD
            // =================================================

            else if ("CARD".equalsIgnoreCase(
                    paymentType)) {

                paymentMethod =
                        new CardPayment(

                                cardNumber,

                                cardHolderName
                        );
            }


            // =================================================
            // BANK
            // =================================================

            else if ("BANK".equalsIgnoreCase(
                    paymentType)) {

                paymentMethod =
                        new BankPayment(

                                bankName,

                                accountNumber
                        );
            }


            // =================================================
            // INVALID PAYMENT TYPE
            // =================================================

            else {

                throw new IllegalArgumentException(
                        "Please select a valid payment method."
                );
            }


            // =====================================================
            // 4. START COMPLETE SIP
            // =====================================================

            SIP sip =
                    sipService.startSIP(

                            investor.getUserId(),

                            fundId,

                            monthlyAmount,

                            startDate,

                            investmentYears,

                            paymentMethod
                    );


            // =====================================================
            // 5. SUCCESS
            // =====================================================

            model.addAttribute(
                    "sip",
                    sip
            );


            return "investorviews/sip-success";


        } catch (Exception e) {

            // =====================================================
            // 6. ERROR
            // =====================================================

            model.addAttribute(
                    "error",
                    e.getMessage()
            );


            // Preserve form

            model.addAttribute(
                    "selectedFund",
                    fundId
            );

            model.addAttribute(
                    "monthlyAmount",
                    monthlyAmount
            );

            model.addAttribute(
                    "investmentYears",
                    investmentYears
            );

            model.addAttribute(
                    "startDate",
                    startDate
            );

            model.addAttribute(
                    "paymentType",
                    paymentType
            );


            // Reload funds

            model.addAttribute(
                    "funds",
                    mutualFundService.getAllFunds()
            );


            return "investorviews/sip";
        }
    }


    // =========================================================
    // VIEW SIP
    // =========================================================

    @GetMapping("/{sipId}")
    public String viewSIP(

            @PathVariable String sipId,

            HttpSession session,

            Model model) {


        Investor investor =
                (Investor) session.getAttribute(
                        "loggedInInvestor"
                );


        if (investor == null) {

            return "redirect:/userlogin/investor";
        }


        SIP sip =
                sipService.getSIPById(
                        sipId
                );


        if (sip == null) {

            model.addAttribute(
                    "error",
                    "SIP not found."
            );


            model.addAttribute(
                    "funds",
                    mutualFundService.getAllFunds()
            );


            return "investorviews/sip";
        }


        model.addAttribute(
                "sip",
                sip
        );


        return "investorviews/sip-details";
    }
}