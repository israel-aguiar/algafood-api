package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaExeption;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	public void excluir(Long formaPagamentoId) {
		try {
			formaPagamentoRepository.deleteById(formaPagamentoId);
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaExeption(formaPagamentoId);
		}
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("FormaPagamento de código %d não pode ser removida, pois está em uso", formaPagamentoId));
		}
	}

	public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId)
				.orElseThrow( () -> new FormaPagamentoNaoEncontradaExeption(formaPagamentoId));
	}

}
