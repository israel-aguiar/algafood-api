package com.algaworks.algafood.domain.exception;

public class NegocioExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NegocioExeption(String mensagem) {
		 super(mensagem);
	 }

	public NegocioExeption(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}