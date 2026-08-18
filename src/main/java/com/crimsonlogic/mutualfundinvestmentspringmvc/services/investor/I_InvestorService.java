package com.crimsonlogic.mutualfundinvestmentspringmvc.services.investor;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface I_InvestorService {

    Map<String, String> validateInvestor(
            Investor investor
    );

    boolean registerInvestor(
            Investor investor
    );

    Investor authenticateInvestor(
            String userId,
            String password
    );
   Investor getInvestorByUserId(  @Param("userId")String userId);

    boolean updateInvestorProfile(
            Investor investor
    );
}