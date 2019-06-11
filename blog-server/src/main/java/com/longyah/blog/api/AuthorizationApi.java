package com.longyah.blog.api;

import com.longyah.blog.dto.ResultDto;
import com.longyah.blog.entity.User;
import com.longyah.blog.form.LoginForm;
import com.longyah.blog.service.UserService;
import com.longyah.blog.utils.CaptchaUtil;
import com.longyah.blog.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.longyah.blog.consts.ResponseCodeConsts.*;

/**
 * @author RenQiang
 * @date 2019/6/4
 */
@Validated
@RestController
@RequestMapping(value = "/api/auth")
public class AuthorizationApi {
    private static final String CAPTCHA_SESSION_KEY = "captcha";

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultVo<Void> login(@Validated @RequestBody LoginForm loginForm) {
        ResultDto<User> loginResult = userService.login(loginForm.getUsername(), loginForm.getPassword());
        if (!loginResult.isSuccess()) {
            return new ResultVo<>(LOGIN_FAIL.getCode(), "登录失败，用户名或密码错误");
        }
        return new ResultVo<>();
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置响应头信息，通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        response.setContentType("image/jpeg");

        // 获取验证码
        String code = CaptchaUtil.getRandomCode();
        // 将验证码输入到session中，用来验证
        request.getSession().setAttribute(CAPTCHA_SESSION_KEY, code);
        // 输出到web页面
        ImageIO.write(CaptchaUtil.genCaptcha(code), "jpg", response.getOutputStream());
    }
}
