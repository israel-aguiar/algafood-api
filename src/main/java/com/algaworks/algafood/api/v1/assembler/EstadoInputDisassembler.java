package com.algaworks.algafood.api.v1.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoInputDisassembler
	extends DomainInputDisassembler<Estado, EstadoInput> {

	@Override
	public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
		getModelMapper().map(estadoInput, estado);
	}
	
}
