package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.api.model.ItemPedidoModel;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoModel.class);
		
		var itemPedidoToItemPedidoModelTypeMap = modelMapper.createTypeMap(
				ItemPedido.class, ItemPedidoModel.class);
		
		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
		
		itemPedidoToItemPedidoModelTypeMap.<String>addMapping(
				itemPedidoSrc -> itemPedidoSrc.getProduto().getNome(),
				(itemPedidoModelDest, value) -> itemPedidoModelDest.setPedidoNome(value));
		
		return modelMapper;
	}
}
