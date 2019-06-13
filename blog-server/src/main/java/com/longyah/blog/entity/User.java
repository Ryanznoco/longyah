package com.longyah.blog.entity;

import com.longyah.blog.entity.Authority;
import com.longyah.blog.entity.IdEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
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
    @Column(name = "password", length = 60, nullable = false)
    private String password;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;
    @Column(name = "introduction", length = 50)
    private String introduction;
    @Column(name = "avatar", length = 100)
    private String avatar;
    @ColumnDefault("1")
    @Column(name = "enable", nullable = false)
    private Boolean enable;
    @ColumnDefault("0")
    @Column(name = "expired", nullable = false)
    private Boolean expired;
    @ColumnDefault("0")
    @Column(name = "locked", nullable = false)
    private Boolean locked;
    @ColumnDefault("now()")
    @Column(name = "last_password_reset_date")
    private Date lastPasswordResetDate;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            })
    private List<Authority> authorities;
}
