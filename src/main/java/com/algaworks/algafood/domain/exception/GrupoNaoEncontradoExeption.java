package com.algaworks.algafood.domain.exception;

public class GrupoNaoEncontradoExeption extends EntidadeNaoEncontradaExeption {

	private static final long serialVersionUID = 1L;
	
	public GrupoNaoEncontradoExeption(String mensagem) {
		 super(mensagem);
	 }
	
	public GrupoNaoEncontradoExeption(Long grupoId) {
		this(String.format("Não existe um cadastro de grupo com código %s", grupoId));
	}

}
