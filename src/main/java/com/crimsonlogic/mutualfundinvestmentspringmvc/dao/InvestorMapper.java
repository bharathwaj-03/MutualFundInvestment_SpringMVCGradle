package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;


import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Investor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestorMapper {

    void insertInvestor(Investor investor);

    Investor getInvestorByUserId(
            @Param("userId")
            String userId);
    Investor authenticateInvestor(
            @Param("userId") String userId,
            @Param("password") String password,
            @Param("role") String role
    );


    List<Investor> getAllInvestors();

    void updateInvestor(
            Investor investor);

    void deleteInvestor(
            @Param("userId")
            String userId);
    void updateInvestorPassword(Investor investor);

    void updateInvestorRiskProfile(
            @Param("userId") String userId,
            @Param("riskProfile") String riskProfile);

    void updateInvestorProfile(
            Investor investor
    );

}