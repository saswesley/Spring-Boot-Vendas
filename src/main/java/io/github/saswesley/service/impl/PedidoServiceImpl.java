package io.github.saswesley.service.impl;

import org.springframework.stereotype.Service;

import io.github.saswesley.domain.repository.PedidosRepo;
import io.github.saswesley.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	private PedidosRepo pedidosrepo;
	
	public PedidoServiceImpl(PedidosRepo pedidosrepo) {
		this.pedidosrepo = pedidosrepo;
	}
}
