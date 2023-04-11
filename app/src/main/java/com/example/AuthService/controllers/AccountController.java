package com.example.AuthService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AuthService.models.AccountDeleteModel;
import com.example.AuthService.models.AccountUpdateModel;
import com.example.AuthService.services.AccountService;

@RestController
@ResponseBody
public class AccountController {

    @Autowired
    private AccountService accountService;

    @CrossOrigin
    @PutMapping("/account/update/username")
    public ResponseEntity<String> updateUsername(@RequestBody AccountUpdateModel accountUpdateModel) throws Exception {
        return new ResponseEntity<>("Cannot update username", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @CrossOrigin
    @PutMapping("/account/update/password")
    public ResponseEntity<String> updatePassword(@RequestBody AccountUpdateModel accountUpdateModel) throws Exception {
        String username = accountUpdateModel.getUsername();
        String password = accountUpdateModel.getPassword();
        String newPassword = accountUpdateModel.getNewPassword();
        
        try {
            accountService.updatePassword(username, password, newPassword);
            return new ResponseEntity<>("password updated successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Cannot update password Error: "+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/account/update/name")
    public ResponseEntity<String> updateName(@RequestBody AccountUpdateModel accountUpdateModel) throws Exception {
        String username = accountUpdateModel.getUsername();
        String password = accountUpdateModel.getPassword();
        String newFirstName = accountUpdateModel.getNewFirstName();
        String newLastName = accountUpdateModel.getNewLastName();
        
        try {
            accountService.updateName(username, password, newFirstName, newLastName);
            return new ResponseEntity<>("name updated successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Cannot update name Error: "+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/account/update/phone")
    public ResponseEntity<String> updatePhone(@RequestBody AccountUpdateModel accountUpdateModel) throws Exception {
        String username = accountUpdateModel.getUsername();
        String password = accountUpdateModel.getPassword();
        String newPhone = accountUpdateModel.getNewPhone();
        
        try {
            accountService.updatePhone(username, password, newPhone);
            return new ResponseEntity<>("phone updated successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Cannot update phone Error: "+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/account/update/email")
    public ResponseEntity<String> updateEmail(@RequestBody AccountUpdateModel accountUpdateModel) throws Exception {
        String username = accountUpdateModel.getUsername();
        String password = accountUpdateModel.getPassword();
        String newEmail = accountUpdateModel.getNewEmail();
        
        try {
            accountService.updateEmail(username, password, newEmail);
            return new ResponseEntity<>("email updated successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Cannot update email Error: "+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @DeleteMapping("/account/delete")
    public ResponseEntity<String> deleteAccount(@RequestBody AccountDeleteModel accountDeleteModel) throws Exception {
        String username = accountDeleteModel.getUsername();
        String password = accountDeleteModel.getPassword();
        
        try {
            accountService.deleteAccount(username, password);
            return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Cannot delete account Error: "+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
