package io.github.saswesley.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.github.saswesley.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
	
	@NotNull(message = "Informe o código do cliente")
	private Integer cliente;
	
	@NotNull(message = "Informe o total do pedido")
	private BigDecimal total;
	
	@NotEmptyList(message = "Pedido não pdoe ser realizado sem itens.")
	private List<ItemPedidoDTO> items;
	

	
}
