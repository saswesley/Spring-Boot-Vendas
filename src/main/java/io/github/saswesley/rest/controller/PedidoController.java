package io.github.saswesley.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.*;

import io.github.saswesley.domain.entity.Pedido;
import io.github.saswesley.rest.dto.PedidoDTO;
import io.github.saswesley.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	private PedidoService pedidoservice;
		
	public PedidoController(PedidoService pedidoservice) {
		this.pedidoservice = pedidoservice;
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Integer save(@RequestBody PedidoDTO dto ) {
		Pedido pedido = pedidoservice.salvar(dto);
		return pedido.getId();
	}
}
