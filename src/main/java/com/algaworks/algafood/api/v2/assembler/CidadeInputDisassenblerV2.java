package com.algaworks.algafood.api.v2.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class CidadeInputDisassenblerV2
	extends DomainInputDisassembler<Cidade, CidadeInputV2>{

	@Override
	public void copyToDomainObject(CidadeInputV2 cidadeInput, Cidade cidade) {
		cidade.setEstado(new Estado());
		getModelMapper().map(cidadeInput, cidade);
	}
	
	

}
