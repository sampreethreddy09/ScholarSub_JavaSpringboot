package com.demo.demo.dto;

public class LoginRequest {
    private String uname;
    private String pswd;
    private int role;
    
    public LoginRequest(){
        
    }

    public LoginRequest(String uname, String pswd, int role) {
        this.uname = uname;
        this.pswd = pswd;
        this.role = role;
    }

    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPswd() {
        return pswd;
    }
    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }

    
}
