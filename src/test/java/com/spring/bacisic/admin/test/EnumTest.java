package com.spring.bacisic.admin.test;

import com.spring.bacisic.admin.models.sys.entity.enums.UserLoginFlagEnum;

public class EnumTest {
    public static void main(String[] args) {
        UserLoginFlagEnum userLoginFlagEnum = UserLoginFlagEnum.valueOf("normal");
        System.out.println(userLoginFlagEnum);
    }
}
