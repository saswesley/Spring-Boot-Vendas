package io.github.saswesley;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.saswesley.domain.entity.Cliente;
import io.github.saswesley.domain.repository.ClientesRepo;


@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner comandLineRunner(@Autowired ClientesRepo clientesrepo) {
		return args -> {
			Cliente c = new Cliente (null, "Wesley");
			clientesrepo.save(c);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
