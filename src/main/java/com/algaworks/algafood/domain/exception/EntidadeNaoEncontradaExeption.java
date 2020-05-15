package com.algaworks.algafood.domain.exception;

public abstract class EntidadeNaoEncontradaExeption extends NegocioExeption {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaExeption(String mensagem) {
		 super(mensagem);
	 }

}
