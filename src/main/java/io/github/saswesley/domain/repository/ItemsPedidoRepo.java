package io.github.saswesley.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saswesley.domain.entity.ItemPedido;

public interface ItemsPedidoRepo extends JpaRepository<ItemPedido, Integer>{
	
}
