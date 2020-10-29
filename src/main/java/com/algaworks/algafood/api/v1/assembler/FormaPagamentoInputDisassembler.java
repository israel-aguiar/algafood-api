package com.algaworks.algafood.api.v1.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.DomainInputDisassembler;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoInputDisassembler
	extends DomainInputDisassembler<FormaPagamento, FormaPagamentoInput> {

}
