package com.algaworks.algafood.api.v1.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainAssembler;
import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler extends DomainAssembler<FotoProduto, FotoProdutoModel> {

}
