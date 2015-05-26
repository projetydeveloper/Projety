package com.projety.model;

/**
 * Created by Djeme Mahamat on 18/02/2015.
 */
public class User {

    private String name;
    private String email;
    private int age;
    private String sex;
    private String loginProvider;
    private String loginId;

    public static final String SEX_MALE="MALE";
    public static final String SEX_FEMALE="FEMALE";
    public static final String LOGIN_PROVIDER_TWITTER="TWITTER";
    public static final String LOGIN_PROVIDER_FACEBOOK="FACEBOOK";
    public static final String LOGIN_PROVIDER_MAIL="MAIL";


    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLoginProvider() {
        return loginProvider;
    }

    public void setLoginProvider(String loginProvider) {
        this.loginProvider = loginProvider;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
