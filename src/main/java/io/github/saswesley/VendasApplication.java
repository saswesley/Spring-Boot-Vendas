package io.github.saswesley;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.saswesley.domain.entity.Cliente;
import io.github.saswesley.domain.repository.Clientes;

@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			System.out.println("Salvando clientes");
			clientes.save (new Cliente("Wesley"));
			clientes.save(new Cliente("Outro cliente"));
			
			List<Cliente> result = clientes.encontrarnome("Wesley");
			result.forEach(System.out::println);
			
			
			
//			System.out.println("Atualizando clientes");
//			todosClientes.forEach(c -> {
//				c.setNome(c.getNome() + " atualizado");
//				clientes.save(c);
//			});
//			
//			todosClientes = clientes.findAll();
//			todosClientes.forEach(System.out::println);
//			
//			System.out.println("Buscando clientes");
//			clientes.findByNomeLike("Cli").forEach(System.out::println);
//			
//			System.out.println("Deletando clientes");
//			clientes.findAll().forEach(c -> {
//				clientes.delete(c);
//			});
//			
//			todosClientes= clientes.findAll();
//			if(todosClientes.isEmpty()) {
//				System.out.println("Nenhum cliente encontrado");
//			}else {
//				todosClientes.forEach(System.out::println);
//			}
			
			
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
