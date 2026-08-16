package com.crimsonlogic.mutualfundinvestmentspringmvc.services.sip;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.SIP;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces.Payable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface I_SIPService {

    // =========================================================
    // VALIDATE SIP
    // =========================================================

    Map<String, String> validateSIP(
            String fundId,
            double monthlyAmount,
            int investmentYears,
            LocalDate startDate,
            String paymentType
    );


    // =========================================================
    // START SIP
    // =========================================================

    SIP startSIP(
            String userId,
            String fundId,
            double monthlyAmount,
            LocalDate startDate,
            int investmentYears,
            Payable paymentMethod
    );


    // =========================================================
    // GET SIP BY ID
    // =========================================================

    SIP getSIPById(
            String sipId
    );


    // =========================================================
    // GET USER SIPS
    // =========================================================

    List<SIP> getSIPsByUser(
            String userId
    );


    // =========================================================
    // GET ALL SIPS
    // =========================================================

    List<SIP> getAllSIPs();


    // =========================================================
    // UPDATE SIP
    // =========================================================

    boolean updateSIP(
            SIP sip
    );


    // =========================================================
    // CANCEL SIP
    // =========================================================

    boolean cancelSIP(
            String sipId
    );
}