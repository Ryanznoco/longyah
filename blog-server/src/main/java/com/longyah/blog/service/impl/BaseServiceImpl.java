package com.longyah.blog.service.impl;

import com.longyah.blog.entity.IdEntity;
import com.longyah.blog.repository.BaseRepository;
import com.longyah.blog.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author RenQiang
 * @date 2019/6/5
 */
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BaseServiceImpl<E extends IdEntity, R extends BaseRepository<E>> implements BaseService<E> {
    @Autowired
    protected R repository;

    @Override
    public E getById(Long id) {
        return repository.getOne(id);
    }
}
