package com.chengw.tiafs.controller;


import com.chengw.common.config.cookie.CookieConfigProperties;
import com.chengw.common.utils.CookieUtils;
import com.chengw.tiafs.util.RequestUtil;
import com.chengw.tiafs.util.VerifyCodeUtil;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author chengw
 */
@Controller
@RequestMapping(value = "/public/v1/")
@Slf4j
public class CommonController {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CookieConfigProperties cookieConfigProperties;


    @RequestMapping(value = "/index")
    public void index(HttpServletRequest request, HttpServletResponse response){
        log.info("显示主页");

        RequestUtil.sendRedirect(response,"/index.html");
    }

    @GetMapping(value = "/vCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setResponseHeader(response);

        logger.info("sid:{}",request.getSession().getId());

        String sessionId = CookieUtils.getCookie(request,cookieConfigProperties.getSessionIdName());
        if(Strings.isNullOrEmpty(sessionId)){
            throw  new IllegalArgumentException(" no  session yet");
        }
        //生成随机字符串
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        redisTemplate.opsForValue().set("vcode:" + sessionId,verifyCode,300, TimeUnit.SECONDS);
        VerifyCodeUtil.outputImage(100,30,response.getOutputStream(),verifyCode);

        logger.info("vCode:{}",verifyCode);

    }

    private void setResponseHeader(HttpServletResponse response){
        response.setHeader("Progma","No-cache");
        response.setHeader("Cache-Control","no-cache,no-store");
        response.setContentType("image/jpeg");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified",time);
        response.setDateHeader("Date",time);
        response.addDateHeader("Expires",time);

    }


}

