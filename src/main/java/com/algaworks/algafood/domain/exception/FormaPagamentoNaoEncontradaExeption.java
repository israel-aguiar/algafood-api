package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontradaExeption extends EntidadeNaoEncontradaExeption {

	private static final long serialVersionUID = 1L;
	
	public FormaPagamentoNaoEncontradaExeption(String mensagem) {
		 super(mensagem);
	 }
	
	public FormaPagamentoNaoEncontradaExeption(Long formaPagamentoId) {
		this(String.format("Não existe um cadastro de forma de pagamento com código %s", formaPagamentoId));
	}

}
