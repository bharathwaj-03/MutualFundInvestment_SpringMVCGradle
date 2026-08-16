package com.crimsonlogic.mutualfundinvestmentspringmvc.model.user;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.User;

import java.time.LocalDate;
import java.util.Objects;

public class Admin extends User {


    private String adminCode;
private LocalDate createdDate;

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Admin() {

    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }



    public Admin(String adminCode) {
        this.adminCode = adminCode;
    }
    //
    public Admin(String userId, String name, String email, String phoneNumber,int age, String adminCode,String password) {
        super(userId, name, email, phoneNumber,age,password);
        this.adminCode = adminCode;

    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", adminCode='" + adminCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(adminCode, admin.adminCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminCode);
    }
}