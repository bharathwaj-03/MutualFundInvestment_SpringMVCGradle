package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    void insertAdmin(Admin admin);

    Admin authenticateAdmin(
            @Param("userId") String userId,
            @Param("password") String password,
            @Param("role") String role);

    Admin getAdminByUserId(
            @Param("userId")
            String userId);

    List<Admin> getAllAdmins();

}