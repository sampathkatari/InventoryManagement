package com.inventorymanagement.model.ui;

/**
 * Created by sampathkatari on 18/06/18.
 */
public class UserDto {
    private String username;
    private String password;
    public UserDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
