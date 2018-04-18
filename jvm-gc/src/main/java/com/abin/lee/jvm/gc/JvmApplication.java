package com.abin.lee.jvm.gc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by abin on 2018/4/18 21:09.
 * jvm-capability
 * com.abin.lee.jvm.gc
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JvmApplication {

    public static void main(String[] args) {

        SpringApplication.run(JvmApplication.class, args);

    }

}
