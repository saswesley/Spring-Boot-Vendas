package io.github.saswesley.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saswesley.domain.entity.Cliente;
import io.github.saswesley.domain.entity.Pedido;

public interface PedidosRepo extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByCliente(Cliente cliente);
}
