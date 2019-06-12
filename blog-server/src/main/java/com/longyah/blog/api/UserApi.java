package com.longyah.blog.api;

import com.longyah.blog.consts.SessionKeyConsts;
import com.longyah.blog.entity.Role;
import com.longyah.blog.entity.User;
import com.longyah.blog.service.UserService;
import com.longyah.blog.vo.ResultVo;
import com.longyah.blog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.util.stream.Collectors;

import static com.longyah.blog.consts.ResponseCodeConsts.*;

/**
 * @author RenQiang
 * @date 2019/6/12
 */
@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResultVo<UserVo> info(String token, HttpSession session) {
        if (!session.getId().equals(token)) {
            return new ResultVo<>(ILLEGAL_TOKEN.getCode(), "Token无效");
        }
        Long userId = (Long) session.getAttribute(SessionKeyConsts.USER_ID_SESSION_KEY);
        User user = userService.getById(userId);
        return new ResultVo<>(UserVo.builder()
                .name(user.getUsername())
                .introduction(user.getIntroduction())
                .avatar(user.getAvatar())
                .roles(user.getRoles().stream().map(Role::getCode).collect(Collectors.toList()))
                .build());
    }
}
