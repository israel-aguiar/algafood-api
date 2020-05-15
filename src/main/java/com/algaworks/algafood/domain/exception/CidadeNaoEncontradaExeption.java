package com.algaworks.algafood.domain.exception;

public class CidadeNaoEncontradaExeption extends EntidadeNaoEncontradaExeption {

	private static final long serialVersionUID = 1L;
	
	public CidadeNaoEncontradaExeption(String mensagem) {
		 super(mensagem);
	 }
	
	public CidadeNaoEncontradaExeption(Long cidadeId) {
		this(String.format("Não existe um cadastro de cidade com código %s", cidadeId));
	}

}
