package com.longyah.blog.repository;

import com.longyah.blog.entity.User;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
public interface UserRepository extends BaseRepository<User> {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    User findByUsername(String username);
}
