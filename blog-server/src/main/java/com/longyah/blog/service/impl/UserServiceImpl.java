package com.longyah.blog.service.impl;

import com.longyah.blog.dto.ResultDto;
import com.longyah.blog.entity.User;
import com.longyah.blog.repository.UserRepository;
import com.longyah.blog.service.UserService;
import com.longyah.blog.utils.EncryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService {

    @Override
    public ResultDto<User> login(String username, String password) {
        User user = repository.findByUsername(username);
        if (user == null) {
            return new ResultDto<>("用户名不存在");
        }
        if (EncryptUtil.validatePassword(password, user.getPassword())) {
            return new ResultDto<>(user);
        }
        return new ResultDto<>("密码错误");
    }
}
