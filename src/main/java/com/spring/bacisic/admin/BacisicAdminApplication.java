package com.spring.bacisic.admin;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.spring.bacisic.admin.models.*.mapper")
@EnableTransactionManagement
public class BacisicAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BacisicAdminApplication.class, args);
    }

    /**
     * page Plugin
     *
     * @author zhangby
     * @date 2019-05-14 13:37
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
