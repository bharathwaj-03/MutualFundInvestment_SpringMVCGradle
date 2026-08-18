package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Nominee;
import org.apache.ibatis.annotations.Param;

public interface NomineeMapper {

    void insertNominee(Nominee nominee);

    Nominee getNomineeById(
            @Param("nomineeId")
            String nomineeId);

    void updateNominee(Nominee nominee);

    void deleteNominee(
            @Param("nomineeId")
            String nomineeId);


}