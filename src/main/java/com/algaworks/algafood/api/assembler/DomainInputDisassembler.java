package com.algaworks.algafood.api.assembler;

import java.lang.reflect.ParameterizedType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author israel
 *
 * @param <D> Classe de domínio
 * @param <I> Classe de modelo de entrada (Input)
 */
@Getter
public abstract class DomainInputDisassembler<D, I> {

	private Class<D> domainClass;
	private Class<I> modelClass;
	
	@Getter(value = AccessLevel.PRIVATE)
	@Autowired
	private ModelMapper modelMapper;
	
	@SuppressWarnings("unchecked")
	public DomainInputDisassembler() {
		this.domainClass = (Class<D>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
		
		this.modelClass = (Class<I>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
		
	}
	
	public D toDomainObject(I domainModel) {
		return modelMapper.map(domainModel, getDomainClass());
	}
}
