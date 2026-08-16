package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.nav.NAVHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NAVHistoryMapper {

    void insertNAVHistory(NAVHistory navHistory);


    List<NAVHistory> getNAVHistoryByFundId(String fundId);

    List<NAVHistory> getAllNAVHistory();
}