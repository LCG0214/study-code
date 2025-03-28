package cn.lcgui.springbootlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.lcgui.springbootlog")
@SpringBootApplication
public class SpringbootLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLogApplication.class, args);
    }

}
