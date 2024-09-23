package com.example.demo.user.controller;


import com.example.demo.PIAResponse;
import com.example.demo.decorator.DecoratorEntity;
import com.example.demo.owner.OwnerEntity;
import com.example.demo.owner.UserWithStatusDto;
import com.example.demo.user.model.RequestRegistrationEntity;
import com.example.demo.user.model.UserEntity;
import com.example.demo.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    final UserService userService;

    @GetMapping(value = "/getUser/{userId}")
    public PIAResponse<UserEntity> getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @GetMapping(value = "/loginUser/{username}/{password}")
    public PIAResponse<UserEntity> loginUser(
            @PathVariable String username, @PathVariable String password) {
        return userService.loginUser(username, password);
    }

    @GetMapping(value = "/changePassword/{username}/{oldPassword}/{newPassword}")
    public PIAResponse<UserEntity> changePassword(
            @PathVariable String username, @PathVariable String oldPassword, @PathVariable String newPassword) {
        return userService.changePassword(username, oldPassword, newPassword);
    }

    @PostMapping(value = "/registerUser", consumes = "multipart/form-data")
    public PIAResponse<UserEntity> registerUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("gender") String gender,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            @RequestParam("creditCardNumber") String creditCardNumber,
            @RequestParam("userType") String userType,
            @RequestParam("contactPhone") String contactPhone,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture
    ) throws IOException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(gender);
        userEntity.setAddress(address);
        userEntity.setEmail(email);
        userEntity.setCreditCardNumber(creditCardNumber);
        userEntity.setUserType(userType);
        userEntity.setContactPhone(contactPhone);

        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                userEntity.setProfilePicture(profilePicture.getBytes());
            } catch (Exception e) {
                return null;
            }
        } else {
            InputStream in = getClass().getResourceAsStream("/images/znak_pitanja.jpg");
            assert in != null;
            userEntity.setProfilePicture(in.readAllBytes());
        }

        return userService.registerUser(userEntity);
    }

    @PutMapping(value = "/updateUser", consumes = "multipart/form-data")
    public PIAResponse<UserEntity> updateUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("gender") String gender,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            @RequestParam("creditCardNumber") String creditCardNumber,
            @RequestParam("userType") String userType,
            @RequestParam("contactPhone") String contactPhone,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture
    ) throws IOException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(gender);
        userEntity.setAddress(address);
        userEntity.setEmail(email);
        userEntity.setCreditCardNumber(creditCardNumber);
        userEntity.setUserType(userType);
        userEntity.setContactPhone(contactPhone);

        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                userEntity.setProfilePicture(profilePicture.getBytes());
            } catch (Exception e) {
                return null;
            }
        }

        return userService.updateUser(userEntity);
    }

    @GetMapping(value = "/addDecorator/{userId}/{firmId}")
    public PIAResponse<DecoratorEntity> addDecorator(@PathVariable Integer userId, @PathVariable Long firmId) {
        return userService.addDecorator(userId, firmId);
    }

    @GetMapping(value = "/getAllOwners")
    public PIAResponse<List<UserWithStatusDto>> getAllOwners() {
        return userService.getAllOwners();
    }

    @GetMapping(value = "/getAllDecorators")
    public PIAResponse<List<DecoratorEntity>> getAllDecorators() {
        return userService.getAllDecorators();
    }

    @GetMapping(value = "/approveRegistration/{userId}")
    public PIAResponse<RequestRegistrationEntity> approveRegistration(@PathVariable Integer userId) {
        return userService.approveRegistration(userId);
    }

    @GetMapping(value = "/rejectRegistration/{userId}")
    public PIAResponse<RequestRegistrationEntity> rejectRegistration(@PathVariable Integer userId) {
        return userService.rejectRegistration(userId);
    }

}
