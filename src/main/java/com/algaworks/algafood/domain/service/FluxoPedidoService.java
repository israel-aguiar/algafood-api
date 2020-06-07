package com.algaworks.algafood.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {
	
	private static final String MSG_STATUS = "Status do pedido %d não pode ser alterado de %s para %s";
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
		mudarStatus(pedido, StatusPedido.CONFIRMADO, StatusPedido.CRIADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
	}

	@Transactional
	public void cancelar(Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
		mudarStatus(pedido, StatusPedido.CANCELADO, StatusPedido.CRIADO);
		pedido.setDataCancelamento(OffsetDateTime.now());
	}

	@Transactional
	public void entregar(Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
		mudarStatus(pedido, StatusPedido.ENTREGUE, StatusPedido.CONFIRMADO);
		pedido.setDataEntrega(OffsetDateTime.now());
	}

	private void mudarStatus(Pedido pedido, StatusPedido novoStatus, StatusPedido statusPreRequisito) {
		
		if(!pedido.getStatus().equals(statusPreRequisito)) {
			throw new NegocioException(String.format(MSG_STATUS,
					pedido.getId(), pedido.getStatus().getDescricao(), novoStatus.getDescricao()));
		}
		pedido.setStatus(novoStatus);
	}

}
