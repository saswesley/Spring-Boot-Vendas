package io.github.saswesley;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@Development
public class MyConfiguration {
	@Bean
	public CommandLineRunner executar() {
		return args ->{
			System.out.println("RODANDO CONFIGURAÇÃO DE DESENVOLVIMENTO");
		};
	}
}
