package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);
	
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	public PedidoModel buscar(String codigoPedido);
	
	public PedidoModel adicionar(PedidoInput pedidoInput);
}
