package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradoExeption extends EntidadeNaoEncontradaExeption {

	private static final long serialVersionUID = 1L;
	
	public RestauranteNaoEncontradoExeption(String mensagem) {
		 super(mensagem);
	 }
	
	public RestauranteNaoEncontradoExeption(Long restauranteId) {
		this(String.format("Não existe um cadastro de restaurante com código %s", restauranteId));
	}

}
