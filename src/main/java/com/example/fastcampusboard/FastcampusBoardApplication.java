package com.example.fastcampusboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) //DB 설정 없이 스프링부트 빌드 시, 사용
@EntityScan(basePackages = {"com.example.fastcampusboard.domain"})
@EnableAutoConfiguration(exclude={JpaRepositoriesAutoConfiguration.class})
public class FastcampusBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastcampusBoardApplication.class, args);
	}

}
