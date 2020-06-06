package com.algaworks.algafood.domain.exception;

public abstract class EntidadeNaoEncontradaExeption extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaExeption(String mensagem) {
		 super(mensagem);
	 }

}
