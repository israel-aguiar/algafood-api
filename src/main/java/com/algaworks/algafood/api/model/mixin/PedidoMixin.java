package com.algaworks.algafood.api.model.mixin;

import java.util.List;

import com.algaworks.algafood.domain.model.ItemPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PedidoMixin {
	
	@JsonIgnore
	private List<ItemPedido> itens;

}
