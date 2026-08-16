package com.crimsonlogic.mutualfundinvestmentspringmvc.services.navhistory;

import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.NAVHistoryMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.exception.MutualFundNotFoundException;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.nav.NAVHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NAVHistoryService
        implements I_NAVHistoryService {

    private NAVHistoryMapper navHistoryMapper;


    // =========================================================
    // SETTER INJECTION
    // =========================================================

    public void setNavHistoryMapper(
            NAVHistoryMapper navHistoryMapper) {

        this.navHistoryMapper =
                navHistoryMapper;
    }


    // =========================================================
    // GET HISTORY BY FUND
    // =========================================================

    @Override
    public List<NAVHistory> getNAVHistoryByFundId(
            String fundId) {

        try {

            if (fundId == null ||
                    fundId.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund ID cannot be empty."
                );
            }

            return navHistoryMapper
                    .getNAVHistoryByFundId(
                            fundId
                    );

        } catch (IllegalArgumentException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to retrieve NAV history."
            );
        }
    }


    // =========================================================
    // GET ALL HISTORY
    // =========================================================

    @Override
    public List<NAVHistory> getAllNAVHistory() {

        try {

            return navHistoryMapper
                    .getAllNAVHistory();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to retrieve NAV history."
            );
        }
    }
}