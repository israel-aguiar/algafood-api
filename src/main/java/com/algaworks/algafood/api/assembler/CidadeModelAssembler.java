package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.DomainAssembler;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler
	extends DomainAssembler<Cidade, CidadeModel> {
	
}
