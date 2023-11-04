package com.tudg.basic;

public class Admin {
    private String adminId;
    private String adminPassWord;
    private String name;

    public Admin(String adminId, String adminPassWord, String name) {
        this.adminId = adminId;
        this.adminPassWord = adminPassWord;
        this.name = name;
    }

    public Admin() {
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setAdminPassWord(String adminPassWord) {
        this.adminPassWord = adminPassWord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getAdminPassWord() {
        return adminPassWord;
    }

    public String getName() {
        return name;
    }
}
