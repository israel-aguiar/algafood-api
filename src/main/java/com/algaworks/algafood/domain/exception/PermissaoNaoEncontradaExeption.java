package com.algaworks.algafood.domain.exception;

public class PermissaoNaoEncontradaExeption extends EntidadeNaoEncontradaExeption {

	private static final long serialVersionUID = 1L;
	
	public PermissaoNaoEncontradaExeption(String mensagem) {
		 super(mensagem);
	 }
	
	public PermissaoNaoEncontradaExeption(Long permissaoId) {
		this(String.format("Não existe um cadastro de permissao com código %s", permissaoId));
	}

}
