package iki.attendance.management.attendance.dto;

public class JwtApiResponse {
    private String tokenType="bearerToken";
    private String token;
    private String username;
    private String role;

    public JwtApiResponse(String tokenType, String token, String username, String role) {
        this.tokenType = tokenType;
        this.token = token;
        this.username = username;
        this.role = role;
    }
    public JwtApiResponse(){

    }

    public String getTokenType() {
        return tokenType;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
