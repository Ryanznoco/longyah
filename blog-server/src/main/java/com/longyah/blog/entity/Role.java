package com.longyah.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author RenQiang
 * @date 2019/6/12
 */
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends IdEntity {
    @Column(name = "code", length = 20, nullable = false, unique = true)
    private String code;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "description", length = 100)
    private String description;
}
