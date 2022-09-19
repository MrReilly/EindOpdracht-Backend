package com.example.EindOpdrachtBackend.dto;


public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private String defaultLocation;

    public UserDto() {
    }
    public UserDto(Long id, String userName, String password, String defaultLocation) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.defaultLocation = defaultLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }
}
