package com.crimsonlogic.mutualfundinvestmentspringmvc.services.navhistory;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.nav.NAVHistory;

import java.util.List;

public interface I_NAVHistoryService {

    List<NAVHistory> getNAVHistoryByFundId(
            String fundId
    );

    List<NAVHistory> getAllNAVHistory();
}