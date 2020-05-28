package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.DomainAssembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler
	extends DomainAssembler<Restaurante, RestauranteModel> {
	
//	@Autowired
//	private ModelMapper modelMapper;
//
//	public RestauranteModel toModel(Restaurante restaurante) {
//		return modelMapper.map(restaurante, RestauranteModel.class);
//	}
//	
//	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
//		return restaurantes.stream()
//				.map(restaurante -> toModel(restaurante))
//				.collect(Collectors.toList());
//	}
	
}
