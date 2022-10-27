package io.github.saswesley.service;

import io.github.saswesley.domain.entity.Pedido;
import io.github.saswesley.rest.dto.PedidoDTO;

public interface PedidoService {
	Pedido salvar (PedidoDTO dto);
	
}
