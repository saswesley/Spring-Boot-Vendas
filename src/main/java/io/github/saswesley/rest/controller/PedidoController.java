package io.github.saswesley.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.saswesley.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	private PedidoService pedidoservice;
		
	public PedidoController(PedidoService pedidoservice) {
		this.pedidoservice = pedidoservice;
	}
}
