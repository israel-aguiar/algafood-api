package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.DomainAssembler;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.domain.model.Produto;

@Component
public class ProdutoModelAssembler extends DomainAssembler<Produto, ProdutoModel> {

}
