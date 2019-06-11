package com.longyah.blog.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
@Data
public class LoginForm {
    @NotEmpty(message = "{user.name.empty}")
    private String username;
    @NotEmpty(message = "{password.empty}")
    private String password;
}
