package com.example.AuthService.utils;

public class InterceptorUtils {

    public static boolean checkIfUrlIsAllowed(String uri, String[] whitelistedUris, String[] blacklistedUris) {
        if( blacklistedUris == null && whitelistedUris == null )
            return true;

        if( whitelistedUris == null ) {
            for( String blacklistedUri : blacklistedUris ) {
                if( blacklistedUri.equals(uri) )
                    return false;
            }
            return true;
        } else {
            for( String whitelistedUri : whitelistedUris ) {
                if( whitelistedUri.equals(uri) )
                    return true;
            }
            return false;
        }
    }
}
