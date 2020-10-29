package com.algaworks.algafood.api.v1.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class CidadeInputDisassenbler
	extends DomainInputDisassembler<Cidade, CidadeInput>{

	@Override
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		cidade.setEstado(new Estado());
		getModelMapper().map(cidadeInput, cidade);
	}
	
	

}
