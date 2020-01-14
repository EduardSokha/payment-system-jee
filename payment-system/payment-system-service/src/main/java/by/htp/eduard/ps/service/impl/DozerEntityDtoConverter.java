package by.htp.eduard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

import by.htp.eduard.service.EntityDtoConverter;

public class DozerEntityDtoConverter implements EntityDtoConverter{
	
	DozerBeanMapper mapper;

	public DozerEntityDtoConverter() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(configDozerMappings());
		//mapper.setCustomConverters(configDozerConverters());
	}

	@Override
	public <E, D> E convertToEntity(D dto, Class<E> entityClass) {
		if(dto == null) {
			return null;
		}
		
		E map = mapper.map(dto, entityClass);
		return map;
	}

	@Override
	public <D, E> D convertToDto(E entity, Class<D> dtoClass) {
		if(entity == null) {
			return null;
		}
		
		D map = mapper.map(entity, dtoClass);
		return map;
	}

	@Override
	public <E, D> List<E> convertToEntityList(List<D> dtoList, Class<E> entityClass) {
		List<E> entities = new ArrayList<>();
		for (D d : dtoList) {
			E entity = convertToEntity(d, entityClass);
			entities.add(entity);
		}
		
		return entities;
	}

	@Override
	public <D, E> List<D> convertToDtoList(List<E> entityList, Class<D> dtoClass) {
		List<D> dtos = new ArrayList<>();
		for (E e : entityList) {
			D dto = convertToDto(e, dtoClass);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	private List<String> configDozerMappings() {
		List<String> mappings = new ArrayList<String>();
		
		mappings.add("dozer/role_maping.xml");
		mappings.add("dozer/account_mapping.xml");
		mappings.add("dozer/currency_mapping.xml");
		mappings.add("dozer/card_mapping.xml");
		
		return mappings;
	}
	
//	private List<CustomConverter> configDozerConverters() {
//		List<CustomConverter> converters = new ArrayList<>();
//		
//		converters.add(new AccountStatusIdNameConverter());
//		converters.add(new CurrencyIdNameConverter());
//		converters.add(new UserIdNameConverter());
//		
//		return converters;
//	}
}
