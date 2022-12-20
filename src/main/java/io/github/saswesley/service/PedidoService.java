package io.github.saswesley.service;

import java.util.Optional;

import io.github.saswesley.domain.entity.Pedido;
import io.github.saswesley.domain.enums.StatusPedido;
import io.github.saswesley.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar (PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
	
	void atualizaStatus(Integer id, StatusPedido statusPedido);
}

