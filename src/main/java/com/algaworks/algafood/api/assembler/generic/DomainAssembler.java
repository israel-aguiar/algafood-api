package com.algaworks.algafood.api.assembler.generic;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

@Getter
public abstract class DomainAssembler<D, M> {

	private Class<D> domainClass;
	private Class<M> modelClass;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@SuppressWarnings("unchecked")
	public DomainAssembler() {
		this.domainClass = (Class<D>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
		
		this.modelClass = (Class<M>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
		
	}
	
	public M toModel(D entity) {
		return modelMapper.map(entity, getModelClass());
	}
	
	public List<M> toCollectionModel(List<D> entities) {
		return entities.stream()
				.map(entity -> toModel(entity))
				.collect(Collectors.toList());
	}
}
