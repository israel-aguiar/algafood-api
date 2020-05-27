package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler
	extends DomainInputDisassembler<Restaurante, RestauranteInput>{
	
//	@Autowired
//	private ModelMapper modelMapper;
//
//	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
//		return modelMapper.map(restauranteInput, Restaurante.class);
//	}
}
