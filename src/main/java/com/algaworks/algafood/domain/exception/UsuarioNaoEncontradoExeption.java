package com.algaworks.algafood.domain.exception;

public class UsuarioNaoEncontradoExeption extends EntidadeNaoEncontradaExeption {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoEncontradoExeption(String mensagem) {
		 super(mensagem);
	 }
	
	public UsuarioNaoEncontradoExeption(Long usuarioId) {
		this(String.format("Não existe um cadastro de usuário com código %s", usuarioId));
	}

}
