package com.chengw.tiafs.controller;


import com.chengw.common.config.cookie.CookieConfigProperties;
import com.chengw.common.models.vo.CommonResponse;
import com.chengw.common.models.vo.UserVO;
import com.chengw.common.utils.CookieUtils;
import com.chengw.common.utils.JwtTokenUtil;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author chengw
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CookieConfigProperties cookieConfigProperties;


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @PostMapping("")
    public @ResponseBody
    CommonResponse login(@RequestBody UserVO user, HttpServletRequest request, HttpServletResponse response) {
        if (null == user) {
            logger.error("无效的用户,接口：{}", request.getRequestURI());
            return CommonResponse.error("无效的用户");
        }

        if (Strings.isNullOrEmpty(user.getVerifyCode())) {
            logger.error("无效的验证码,接口：{}", request.getRequestURI());
            return CommonResponse.error("无效的验证码");
        }


        if (Strings.isNullOrEmpty(user.getPassword()) || Strings.isNullOrEmpty(user.getUsername())) {
            logger.error("无效的用户登录信息,接口：{}", request.getRequestURI());
            return CommonResponse.error("无效的用户登录信息");
        }

        String sessionId = CookieUtils.getCookie(request, cookieConfigProperties.getSessionIdName());

        String vCode = redisTemplate.opsForValue().get("vcode:" + sessionId);

        if(Strings.isNullOrEmpty(vCode)){
            return CommonResponse.error("验证码已失效");
        }

        if (!Objects.equals(user.getVerifyCode().toLowerCase(), vCode.toLowerCase())) {
            logger.error("验证码输入错误,{}",vCode);
            return CommonResponse.error("验证码输入错误");
        }
        redisTemplate.delete("vcode:" + sessionId);

        //todo 用户信息验证
        logger.info("用户名：{}",user.getUsername());
        logger.info("密码：{}",user.getPassword());

        Map<String,Object> userInfo = new HashMap(1);
        userInfo.put("username",user.getUsername());

        String token = jwtTokenUtil.gegenrateToken();
        String jwt = jwtTokenUtil.generateJwt(userInfo, token, "**");
        logger.info("jwt:{}",jwt);
        /**
         * token 有效时间24 h
         *
         * ***/
        redisTemplate.opsForValue().set("token:" + sessionId,jwt,24*60*60*60, TimeUnit.SECONDS);

        return CommonResponse.success(jwt);


    }

    @GetMapping("")
    public @ResponseBody
    CommonResponse getUserInfo(HttpServletRequest request, HttpServletResponse response){
        UserVO user = new UserVO();
        user.setUsername("chengw");
        return CommonResponse.success(user);
    }

}
