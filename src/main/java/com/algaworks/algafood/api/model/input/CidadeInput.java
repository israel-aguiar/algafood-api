package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	private EstadoIdInput estado;

}
