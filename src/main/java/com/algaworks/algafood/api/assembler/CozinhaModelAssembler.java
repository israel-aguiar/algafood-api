package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.DomainAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler
	extends DomainAssembler<Cozinha, CozinhaModel> {

}
