package com.example.AuthService.interceptors;

import com.example.AuthService.constants.AuthorizationConstants;
import com.example.AuthService.constants.CommonConstants;
import com.example.AuthService.enums.AllowedUrlTestType;
import com.example.AuthService.services.AuthorizationService;
import com.example.AuthService.services.AuthorizationServiceImpl;
import com.example.AuthService.utils.InterceptorUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthorizationInterceptor extends InterceptorBase implements HandlerInterceptor {

    public AuthorizationInterceptor() {
        super(AllowedUrlTestType.BLACKLISTING_CHECK, new String[] {
                "/api/v1/login",
                "/api/v1/signup",
                "/api/v1/health"
        });
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        if( !InterceptorUtils.checkIfUrlIsAllowed(uri, whitelistedUris, blacklistedUris))
            return true;


        String authToken = request.getHeader( AuthorizationConstants.AUTHORIZATION_HEADER );
        if(authToken == null || !authToken.startsWith(AuthorizationConstants.AUTHORIZATION_PREFIX)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            System.out.println("Authorization format is wrong"); // TODO Add log statement
            return false;
        }

        String authType = request.getHeader(AuthorizationConstants.AUTHORIZATION_TYPE_HEADER);
        authType = ( authType == null ) ? "" : authType;

        try {
            AuthorizationService authorizationService = new AuthorizationServiceImpl(authType);
            String token = authToken.substring(AuthorizationConstants.AUTHORIZATION_PREFIX.length());
            String username = authorizationService.getUniqueIdFromToken(token);
            request.setAttribute(CommonConstants.UNIQUE_ID, username);
            System.out.println("User authorised"); // TODO Add log statement
            return true;
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            System.out.println("User unauthorized Error: "+e.getMessage()); // TODO Add log statement
            return false;
        }

    }
}
