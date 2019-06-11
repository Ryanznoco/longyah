package com.longyah.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
@Getter
@Setter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class IdEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
