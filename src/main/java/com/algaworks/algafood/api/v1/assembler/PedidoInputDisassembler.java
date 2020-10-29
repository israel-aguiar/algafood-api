package com.algaworks.algafood.api.v1.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoInputDisassembler extends DomainInputDisassembler<Pedido, PedidoInput> {

}
