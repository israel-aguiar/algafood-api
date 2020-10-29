package com.algaworks.algafood.api.v1.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;

@Component
public class ProdutoInputDisassembler extends DomainInputDisassembler<Produto, ProdutoInput>{

}
