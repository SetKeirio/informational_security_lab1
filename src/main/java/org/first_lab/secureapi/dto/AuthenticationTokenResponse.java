package org.first_lab.secureapi.dto;

public class AuthenticationTokenResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private String userLogin;
    private String userFullName;
    private long expiresIn;

    public AuthenticationTokenResponse() {
    }

    public AuthenticationTokenResponse(String accessToken, String userLogin, String userFullName, long expiresIn) {
        this.accessToken = accessToken;
        this.userLogin = userLogin;
        this.userFullName = userFullName;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
