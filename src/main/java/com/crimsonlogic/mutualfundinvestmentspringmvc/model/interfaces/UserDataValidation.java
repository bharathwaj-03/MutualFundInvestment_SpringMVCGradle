package com.crimsonlogic.mutualfundinvestmentspringmvc.model.interfaces;

import com.crimsonlogic.mutualfundinvestmentspringmvc.exception.UserDataValidationException;

//validate all the user entered data includes name,phoneNum,email,pancard, upiID etc.

@FunctionalInterface
public interface UserDataValidation  {
    String validate(String str1) throws UserDataValidationException;
}
