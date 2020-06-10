package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.core.validation.FileSize;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoInput {

	@NotNull
	@FileSize(max = "5000KB")
	private MultipartFile arquivo;

	@NotBlank
	private	String descricao;
	
}
