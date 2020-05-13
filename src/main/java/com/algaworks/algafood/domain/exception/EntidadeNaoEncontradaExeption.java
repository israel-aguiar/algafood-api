package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Entidade não encontrada")
public class EntidadeNaoEncontradaExeption extends ResponseStatusException {

	private static final long serialVersionUID = 1L;
	
	 public EntidadeNaoEncontradaExeption(HttpStatus status, String mensagem) {
		super(status, mensagem);
		// TODO Auto-generated constructor stub
	}

	public EntidadeNaoEncontradaExeption(String mensagem) {
		 super(HttpStatus.NOT_FOUND, mensagem);
	 }

}
