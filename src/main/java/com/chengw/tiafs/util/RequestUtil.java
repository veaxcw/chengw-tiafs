package com.chengw.tiafs.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestUtil {

    public static void sendRedirect(HttpServletResponse response, String url) {
        String rUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(url).build().toUriString();

        try {
            response.sendRedirect(rUrl);
            return;
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

}
