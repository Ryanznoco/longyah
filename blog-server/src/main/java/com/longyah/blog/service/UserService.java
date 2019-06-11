package com.longyah.blog.service;

import com.longyah.blog.dto.ResultDto;
import com.longyah.blog.entity.User;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
public interface UserService extends BaseService<User> {

    /**
     * 登录操作
     *
     * @param username
     * @param password
     * @return
     */
    ResultDto<User> login(String username, String password);
}
