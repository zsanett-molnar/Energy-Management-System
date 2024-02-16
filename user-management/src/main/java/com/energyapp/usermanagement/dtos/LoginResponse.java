package com.energyapp.usermanagement.dtos;

public class LoginResponse {
    private UserDetailsDTO userDTO;
    private String token;

    public LoginResponse(){
    }

    public LoginResponse(UserDetailsDTO userDTO, String token) {
        this.userDTO = userDTO;
        this.token = token;
    }

    public UserDetailsDTO getUserDto() {
        return userDTO;
    }

    public void setUserDto(UserDetailsDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "userDTO=" + userDTO +
                ", token='" + token + '\'' +
                '}';
    }
}
