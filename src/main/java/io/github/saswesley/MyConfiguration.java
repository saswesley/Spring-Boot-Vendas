package io.github.saswesley;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


@Configuration
public class MyConfiguration {

	@Bean(name = "aplicationName")
	public String applicationName() {
		return "Sistema de Vendas";
	}
}
