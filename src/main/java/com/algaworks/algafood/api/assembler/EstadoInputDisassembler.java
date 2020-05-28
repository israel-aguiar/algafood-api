package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoInputDisassembler
	extends DomainInputDisassembler<Estado, EstadoInput> {

	@Override
	public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
		getModelMapper().map(estadoInput, estado);
	}
	
}
