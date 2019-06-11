package com.longyah.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends IdEntity {
    @Column(name = "username", unique = true, length = 16, nullable = false)
    private String username;
    @Column(name = "password", length = 40, nullable = false)
    private String password;
}
