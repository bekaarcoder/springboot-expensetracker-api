package com.springboot.expenseapi.service;

import com.springboot.expenseapi.entity.User;
import com.springboot.expenseapi.entity.UserModel;
import com.springboot.expenseapi.entity.UserUpdateModel;

public interface UserService {

    User createUser(UserModel userModel);

    User getUser();

    User updateUser(UserUpdateModel userModel);

    void deleteUser();

    User getLoggedInUser();
}
