package me.urim.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//create_at, update_at 자동 업데이트
@EnableJpaAuditing
@SpringBootApplication
public class SpringbootDeveloperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDeveloperApplication.class, args);
		//SpringbootDeveloperApplication.class : 메인으로 사용할 클래스
		//args : 커맨드 라인의 인수
	}

}
