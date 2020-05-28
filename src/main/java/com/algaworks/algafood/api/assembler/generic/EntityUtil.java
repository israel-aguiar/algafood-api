package com.algaworks.algafood.api.assembler.generic;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import org.springframework.util.ReflectionUtils;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Restaurante;

public class EntityUtil {
	
	public static void resetEntityFields(Class<?> modelClass, Object object ) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(modelClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			
			for (PropertyDescriptor propertyDescriptorModel : propertyDescriptors) {
				System.out.printf(">>>name: %s\n", propertyDescriptorModel.getName());
				if(!propertyDescriptorModel.getName().equals("class")) {
					String nameField = propertyDescriptorModel.getName();
					Class<?> classObject = object.getClass();
					PropertyDescriptor propertyDescriptorObject = new PropertyDescriptor(nameField, classObject);
					Field fieldObject = ReflectionUtils.findField(classObject, nameField);
					
					Class<?> fieldClazz = fieldObject.getType();
					
					//verificar anotações possíveis do JPA
					boolean isEntity = fieldClazz.isAnnotationPresent(Entity.class);
					boolean isEmbeddable = fieldClazz.isAnnotationPresent(Embeddable.class);
					
					Method methodGet = propertyDescriptorObject.getReadMethod();
					Object fieldValue = ReflectionUtils.invokeMethod(methodGet, object);
					
					if(fieldValue != null) {
						//verificar anotações possíveis do JPA
						if(isEntity) {
							resetEntityField(propertyDescriptorObject, object);
	//						System.out.printf("name: %s\n", field.getName());
						} else if (isEmbeddable) {
							Class<?> classFieldModel = propertyDescriptorModel.getReadMethod().getDeclaringClass();
							resetEntityFields(classFieldModel, fieldValue);//recursao: possibilidade de loop infinito
						}
					}
				}
			}
			
		} catch (IntrospectionException e) {
			// TODO Trabalhar esta exception
			throw new RuntimeException(e);
		}
	}
	
	public static void resetEntityFields(Object object0) {
		
		try {
			Class<?> clazz = object0.getClass();
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
			
			for (PropertyDescriptor pd : props) {
				String name = pd.getName();
				Optional<Field> fieldOptional = 
						Optional.ofNullable(ReflectionUtils.findField(object0.getClass(), name));
				
				fieldOptional.ifPresent(field -> {
					Class<?> fieldClazz = field.getType();
//					System.out.printf("fieldClazz: %s\n", fieldClazz);
					
					//verificar anotações possíveis do JPA
					boolean isEntity = fieldClazz.isAnnotationPresent(Entity.class);
					boolean isEmbeddable = fieldClazz.isAnnotationPresent(Embeddable.class);;
					
					//verificar anotações possíveis do JPA
					if(isEntity) {
						resetEntityField(pd, object0);
//						System.out.printf("name: %s\n", field.getName());
						
					} else if (isEmbeddable) {//Endereco é um Embeddable
						Method methodGet = pd.getReadMethod();
						Object objectEmbeddable = ReflectionUtils.invokeMethod(methodGet, object0);
						if (objectEmbeddable != null)
							resetEntityFields(objectEmbeddable); //recursao: possibilidade de loop infinito
					}
				});
			}
			
			
		} catch (IntrospectionException e) {
			// TODO Trabalhar esta exception
			throw new RuntimeException();
		}
		
	}
	
	private static void resetEntityField(PropertyDescriptor property, Object object0) {
		
		try {
			Method method = property.getWriteMethod();
//			System.out.printf("method: %s\n", method);
			Class<?> entityClass = property.getReadMethod().getReturnType();
			Object en = entityClass.getDeclaredConstructor().newInstance();
			ReflectionUtils.invokeMethod(method, object0, en);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Trabalhar esta exception
			throw new RuntimeException(e);
		}
		
	}

	public static void main(String[] args) {
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		cozinha.setNome("cozinha aaa");
		
		Estado estado = new Estado();
		estado.setId(1L);
		estado.setNome("Bahia");
		
		Cidade cidade = new Cidade();
		cidade.setId(1L);
		cidade.setNome("Vitória da Conquista");
		cidade.setEstado(estado);
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua a");
		endereco.setCidade(cidade);
				
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Restaurante bbb");
		restaurante.setTaxaFrete(new BigDecimal("12.5"));
		restaurante.setCozinha(cozinha);
		restaurante.setEndereco(endereco);
				

		System.out.printf("cozinha antes: %s\n", restaurante.getCozinha().getNome());
		System.out.printf("cidade antes: %s\n", restaurante.getEndereco().getCidade().getNome());
		
//		EntityUtil.resetEntityFields(restaurante);
		EntityUtil.resetEntityFields(RestauranteInput.class, restaurante);
		
		System.out.printf("cozinha depois: %s\n", restaurante.getCozinha().getNome());
		System.out.printf("cidade depois: %s\n", restaurante.getEndereco().getCidade().getNome());
		
//		EntityUtil.resetEntityFields(RestauranteModel.class, restaurante);
		
	}
}
