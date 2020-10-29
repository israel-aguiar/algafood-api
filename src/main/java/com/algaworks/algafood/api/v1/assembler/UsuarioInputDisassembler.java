package com.algaworks.algafood.api.v1.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler extends DomainInputDisassembler<Usuario, UsuarioInput> {

}
