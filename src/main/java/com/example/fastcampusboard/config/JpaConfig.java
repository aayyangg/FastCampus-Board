package com.example.fastcampusboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("ayyang"); // TODO: 스프링 시큐리티로 인증 기능을 할 경우, 수정하자. -> 이걸하면 누가 로그인했는지 알게됨. (식별 가능)
    }
}
