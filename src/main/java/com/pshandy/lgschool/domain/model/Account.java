package com.pshandy.lgschool.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(columnDefinition="TEXT", name = "account_login")
    private String login;

    @Column(columnDefinition="TEXT", name = "account_password", nullable = false)
    private String password;

    @Column(columnDefinition="TEXT", name = "account_role", nullable = false)
    private String role;

}
