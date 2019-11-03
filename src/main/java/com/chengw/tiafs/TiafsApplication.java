package com.chengw.tiafs;


import com.chengw.common.config.cookie.CookieConfigProperties;
import com.chengw.common.config.cookie.TokenConfigProperties;
import com.chengw.common.config.jwt.JwtConfigProperties;
import com.chengw.common.utils.JwtTokenUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


/**
 * @author chengw
 */
@SpringBootApplication
@MapperScan(basePackages = "com.chengw.tiafs.mapper")
@EnableConfigurationProperties({
        JwtConfigProperties.class,
        CookieConfigProperties.class,
        TokenConfigProperties.class
})
@EnableEurekaClient
public class TiafsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(TiafsApplication.class, args);
        final String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        System.out.println("[----------------------------------------------]");
        System.out.println("> service started: "+ Arrays.toString(activeProfiles));
        System.out.println("[----------------------------------------------]");
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil(JwtConfigProperties jwtConfigProperties) {
        return new JwtTokenUtil(jwtConfigProperties);
    }

}
