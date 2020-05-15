package com.algaworks.algafood.domain.exception;

public class CozinhaNaoEncontradaExeption extends EntidadeNaoEncontradaExeption {

	private static final long serialVersionUID = 1L;
	
	public CozinhaNaoEncontradaExeption(String mensagem) {
		 super(mensagem);
	 }
	
	public CozinhaNaoEncontradaExeption(Long cozinhaId) {
		this(String.format("Não existe um cadastro de cozinha com código %s", cozinhaId));
	}

}
