package com.chengw.tiafs.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 秒杀系统
 * @author chengw
 */
@Data
public class SecThread extends Thread {

    private SecService secService ;

    private int i;

    public SecThread() {
    }

    public SecThread(SecService secService, int i) {
        this.secService = secService;
        this.i = i;
    }

    @Override
    public void run() {
        secService.Sec(i);
    }
}
