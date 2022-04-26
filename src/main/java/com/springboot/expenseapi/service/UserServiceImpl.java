package com.springboot.expenseapi.service;

import com.springboot.expenseapi.entity.User;
import com.springboot.expenseapi.entity.UserModel;
import com.springboot.expenseapi.entity.UserUpdateModel;
import com.springboot.expenseapi.exceptions.ItemAlreadyExistsException;
import com.springboot.expenseapi.exceptions.ResourceNotFoundException;
import com.springboot.expenseapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder bcryptEncoder;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserModel userModel) {
        if(userRepository.existsByEmail(userModel.getEmail())) {
            throw new ItemAlreadyExistsException("User already exists with email " + userModel.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUser() {
        return userRepository.findById(getLoggedInUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + getLoggedInUser().getId()));
    }

    @Override
    public User updateUser(UserUpdateModel userModel) {
        User existingUser = getLoggedInUser();
        existingUser.setName(userModel.getName() != null ? userModel.getName() : existingUser.getName());
        existingUser.setEmail(userModel.getEmail() != null ? userModel.getEmail() : existingUser.getEmail());
        existingUser.setAge(userModel.getAge() != null ? userModel.getAge() : existingUser.getAge());
        existingUser.setPassword(userModel.getPassword() != null ? bcryptEncoder.encode(userModel.getPassword()) : existingUser.getPassword());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser() {
        User existingUser = getLoggedInUser();
        userRepository.delete(existingUser);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email: " + email));
    }
}
