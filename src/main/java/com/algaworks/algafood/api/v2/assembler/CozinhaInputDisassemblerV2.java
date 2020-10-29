package com.algaworks.algafood.api.v2.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.v2.model.input.CozinhaInputV2;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaInputDisassemblerV2
	extends DomainInputDisassembler<Cozinha, CozinhaInputV2> {

	@Override
	public void copyToDomainObject(CozinhaInputV2 cozinhaInput, Cozinha cozinha) {
		getModelMapper().map(cozinhaInput, cozinha);
	}
	
}
