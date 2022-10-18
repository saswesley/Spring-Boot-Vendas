package io.github.saswesley.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saswesley.domain.entity.Pedido;

public interface PedidosRepo extends JpaRepository<Pedido, Integer> {

}
