package com.chengw.tiafs.controller;
import com.chengw.tiafs.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chengw
 */
@Controller
@Slf4j
public class IndexController {

    @RequestMapping(value = "/")
    public void login( HttpServletResponse response){

        log.info("测试登录");
        RequestUtil.sendRedirect(response,"/index.html");
    }

}
