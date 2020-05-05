package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaExeption;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.service.CadastroPermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissao;
	
	@GetMapping
	public List<Permissao> listar() {
		return permissaoRepository.findAll();
	}
	
	@GetMapping("/{permissaoId}")
	public ResponseEntity<Permissao> buscar(@PathVariable Long permissaoId) {
		Optional<Permissao> permissao = permissaoRepository.findById(permissaoId);
		
		if (permissao.isPresent()) {
			return ResponseEntity.ok(permissao.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Permissao permissao) {
		try {
			permissao = cadastroPermissao.salvar(permissao);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(permissao);
		} catch (EntidadeNaoEncontradaExeption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@PutMapping("/{permissaoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long permissaoId,
			@RequestBody Permissao permissao) {
		try {
			Optional<Permissao> permissaoAtualOptional = permissaoRepository.findById(permissaoId);
			
			if (permissaoAtualOptional.isPresent()) {
				Permissao permissaoAtual = permissaoAtualOptional.get();
				BeanUtils.copyProperties(permissao, permissaoAtual, "id");
				Permissao permissaoSalvo = cadastroPermissao.salvar(permissaoAtual);
				return ResponseEntity.ok(permissaoSalvo);
			}
			
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaExeption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<Estado> remover(@PathVariable Long permissaoId) {
		try {
			cadastroPermissao.excluir(permissaoId);
			return ResponseEntity.noContent().build();
		
		} catch (EntidadeNaoEncontradaExeption e) {
			return ResponseEntity.notFound().build();

		}
		catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
