package com.algaworks.algafood.api.v1.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoInputDisassembler extends DomainInputDisassembler<Grupo, GrupoInput> {

}
