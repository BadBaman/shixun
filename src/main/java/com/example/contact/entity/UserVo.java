package com.example.contact.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class UserVo {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotEmpty(message="用户名不能为空！")
    @Id
    private String userName;


    @Size(min=6,max=16,message = "密码长度必须6到16位")
    private String password;


    @Override
    public String toString() {
        return "{" +"name='" + getUserName() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }

    @Id
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
}
