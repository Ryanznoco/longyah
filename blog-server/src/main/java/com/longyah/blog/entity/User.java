package com.longyah.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "introduction", length = 50)
    private String introduction;
    @Column(name = "avatar", length = 100)
    private String avatar;
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            })
    private List<Role> roles;
}
