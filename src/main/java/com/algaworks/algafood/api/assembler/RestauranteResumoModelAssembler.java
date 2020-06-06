package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.DomainAssembler;
import com.algaworks.algafood.api.model.RestauranteResumoModel;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteResumoModelAssembler extends DomainAssembler<Restaurante, RestauranteResumoModel>{

}
