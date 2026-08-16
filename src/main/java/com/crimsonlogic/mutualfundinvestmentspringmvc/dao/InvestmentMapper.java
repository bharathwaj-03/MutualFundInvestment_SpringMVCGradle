package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.Investment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestmentMapper {

    void insertInvestment(
            Investment investment);

    void updateInvestment(
            Investment investment);

    Investment getInvestmentById(
            @Param("investmentId")
            String investmentId);

    List<Investment> getInvestmentsByUser(
            @Param("userId")
            String userId);

    List<Investment> getAllInvestments();
}