package com.crimsonlogic.mutualfundinvestmentspringmvc.services.admin;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Admin;

public interface I_AdminService {

    boolean authenticateAdmin(
            String userId,
            String password
    );

    Admin getAdminByUserId(
            String userId
    );

    boolean updateAdminProfile(
            Admin admin
    );
}