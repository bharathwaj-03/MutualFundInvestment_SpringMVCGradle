package com.crimsonlogic.mutualfundinvestmentspringmvc.dao;

import com.crimsonlogic.mutualfundinvestmentspringmvc.model.abstraction.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    void insertUser(User user);


    User getUserById(
            @Param("userId") String userId);

    void updateUserProfile(User user);

}