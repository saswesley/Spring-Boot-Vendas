package io.github.saswesley.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.saswesley.domain.entity.ItemPedido;

public interface ItemsPedidoRepo extends JpaRepository<ItemPedido, Integer>{
	
}
