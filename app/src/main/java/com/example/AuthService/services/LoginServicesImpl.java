package com.example.AuthService.services;

import com.example.AuthService.models.login.LoginResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AuthService.entities.AuthData;
import com.example.AuthService.models.login.LoginModel;
import com.example.AuthService.utils.PasswordUtil;

@AllArgsConstructor
@Service
public class LoginServicesImpl implements LoginService {
    @Autowired
    private AuthDataRepositoryHandler repo;

    @Autowired
    private AuthorizationService authorizationService;

    public LoginResponseModel handleLogin(LoginModel loginModel, String authType) throws Exception {
        String username = loginModel.getUsername();
        String password = PasswordUtil.getEncodedPassword(username, loginModel.getPassword());

        AuthData authData = repo.findById(username);
        if( authData == null )
            throw new Exception("Username does not exist!");

        if( authData.getPassword() == null || authData.getPassword().equals("") )
            throw new Exception("Password cannot be null or empty!");

        if( !authData.getPassword().equals(password) ) {
            throw new Exception( "Wrong password !!" );
        }

        AuthorizationService authorizationService = new AuthorizationServiceImpl(authType);
        String token = authorizationService.createAuthorizationToken(username);
        return new LoginResponseModel() {{
            setUniqueId(username);
            setJwtToken(token);
            setExpiresAt(authorizationService.getExpiresAtFromToken(token));
        }};
    }

    @Override
    public LoginResponseModel handleAuthTokenVerification(String token) throws Exception {
        String username = authorizationService.getUniqueIdFromToken(token);
        long expiresAt = authorizationService.getExpiresAtFromToken(token);
        return new LoginResponseModel() {{
            setUniqueId(username);
            setJwtToken(token);
            setExpiresAt(expiresAt);
        }};
    }

    @Override
    public LoginResponseModel handleAuthTokenRefresh(String token) throws Exception {
        String newToken = authorizationService.refreshToken(token);
        String username = authorizationService.getUniqueIdFromToken(newToken);
        long expiresAt = authorizationService.getExpiresAtFromToken(newToken);

        return new LoginResponseModel() {{
            setUniqueId(username);
            setJwtToken(newToken);
            setExpiresAt(expiresAt);
        }};
    }
}
