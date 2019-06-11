package com.longyah.blog.repository;

import com.longyah.blog.entity.IdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
@NoRepositoryBean
public interface BaseRepository<E extends IdEntity> extends JpaRepository<E, String>, JpaSpecificationExecutor<E> {
}
