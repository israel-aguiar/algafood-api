package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.api.v1.openapi.controller.PermissaoControllerOpenApi;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
//	@Autowired
//	private CadastroPermissaoService cadastroPermissao;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@GetMapping
	public CollectionModel<PermissaoModel> listar() {
		
		return permissaoModelAssembler.toCollectionModel(permissaoRepository.findAll());
	}
	
//	@GetMapping("/{permissaoId}")
//	public Permissao buscar(@PathVariable Long permissaoId) {
//		return cadastroPermissao.buscarOuFalhar(permissaoId);
//	}
//	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Permissao adicionar(@RequestBody @Valid Permissao permissao) {
//		return cadastroPermissao.salvar(permissao);
//	}
//	
//	
//	@PutMapping("/{permissaoId}")
//	public Permissao atualizar(@PathVariable Long permissaoId,
//			@RequestBody @Valid Permissao permissao) {
//		Permissao permissaoAtual = cadastroPermissao.buscarOuFalhar(permissaoId);
//		BeanUtils.copyProperties(permissao, permissaoAtual, "id");
//		return cadastroPermissao.salvar(permissaoAtual);
//	}
//	
//	@DeleteMapping("/{permissaoId}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void remover(@PathVariable Long permissaoId) {
//		cadastroPermissao.excluir(permissaoId);
//	}
}
