package com.chengw.tiafs.config.security;

import com.alibaba.fastjson.JSON;
import com.chengw.common.config.cookie.CookieConfigProperties;
import com.chengw.common.config.cookie.TokenConfigProperties;
import com.chengw.common.models.vo.CommonResponse;
import com.chengw.common.models.vo.UserVO;
import com.chengw.common.utils.CookieUtils;
import com.chengw.common.utils.JwtTokenUtil;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户登录验证 过滤器
 * 拦截所有请求头中带有Authorization
 **
 * @author chengwei*/
@Component
//@Profile("test")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DevJwtAuthenticationFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(DevJwtAuthenticationFilter.class);

    @Autowired
    private TokenConfigProperties tokenConfigProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CookieConfigProperties cookieConfigProperties;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        logger.info("测试拦截器：{}", request.getRequestURI());
//        String jwtToken = request.getHeader(tokenConfigProperties.getHeaderName());
        String jwtToken = generateToken();
        logger.info("token:{}", jwtToken);

        String sessionId = CookieUtils.getCookie(request, cookieConfigProperties.getSessionIdName());
        response.setContentType("application/json");

        if(Strings.isNullOrEmpty(jwtToken)){
            String body = JSON.toJSONString(CommonResponse.unauthenticated());
            response.getWriter().write(body);
            return;
        }
        String token = redisTemplate.opsForValue().get("token:" + "test");
        if (Strings.isNullOrEmpty(token)) {
            String body = JSON.toJSONString(CommonResponse.error("token 已过期"));
            response.getWriter().write(body);
            return;
        }

        if(!jwtToken.startsWith(JwtTokenUtil.AUTHORIZATION_HEADER_STRING)){
            String body = JSON.toJSONString(CommonResponse.error("token 不正确"));
            response.getWriter().write(body);
            return;
        }

        // 刷新token 过期时间
        redisTemplate.opsForValue().setIfAbsent("token:" + sessionId, token, 5 * 60 * 60 * 60, TimeUnit.SECONDS);
        String tk = jwtToken.substring(JwtTokenUtil.AUTHORIZATION_HEADER_STRING.length()).trim();
        Map<String, Object> result = jwtTokenUtil.decodeJwtToken(tk);
        logger.info("result:{}", result);
        UserVO user = JSON.parseObject(JSON.toJSONString(result), UserVO.class);
        logger.info(user.toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * @return 生成假的token
     */
    private String generateToken() {
        Map<String,Object> userInfo = new HashMap(1);
        userInfo.put("username","chengw");


        String token = jwtTokenUtil.generateToken();
        String jwt = jwtTokenUtil.generateJwt(userInfo, token, "**");

        logger.info("jwt:{}", JwtTokenUtil.AUTHORIZATION_HEADER_STRING + jwt);
        /**
         * token 有效时间24 h
         *
         * ***/
        redisTemplate.opsForValue().set("token:" + "test",jwt,24*60*60*60, TimeUnit.SECONDS);
        return JwtTokenUtil.AUTHORIZATION_HEADER_STRING + jwt;
    }

}
