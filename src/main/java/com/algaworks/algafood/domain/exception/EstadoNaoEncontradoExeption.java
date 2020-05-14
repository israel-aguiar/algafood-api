package com.algaworks.algafood.domain.exception;

public class EstadoNaoEncontradoExeption extends EntidadeNaoEncontradaExeption {

	private static final long serialVersionUID = 1L;
	
	public EstadoNaoEncontradoExeption(String mensagem) {
		 super(mensagem);
	 }
	
	public EstadoNaoEncontradoExeption(Long estadoId) {
		this(String.format("Não existe um cadastro de estado com código %s", estadoId));
	}

}
