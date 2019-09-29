package com.chengw.tiafs.config.security.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.chengw.common.models.vo.UserVO;
import com.chengw.tiafs.util.RequestUtil;
import com.chengw.tiafs.model.vo.LoginEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author chengw
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("测试拦截器");

        request.getRequestURI();
        log.info(request.getRequestURI());
        boolean flag = true;
        //checkSession(request);
        HttpSession session = request.getSession();
        LoginEntity user = (LoginEntity) session.getAttribute("user");
        if(user == null || user.getUsername() == null){
            log.info("转发请求至登录界面");
            RequestUtil.sendRedirect(response,"/signin.html");
            flag = false;
        }

        return flag;
    }

    private boolean checkSession(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session == null){
            session = request.getSession();
        }
        if(session != null){
            String sessionStr = "{" +
                    "			  \"serviceResponse\" : {" +
                    "			    \"authenticationSuccess\" : {" +
                    "			      \"user\" : \"chengw\"," +
                    "			      \"attributes\" : {\r\n" +
                    "			        \"userName\" : \"chengw\"" +
                    "			      }" +
                    "			    }" +
                    "			  }" +
                    "			}";
            JSONObject jsonObject = JSONObject.parseObject(sessionStr);
            if(jsonObject !=null && jsonObject.containsKey("serviceResponse")){
                JSONObject serviceResponse = jsonObject.getJSONObject("serviceResponse");
                if(serviceResponse != null && serviceResponse.containsKey("authenticationSuccess")){
                    JSONObject authenticationSuccess = serviceResponse.getJSONObject("authenticationSuccess");
                    UserVO userInfo = JSONObject.toJavaObject(authenticationSuccess, UserVO.class);
                    session.setAttribute("user",userInfo);
                    return true;
                }

            }
        }

        return true;
    }


}

