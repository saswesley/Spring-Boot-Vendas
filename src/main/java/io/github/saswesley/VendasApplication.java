package io.github.saswesley;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.saswesley.domain.entity.Cliente;
import io.github.saswesley.domain.entity.Pedido;
import io.github.saswesley.domain.repository.ClientesRepo;
import io.github.saswesley.domain.repository.PedidosRepo;

@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner init(
			@Autowired ClientesRepo clientes, 
			@Autowired PedidosRepo pedidos
	) {
		
		return args -> {
			System.out.println("Salvando clientes");
			Cliente wes = new Cliente ("Wes");
			clientes.save(wes);
			
			
			Pedido p = new Pedido();
			p.setCliente(wes);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			
			pedidos.save(p);
			
//			Cliente cliente = clientes.findClienteFetchPedidos(wes.getId());
//			System.out.println(cliente);
//			System.out.println(cliente.getPedidos());
			pedidos.findByCliente(wes).forEach(System.out::println);
			
			
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
