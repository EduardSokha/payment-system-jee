package by.htp.eduard.service;

import java.util.List;

public interface EntityDtoConverter {

	<E, D> E convertToEntity(D dto, Class<E> entityClass);

	<D, E> D convertToDto(E entity, Class<D> dtoClass);

	<E, D> List<E> convertToEntityList(List<D> dtoList, Class<E> entityClass);

	<D, E> List<D> convertToDtoList(List<E> entityList, Class<D> dtoClass);

}
