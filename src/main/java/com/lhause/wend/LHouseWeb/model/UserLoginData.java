package com.lhause.wend.LHouseWeb.model;

import org.springframework.stereotype.Component;

/**
 *
 * @author Wendley S
 */
@Component
public class UserLoginData {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
