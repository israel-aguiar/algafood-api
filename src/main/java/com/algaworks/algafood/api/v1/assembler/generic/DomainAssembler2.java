package com.algaworks.algafood.api.v1.assembler.generic;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import lombok.Getter;

@Getter
public abstract class DomainAssembler2<D, M extends RepresentationModel<M> > extends RepresentationModelAssemblerSupport<D, M> {

	private Class<D> domainClass;
	private Class<M> modelClass;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public DomainAssembler2(Class<?> controllerClass, Class<M> modelClass) {
		super(controllerClass, modelClass);
		
		this.modelClass = modelClass;
		
	}
	
	public M toModel(D entity) {
		M entityModel = createModelWithId(getId(entity), entity);
		
		modelMapper.map(entity, entityModel);
		
		modifyModel(entityModel);
		
		return entityModel;
	}

	@Override
	public CollectionModel<M> toCollectionModel(Iterable<? extends D> entities) {
		CollectionModel<M> collectionModel = super.toCollectionModel(entities);
		
		modifyCollectionModel(collectionModel);
		
		return collectionModel;
	}
	
	public void modifyModel(RepresentationModel<M> entityModel) {
		
	}

	protected void modifyCollectionModel(CollectionModel<M> collectionModel) {
		
	}

	protected abstract Object getId(D entity);

}
