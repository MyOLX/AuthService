package com.example.AuthService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_auth_data")
public class AuthData {
    @Id
    @Column(nullable = false, unique = true, length = 100, columnDefinition = "VARCHAR(100)")
    private String username;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String createdAt;
    private String lastUpdatedAt;
    private String publicToken;
    private String privateToken;
}
