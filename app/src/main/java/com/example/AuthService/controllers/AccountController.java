package com.example.AuthService.controllers;

import com.example.AuthService.constants.CommonConstants;
import com.example.AuthService.models.account.AccountDetailsModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AuthService.models.account.AccountDeleteModel;
import com.example.AuthService.models.account.AccountUpdateModel;
import com.example.AuthService.services.AccountService;

@RestController
@RequestMapping("/api/v1/account")
@ResponseBody
@AllArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;

    @CrossOrigin
    @GetMapping("/get/username")
    public ResponseEntity<String> getUsername(HttpServletRequest request) {
        return new ResponseEntity<>(request.getAttribute(CommonConstants.UNIQUE_ID).toString(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/get/user-details")
    public ResponseEntity<Object> getUserDetails(HttpServletRequest request) {
        String username = request.getAttribute(CommonConstants.UNIQUE_ID).toString();
        try {
            AccountDetailsModel accountDetailsModel = accountService.getUserDetails(username);
            return new ResponseEntity<>(accountDetailsModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot find user details Error: "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/update/username")
    public ResponseEntity<String> updateUsername(@RequestBody AccountUpdateModel accountUpdateModel) {
        return new ResponseEntity<>("Cannot update username", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @CrossOrigin
    @PutMapping("/update/password")
    public ResponseEntity<String> updatePassword(HttpServletRequest request, @RequestBody AccountUpdateModel accountUpdateModel) {
        String username = request.getAttribute(CommonConstants.UNIQUE_ID).toString();

        String password = accountUpdateModel.getPassword();
        String newPassword = accountUpdateModel.getNewPassword();
        
        try {
            accountService.updatePassword(username, password, newPassword);
            return new ResponseEntity<>("password updated successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Cannot update password Error: "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/update/name")
    public ResponseEntity<String> updateName(HttpServletRequest request, @RequestBody AccountUpdateModel accountUpdateModel) {
        String username = request.getAttribute(CommonConstants.UNIQUE_ID).toString();

        String newFirstName = accountUpdateModel.getNewFirstName();
        String newLastName = accountUpdateModel.getNewLastName();
        
        try {
            accountService.updateName(username, newFirstName, newLastName);
            return new ResponseEntity<>("name updated successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Cannot update name Error: "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/update/phone")
    public ResponseEntity<String> updatePhone(HttpServletRequest request, @RequestBody AccountUpdateModel accountUpdateModel) {
        String username = request.getAttribute(CommonConstants.UNIQUE_ID).toString();

        String newPhone = accountUpdateModel.getNewPhone();
        
        try {
            accountService.updatePhone(username, newPhone);
            return new ResponseEntity<>("phone updated successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Cannot update phone Error: "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/update/email")
    public ResponseEntity<String> updateEmail(HttpServletRequest request, @RequestBody AccountUpdateModel accountUpdateModel) {
        String username = request.getAttribute(CommonConstants.UNIQUE_ID).toString();

        String newEmail = accountUpdateModel.getNewEmail();
        
        try {
            accountService.updateEmail(username, newEmail);
            return new ResponseEntity<>("email updated successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Cannot update email Error: "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(HttpServletRequest request, @RequestBody AccountDeleteModel accountDeleteModel) {
        String username = request.getAttribute(CommonConstants.UNIQUE_ID).toString();

        String password = accountDeleteModel.getPassword();
        
        try {
            accountService.deleteAccount(username, password);
            return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Cannot delete account Error: "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
