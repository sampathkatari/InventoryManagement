package com.inventorymanagement.model.ui;

/**
 * Created by sampathkatari on 18/06/18.
 */
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String dob;
    private String gender;
    private String password;
    public UserDto() {

    }

    private UserDto(Builder builder) {
        setId(builder.id);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setUserName(builder.userName);
        setDob(builder.dob);
        setGender(builder.gender);
        setPassword(builder.password);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static final class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String userName;
        private String dob;
        private String gender;
        private String password;

        private Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder dob(String val) {
            dob = val;
            return this;
        }

        public Builder gender(String val) {
            gender = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
