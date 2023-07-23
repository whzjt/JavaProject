package com.tyj.StudentManagementSystemV2;

public class UserPhone {
    private String userName;
    private String password;
    private String idCard;
    private String phoneNumber;

    public UserPhone() {
    }

    public UserPhone(String userName, String password, String idCard, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
