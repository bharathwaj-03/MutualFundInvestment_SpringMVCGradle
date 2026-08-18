package com.crimsonlogic.mutualfundinvestmentspringmvc.controller;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.user.Admin;
import com.crimsonlogic.mutualfundinvestmentspringmvc.services.admin.I_AdminService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/profile")
public class AdminProfileController {


    private final I_AdminService adminService;


    public AdminProfileController(
            I_AdminService adminService) {

        this.adminService =
                adminService;
    }


    // =========================================================
    // EDIT PROFILE PAGE
    // =========================================================

    @GetMapping("/edit")
    public String editProfile(
            HttpSession session,
            Model model) {


        String adminId =
                (String) session.getAttribute(
                        "loggedInAdmin"
                );


        if (adminId == null) {

            return "redirect:/userlogin/admin";
        }


        Admin admin =
                adminService.getAdminByUserId(
                        adminId
                );


        if (admin == null) {

            model.addAttribute(
                    "error",
                    "Admin profile not found."
            );

            return "adminviews/admin-success";
        }


        model.addAttribute(
                "admin",
                admin
        );


        return "adminviews/edit-profile";
    }


    // =========================================================
    // SAVE PROFILE
    // =========================================================

    @PostMapping("/edit")
    public String updateProfile(
            @ModelAttribute Admin admin,
            HttpSession session,
            Model model) {

        String loggedInAdmin =
                (String) session.getAttribute("loggedInAdmin");

        if (loggedInAdmin == null) {
            return "redirect:/userlogin/admin";
        }

        admin.setUserId(loggedInAdmin);

        boolean updated =
                adminService.updateAdminProfile(admin);

        if (!updated) {

            model.addAttribute(
                    "error",
                    "Unable to update profile."
            );

            model.addAttribute(
                    "admin",
                    admin
            );

            return "adminviews/edit-profile";
        }

        Admin updatedAdmin =
                adminService.getAdminByUserId(
                        loggedInAdmin
                );

        model.addAttribute(
                "admin",
                updatedAdmin
        );

        model.addAttribute(
                "success",
                "Profile updated successfully!"
        );

        return "adminviews/edit-profile";
    }
}