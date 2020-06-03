package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		return produtoRepository.findById(restaurante.getId(), produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
	}
	
	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
		try {
			Produto produto = buscarOuFalhar(restauranteId, produtoId);
			produtoRepository.delete(produto);
			produtoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(restauranteId, produtoId);
		}
		
	}

}
