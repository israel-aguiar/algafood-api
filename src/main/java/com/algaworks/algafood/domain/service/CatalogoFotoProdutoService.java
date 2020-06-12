package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.FotoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorage;

	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String novoNomeArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> fotoExistente = produtoRepository
				.findFotoById(restauranteId, produtoId);
		
		if(fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(novoNomeArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
			.nomeArquivo(foto.getNomeArquivo())
			.contentType(foto.getContentType())
			.inputStream(dadosArquivo)
			.build();
		
		fotoStorage.substituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}
	
	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
		FotoProduto fotoProduto = produtoRepository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoNaoEncontradaException(restauranteId, produtoId));
		
		return fotoProduto;
	}

	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
		FotoProduto fotoProduto = buscarOuFalhar(restauranteId, produtoId);
		
		produtoRepository.delete(fotoProduto);
		produtoRepository.flush();
		
		fotoStorage.remover(fotoProduto.getNomeArquivo());
	}
}
