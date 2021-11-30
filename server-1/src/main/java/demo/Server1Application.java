package demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Server1Application {

//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}
	public static void main(String[] args) {
		SpringApplication.run(Server1Application.class, args);
	}

}
