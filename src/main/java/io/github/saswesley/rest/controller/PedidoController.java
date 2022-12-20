package io.github.saswesley.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import io.github.saswesley.domain.entity.ItemPedido;
import io.github.saswesley.domain.entity.Pedido;
import io.github.saswesley.domain.enums.StatusPedido;
import io.github.saswesley.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.saswesley.rest.dto.InformacaoItemPedidoDTO;
import io.github.saswesley.rest.dto.InformacoesPedidoDTO;
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
	public Integer save(@RequestBody @Valid PedidoDTO dto ) {
		Pedido pedido = pedidoservice.salvar(dto);
		return pedido.getId();
	}
	
	@GetMapping("{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return pedidoservice
				.obterPedidoCompleto(id)
				.map(p -> converter(p))
				.orElseThrow(() -> 
					new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
	}
	
	@PatchMapping("{id}")  //Serve para atualizar somente um campo
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Integer id, @RequestBody @Valid AtualizacaoStatusPedidoDTO dto) {
		
		String novoStatus = dto.getNovoStatus();
		pedidoservice.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
	}
	
	private InformacoesPedidoDTO converter(Pedido pedido) {
		return InformacoesPedidoDTO
			.builder()  //Builder é um metodo estatico que da pra setar os valores sem precisar instaciar
			.codigo(pedido.getId())
			.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
			.cpf(pedido.getCliente().getCpf())
			.nomeCliente(pedido.getCliente().getNome())
			.total(pedido.getTotal())
			.status(pedido.getStatus().name())
			.items(converter(pedido.getItens()))
			.build();
	}
	

	
	private List<InformacaoItemPedidoDTO> converter (List<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		return itens.stream().map( 
				item -> InformacaoItemPedidoDTO
				.builder().descricaoProduto(item.getProduto().getDescricao())
				.precoUnitario(item.getProduto().getPreco())
				.quantidade(item.getQuantidade())
				.build()
				).collect(Collectors.toList());
	}
}
