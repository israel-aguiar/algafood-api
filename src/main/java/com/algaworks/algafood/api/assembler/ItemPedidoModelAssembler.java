package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.DomainAssembler;
import com.algaworks.algafood.api.model.ItemPedidoModel;
import com.algaworks.algafood.domain.model.ItemPedido;

@Component
public class ItemPedidoModelAssembler extends DomainAssembler<ItemPedido, ItemPedidoModel> {

}
