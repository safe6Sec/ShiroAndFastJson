package com.shiro.vuln;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan
public class ShiroAndFastJsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroAndFastJsonApplication.class, args);
    }

}
