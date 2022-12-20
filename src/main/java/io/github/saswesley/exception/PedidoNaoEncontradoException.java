package io.github.saswesley.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
	
	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado.");
	}
}
