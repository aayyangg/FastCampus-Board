package com.example.fastcampusboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) //DB 설정 없이 스프링부트 빌드 시, 사용
public class FastcampusBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastcampusBoardApplication.class, args);
	}

}
