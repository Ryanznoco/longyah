package com.longyah.blog.security;

import com.longyah.blog.config.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author RenQiang
 * @date 2019-06-13
 */
@Getter
@Setter
public class JwtUser implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Boolean enable;
    private Boolean expired;
    private Boolean locked;
    private List<? extends GrantedAuthority> authorities;
    private Date lastPasswordResetDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return System.currentTimeMillis() - lastPasswordResetDate.getTime() < SecurityProperties.PASSWORD_EXPIRE_DAYS * 24 * 60 * 60;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
