package com.longyah.blog.api;

import com.longyah.blog.config.SecurityProperties;
import com.longyah.blog.form.LoginForm;
import com.longyah.blog.security.JwtTokenUtil;
import com.longyah.blog.security.JwtUser;
import com.longyah.blog.utils.CaptchaUtil;
import com.longyah.blog.vo.ResultVo;
import com.longyah.blog.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author RenQiang
 * @date 2019/6/4
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthorizationApi {
    private static final String CAPTCHA_SESSION_KEY = "captcha";

    @Autowired
    private AuthenticationManager authenticationManager;
    @Qualifier("jwtUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SecurityProperties securityProperties;

    @PostMapping("/login")
    public ResultVo<TokenVo> login(@Validated @RequestBody LoginForm loginForm) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return new ResultVo<>(TokenVo.builder().token(token).build());
    }

    @GetMapping("/refreshToken")
    public ResultVo<TokenVo> refresh(HttpServletRequest request) {
        String authToken = request.getHeader(securityProperties.getJwtTokenHeader());
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return new ResultVo<>(TokenVo.builder().token(refreshedToken).build());
        }
        throw new CredentialsExpiredException("Token已过期");
    }

    @PostMapping("/logout")
    public ResultVo<Void> logout() {
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
