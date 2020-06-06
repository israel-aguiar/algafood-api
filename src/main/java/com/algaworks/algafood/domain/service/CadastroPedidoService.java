package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class CadastroPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscarOuFalhar(Long pedidoId) {
		Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
		return pedido.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}
}
