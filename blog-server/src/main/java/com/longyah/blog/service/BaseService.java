package com.longyah.blog.service;

import com.longyah.blog.entity.IdEntity;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
public interface BaseService<E extends IdEntity> {

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    E getById(Long id);
}
