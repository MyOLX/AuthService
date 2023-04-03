package com.example.AuthService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AuthService.entities.AuthData;

@Repository
public interface AuthDataRepository extends JpaRepository<AuthData, String> {

}
