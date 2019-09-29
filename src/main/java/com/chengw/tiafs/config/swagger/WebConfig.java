package com.chengw.tiafs.config.swagger;


import com.chengw.tiafs.config.security.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author chengw
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    public static final String[] EXCLUDE = {
            "/Styles/**","/**/**.js",
            "/*.ico","/img/**",
            "/images/**'",
            "/sec",
            "/api/login","/api/signin",
            "/api/checkCode",
            "/error",
            "/signin.html",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Override
    public  void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");


        super.addResourceHandlers(registry);
    }

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         *addPathPatterns 用于添加拦截器规则
         * excludePathPatterns 排除拦截器规则
         * ***/

        //registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns(EXCLUDE);
        super.addInterceptors(registry);
    }





}
