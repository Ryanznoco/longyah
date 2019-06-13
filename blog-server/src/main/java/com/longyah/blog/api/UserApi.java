package com.longyah.blog.api;

import com.longyah.blog.config.SecurityProperties;
import com.longyah.blog.entity.User;
import com.longyah.blog.security.JwtTokenUtil;
import com.longyah.blog.security.JwtUser;
import com.longyah.blog.service.UserService;
import com.longyah.blog.vo.ResultVo;
import com.longyah.blog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.stream.Collectors;

/**
 * @author RenQiang
 * @date 2019/6/12
 */
@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Qualifier("jwtUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/info")
    public ResultVo<UserVo> info(HttpServletRequest request) {
        String token = request.getHeader(securityProperties.getJwtTokenHeader()).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);

        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(username);
        User user = userService.getById(jwtUser.getId());
        return new ResultVo<>(UserVo.builder()
                .name(user.getUsername())
                .introduction(user.getIntroduction())
                .avatar(user.getAvatar())
                .roles(user.getAuthorities().stream()
                        .map(authority -> authority.getRole().name())
                        .collect(Collectors.toList()))
                .build());
    }
}
