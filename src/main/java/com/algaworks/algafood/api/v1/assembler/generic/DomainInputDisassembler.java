package com.algaworks.algafood.api.v1.assembler.generic;

import java.lang.reflect.ParameterizedType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
	private Class<I> inputClass;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@SuppressWarnings("unchecked")
	public DomainInputDisassembler() {
		this.domainClass = (Class<D>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
		
		this.inputClass = (Class<I>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
		
	}
	
	public D toDomainObject(I input) {
		return modelMapper.map(input, getDomainClass());
	}
	
	public void copyToDomainObject(I input, D domain) {
		ModelMapperUtil.resetEntityFields(inputClass, domain);
		modelMapper.map(input, domain);
	}
}
