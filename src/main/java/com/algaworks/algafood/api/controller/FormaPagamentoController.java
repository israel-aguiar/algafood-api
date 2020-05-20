package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

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
	public FormaPagamento buscar(@PathVariable Long formaPagamentoId) {
		return cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamento adicionar(@RequestBody @Valid FormaPagamento formaPagamento) {
			return cadastroFormaPagamento.salvar(formaPagamento);
	}
	
	
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamento atualizar(@PathVariable Long formaPagamentoId,
			@RequestBody @Valid FormaPagamento formaPagamento) {
		FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
		return cadastroFormaPagamento.salvar(formaPagamentoAtual);
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		cadastroFormaPagamento.excluir(formaPagamentoId);
	}
}
