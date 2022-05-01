package com.example.mop125;

public class userAccount {
    private String emailId; // firebase 고유 토큰 정보 -> 사용자 하나 계정만 갖는 key 값
    private String password;
    private String IdToken;
    private String univ;
    private String age;
    private String sleepTime;
    private String wakeupTime;
    //정보 확장은 여기에서 가능합니다!!! 구현할 체크리스트 종류는 여기에 만들면 될 것 같아요

    public userAccount() { }

    public String getEmailId() { return emailId; }

    public String getPassword() { return password; }

    public void setEmailId(String emailId) { this.emailId = emailId; }

    public void setPassword(String password) { this.password = password; }

    public String getIdToken() { return IdToken; }

    public void setIdToken(String idToken) { IdToken = idToken; }
}
