package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.financeactivity.SIP;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SIPMapper {

    void insertSIP(SIP sip);

    void updateSIP(SIP sip);

    void deleteSIP(
            @Param("sipId")
            String sipId
    );

    SIP getSIPById(
            @Param("sipId")
            String sipId
    );

    List<SIP> getSIPsByUserId(
            @Param("userId")
            String userId
    );

    List<SIP> getAllSIPs();
}