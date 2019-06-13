package com.longyah.blog.entity;

import com.longyah.blog.consts.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author RenQiang
 * @date 2019-06-13
 */
@Getter
@Setter
@Entity
@Table(name = "role")
public class Authority extends IdEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false, unique = true)
    private RoleEnum role;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "description", length = 50)
    private String description;
}
