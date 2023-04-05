package com.example.AuthService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_auth_data")
public class AuthData {
    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String createdAt;
    private String lastUpdatedAt;
    private String publicToken;
    private String privateToken;
}
