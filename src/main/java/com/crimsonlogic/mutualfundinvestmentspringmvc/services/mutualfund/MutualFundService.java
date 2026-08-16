package com.crimsonlogic.mutualfundinvestmentspringmvc.services.mutualfund;

import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.MutualFundMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.NAVHistoryMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.exception.MutualFundNotFoundException;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.MutualFund;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.nav.NAVHistory;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.DateUtil;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.IdGeneratorUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutualFundService implements I_MutualFundService {

    private MutualFundMapper mutualFundMapper;

    private NAVHistoryMapper navHistoryMapper;


    // =========================================================
    // SETTER INJECTION
    // =========================================================

    public void setMutualFundMapper(
            MutualFundMapper mutualFundMapper) {

        this.mutualFundMapper = mutualFundMapper;
    }


    public void setNavHistoryMapper(
            NAVHistoryMapper navHistoryMapper) {

        this.navHistoryMapper = navHistoryMapper;
    }


    // =========================================================
    // ADD FUND
    // =========================================================

    @Override
    public void addFund(MutualFund fund) {

        try {

            if (fund == null) {

                throw new IllegalArgumentException(
                        "Mutual Fund details cannot be empty."
                );
            }

            if (fund.getFundName() == null ||
                    fund.getFundName().trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund name cannot be empty."
                );
            }

            if (fund.getNav() <= 0) {

                throw new IllegalArgumentException(
                        "NAV must be greater than 0."
                );
            }

            if (fund.getMinimumInvestment() <= 0) {

                throw new IllegalArgumentException(
                        "Minimum investment must be greater than 0."
                );
            }

            if (fund.getFundId() == null ||
                    fund.getFundId().trim().isEmpty()) {

                fund.setFundId(
                        IdGeneratorUtil.generateFundId()
                );
            }

            mutualFundMapper.insertFund(fund);

        } catch (IllegalArgumentException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to add mutual fund. Please try again."
            );
        }
    }


    // =========================================================
    // UPDATE FUND
    // =========================================================

    @Override
    public void updateFund(
            MutualFund fund) {

        try {

            if (fund == null ||
                    fund.getFundId() == null ||
                    fund.getFundId().trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Invalid mutual fund details."
                );
            }

            MutualFund existingFund =
                    mutualFundMapper.getFundById(
                            fund.getFundId()
                    );

            if (existingFund == null) {

                throw new MutualFundNotFoundException(
                        "Mutual Fund not found."
                );
            }

            if (fund.getNav() <= 0) {

                throw new IllegalArgumentException(
                        "NAV must be greater than 0."
                );
            }

            if (fund.getMinimumInvestment() <= 0) {

                throw new IllegalArgumentException(
                        "Minimum investment must be greater than 0."
                );
            }

            mutualFundMapper.updateFund(fund);

        } catch (MutualFundNotFoundException e) {

            throw e;

        } catch (IllegalArgumentException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to update mutual fund. Please try again."
            );
        }
    }


    // =========================================================
    // DELETE FUND
    // =========================================================

    @Override
    public void deleteFund(
            String fundId) {

        try {

            if (fundId == null ||
                    fundId.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund ID cannot be empty."
                );
            }

            MutualFund fund =
                    mutualFundMapper.getFundById(
                            fundId
                    );

            if (fund == null) {

                throw new MutualFundNotFoundException(
                        "Mutual Fund not found."
                );
            }

            mutualFundMapper.deleteFund(fundId);

        } catch (MutualFundNotFoundException e) {

            throw e;

        } catch (IllegalArgumentException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to delete mutual fund. Please try again."
            );
        }
    }


    // =========================================================
    // GET FUND BY ID
    // =========================================================

    @Override
    public MutualFund getFundById(
            String fundId) {

        try {

            if (fundId == null ||
                    fundId.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund ID cannot be empty."
                );
            }

            MutualFund fund =
                    mutualFundMapper.getFundById(
                            fundId
                    );

            if (fund == null) {

                throw new MutualFundNotFoundException(
                        "Mutual Fund not found."
                );
            }

            return fund;

        } catch (MutualFundNotFoundException e) {

            throw e;

        } catch (IllegalArgumentException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to retrieve mutual fund."
            );
        }
    }


    // =========================================================
    // GET FUND BY NAME
    // =========================================================

    @Override
    public MutualFund getFundByName(
            String fundName) {

        try {

            if (fundName == null ||
                    fundName.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund name cannot be empty."
                );
            }

            MutualFund fund =
                    mutualFundMapper.getFundByName(
                            fundName
                    );

            if (fund == null) {

                throw new MutualFundNotFoundException(
                        "Mutual Fund not found."
                );
            }

            return fund;

        } catch (MutualFundNotFoundException e) {

            throw e;

        } catch (IllegalArgumentException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to search mutual fund."
            );
        }
    }


    // =========================================================
    // GET FUNDS BY CATEGORY
    // =========================================================

    @Override
    public List<MutualFund> getFundsByCategory(
            String category) {

        try {

            if (category == null ||
                    category.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund category cannot be empty."
                );
            }

            return mutualFundMapper
                    .getFundsByCategory(category);

        } catch (IllegalArgumentException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to retrieve funds."
            );
        }
    }


    // =========================================================
    // GET ALL FUNDS
    // =========================================================

    @Override
    public List<MutualFund> getAllFunds() {

        try {

            return mutualFundMapper.getAllFunds();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to retrieve mutual funds."
            );
        }
    }


    // =========================================================
    // UPDATE NAV
    // =========================================================

    @Override
    public void updateNAV(
            String fundId,
            double newNAV,
            String adminId) {

        try {

            // 1. Validate Fund ID

            if (fundId == null ||
                    fundId.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund ID cannot be empty."
                );
            }


            // 2. Find Fund

            MutualFund mutualFund =
                    mutualFundMapper.getFundById(
                            fundId
                    );

            if (mutualFund == null) {

                throw new MutualFundNotFoundException(
                        "Mutual Fund not found."
                );
            }


            // 3. Validate NAV

            if (newNAV <= 0) {

                throw new IllegalArgumentException(
                        "NAV must be greater than 0."
                );
            }


            // 4. Get Old NAV

            double oldNAV =
                    mutualFund.getNav();


            // 5. Prevent Same NAV

            if (Double.compare(
                    oldNAV,
                    newNAV) == 0) {

                throw new IllegalArgumentException(
                        "New NAV is same as current NAV."
                );
            }


            // 6. Update NAV

            mutualFund.setNav(newNAV);

            mutualFundMapper.updateFund(
                    mutualFund
            );


            // 7. Create NAV History

            NAVHistory history =
                    new NAVHistory();

            history.setHistoryId(
                    IdGeneratorUtil
                            .generateNavHistoryId()
            );

            history.setMutualFund(
                    mutualFund
            );

            history.setOldNav(oldNAV);

            history.setNewNav(newNAV);

            history.setChangeDate(
                    DateUtil.getCurrentDate()
            );

            history.setChangedBy(adminId);


            // 8. Save NAV History

            navHistoryMapper.insertNAVHistory(
                    history
            );

        } catch (MutualFundNotFoundException e) {

            throw e;

        } catch (IllegalArgumentException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to update NAV. Please try again."
            );
        }
    }


    // =========================================================
    // GET CURRENT NAV
    // =========================================================

    @Override
    public double getCurrentNav(
            String fundId) {

        try {

            if (fundId == null ||
                    fundId.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Fund ID cannot be empty."
                );
            }

            MutualFund fund =
                    mutualFundMapper.getFundById(
                            fundId
                    );

            if (fund == null) {

                throw new MutualFundNotFoundException(
                        "Mutual Fund not found."
                );
            }

            return fund.getNav();

        } catch (MutualFundNotFoundException e) {

            throw e;

        } catch (IllegalArgumentException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to retrieve current NAV."
            );
        }
    }
}