package io.github.saswesley.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.stereotype.Service;

import io.github.saswesley.domain.entity.Cliente;
import io.github.saswesley.domain.entity.ItemPedido;
import io.github.saswesley.domain.entity.Pedido;
import io.github.saswesley.domain.entity.Produto;
import io.github.saswesley.domain.enums.StatusPedido;
import io.github.saswesley.domain.repository.ClientesRepo;
import io.github.saswesley.domain.repository.ItemsPedidoRepo;
import io.github.saswesley.domain.repository.PedidosRepo;
import io.github.saswesley.domain.repository.ProdutosRepo;
import io.github.saswesley.exception.PedidoNaoEncontradoException;
import io.github.saswesley.exception.RegraNegocioException;
import io.github.saswesley.rest.dto.ItemPedidoDTO;
import io.github.saswesley.rest.dto.PedidoDTO;
import io.github.saswesley.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
	private final PedidosRepo pedidosrepo;
	private final ClientesRepo clientesrepo;
	private final ProdutosRepo produtosrepo;
	private final ItemsPedidoRepo itemPedidoRepo;
	
	@Override
	@Transactional
	public Pedido salvar (PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientesrepo.findById(idCliente)
			.orElseThrow(() -> new RegraNegocioException("Código de cliente invalido. "));

		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		List <ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		pedidosrepo.save(pedido);
		itemPedidoRepo.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		return pedido;
	}
	
	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return pedidosrepo.findByIdFetchItens(id);
	}
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if(items.isEmpty()) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
		}
		return items.stream()
				.map(dto -> {
				Integer idProduto = dto.getProduto();
				Produto produto = produtosrepo
					.findById(idProduto)
					.orElseThrow(
							() -> new RegraNegocioException("Código de produto invalido. " + idProduto
									));
			
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setQuantidade(dto.getQuantidade());
				itemPedido.setPedido(pedido);
				itemPedido.setProduto(produto);
				return itemPedido;
		}).collect(Collectors.toList());
		
	}

	@Override
	@Transactional
	public void atualizaStatus(Integer id, StatusPedido statusPedido) {
		pedidosrepo
				.findById(id) //caso encontre cai no map
				.map(pedido -> {
					pedido.setStatus(statusPedido);
					return pedidosrepo.save(pedido);
				}).orElseThrow(() -> new PedidoNaoEncontradoException());
	}
	
}
