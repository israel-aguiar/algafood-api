package com.algaworks.algafood.api.v1.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainAssembler;
import com.algaworks.algafood.api.v1.model.ItemPedidoModel;
import com.algaworks.algafood.domain.model.ItemPedido;

@Component
public class ItemPedidoModelAssembler extends DomainAssembler<ItemPedido, ItemPedidoModel> {

}
