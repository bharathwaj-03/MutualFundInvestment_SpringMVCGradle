package com.crimsonlogic.mutualfundinvestmentspringmvc.services.admin;

import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.AdminMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.dao.UserMapper;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.User;
import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Admin;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.DateUtil;
import com.crimsonlogic.mutualfundinvestmentspringmvc.utilities.security.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AdminService implements I_AdminService {

    private AdminMapper adminMapper;

    private UserMapper userMapper;


    // =========================================================
    // SETTER INJECTION
    // =========================================================

    public void setAdminMapper(
            AdminMapper adminMapper) {

        this.adminMapper = adminMapper;
    }


    public void setUserMapper(
            UserMapper userMapper) {

        this.userMapper = userMapper;
    }


    // =========================================================
    // INITIALIZE DEFAULT ADMINS
    // =========================================================

    @PostConstruct
    public void initializeAdmins() {

        System.out.println(
                "\n===== INITIALIZING ADMINS ====="
        );


        createAdmin(
                "ADM001",
                "Deepak",
                "Deep@37",
                "A001",
                "8909878678"
        );


        createAdmin(
                "ADM002",
                "Rahul",
                "Rahul@37",
                "A002",
                "7865674567"
        );


        createAdmin(
                "ADM003",
                "Manager",
                "Manager@37",
                "A003",
                "6789098789"
        );


        System.out.println(
                "===== ADMIN INITIALIZATION COMPLETE =====\n"
        );
    }


    // =========================================================
    // CREATE ADMIN
    // =========================================================

    private void createAdmin(
            String userId,
            String name,
            String password,
            String adminCode,String phNo) {


        // =====================================================
        // CHECK WHETHER ADMIN ALREADY EXISTS
        // =====================================================

        Admin existingAdmin =
                adminMapper.getAdminByUserId(
                        userId
                );


        if (existingAdmin != null) {

            System.out.println(
                    "Admin "
                            + userId
                            + " already exists. Skipping."
            );

            return;
        }


        // =====================================================
        // CREATE ADMIN OBJECT
        // =====================================================

        Admin admin =
                new Admin();


        admin.setUserId(
                userId
        );


        admin.setName(
                name
        );


        admin.setPassword(
                PasswordUtil.hashPassword(
                        password
                )
        );


        admin.setEmail(
                name.toLowerCase()
                        + "@gmail.com"
        );


        admin.setPhoneNumber(
              phNo
        );


        admin.setUserRole(
                "ADMIN"
        );


        admin.setAdminCode(
                adminCode
        );


        admin.setCreatedDate(
                DateUtil.getCurrentDate()
        );


        // =====================================================
        // CHECK PARENT USER
        // =====================================================

        User existingUser =
                userMapper.getUserById(
                        userId
                );


        if (existingUser == null) {

            userMapper.insertUser(
                    admin
            );

            System.out.println(
                    "User record created for "
                            + userId
            );
        }


        // =====================================================
        // INSERT ADMIN CHILD RECORD
        // =====================================================

        adminMapper.insertAdmin(
                admin
        );


        System.out.println(
                "Admin "
                        + userId
                        + " created successfully."
        );
    }


    // =========================================================
    // ADMIN AUTHENTICATION
    // =========================================================

    @Override
    public boolean authenticateAdmin(
            String userId,
            String password) {


        Admin admin =
                adminMapper.getAdminByUserId(
                        userId
                );


        if (admin == null) {

            System.out.println(
                    "Admin not found."
            );

            return false;
        }


        // =====================================================
        // VERIFY HASHED PASSWORD
        // =====================================================

        boolean validPassword =
                PasswordUtil.verifyPassword(
                        password,
                        admin.getPassword()
                );


        if (!validPassword) {

            System.out.println(
                    "Incorrect Password."
            );

            return false;
        }


        System.out.println(
                "\nWelcome Admin "
                        + admin.getName()
        );


        return true;
    }
}