package com.algaworks.algafood.domain.exception;

public class EntidadeNaoEncontradaExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	 public EntidadeNaoEncontradaExeption(String mensagem) {
		 super(mensagem);
	 }

}
