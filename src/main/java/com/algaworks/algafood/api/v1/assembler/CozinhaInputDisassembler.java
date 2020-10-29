package com.algaworks.algafood.api.v1.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaInputDisassembler
	extends DomainInputDisassembler<Cozinha, CozinhaInput> {

	@Override
	public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
		getModelMapper().map(cozinhaInput, cozinha);
	}
	
}
