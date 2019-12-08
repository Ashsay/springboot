package com.single;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.single","org.n3r.idworker"})
@MapperScan(basePackages = {"com.single.dao.mapper"})
//@EnableScheduling
public class SingleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SingleApplication.class, args);
	}
}
