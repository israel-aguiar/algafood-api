package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler
	extends DomainInputDisassembler<Restaurante, RestauranteInput> {

	@Override
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
//		Para evitar Caused by: org.hibernate.HibernateException: identifier of an instance of
//		com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		
		super.copyToDomainObject(restauranteInput, restaurante);
	}
	
	
	
//	@Autowired
//	private ModelMapper modelMapper;
//
//	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
//		return modelMapper.map(restauranteInput, Restaurante.class);
//	}
}
