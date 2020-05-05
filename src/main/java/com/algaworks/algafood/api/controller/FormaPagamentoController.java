package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/formas_pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	@GetMapping
	public List<FormaPagamento> listar() {
		return formaPagamentoRepository.findAll();
	}
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId) {
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(formaPagamentoId);
		
		if (formaPagamento.isPresent()) {
			return ResponseEntity.ok(formaPagamento.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody FormaPagamento formaPagamento) {
		try {
			formaPagamento = cadastroFormaPagamento.salvar(formaPagamento);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(formaPagamento);
		} catch (EntidadeNaoEncontradaExeption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long formaPagamentoId,
			@RequestBody FormaPagamento formaPagamento) {
		try {
			Optional<FormaPagamento> formaPagamentoAtualOptional = formaPagamentoRepository.findById(formaPagamentoId);
			
			if (formaPagamentoAtualOptional.isPresent()) {
				FormaPagamento formaPagamentoAtual = formaPagamentoAtualOptional.get();
				BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
				FormaPagamento formaPagamentoSalvo = cadastroFormaPagamento.salvar(formaPagamentoAtual);
				return ResponseEntity.ok(formaPagamentoSalvo);
			}
			
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaExeption e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<Estado> remover(@PathVariable Long formaPagamentoId) {
		try {
			cadastroFormaPagamento.excluir(formaPagamentoId);
			return ResponseEntity.noContent().build();
		
		} catch (EntidadeNaoEncontradaExeption e) {
			return ResponseEntity.notFound().build();

		}
		catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
