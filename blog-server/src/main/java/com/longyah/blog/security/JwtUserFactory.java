package com.longyah.blog.security;

import com.longyah.blog.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author RenQiang
 * @date 2019-06-13
 */
public final class JwtUserFactory {

    private JwtUserFactory() {

    }

    public static JwtUser create(User user) {
        JwtUser jwtUser = new JwtUser();
        BeanUtils.copyProperties(user, jwtUser);
        jwtUser.setAuthorities(user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().name()))
                .collect(Collectors.toList()));
        return jwtUser;
    }

}
