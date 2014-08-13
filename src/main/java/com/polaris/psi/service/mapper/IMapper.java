package com.polaris.psi.service.mapper;

import java.util.List;

public interface IMapper<E, D> {
	
	/**
	 * Maps a list of entity objects to a list of dtos.
	 * 
	 * @param entities
	 * @return
	 */
	public List<D> mapToDto(List<E> entities);
	
	/**
	 * Maps a single entity object to a singel dto.
	 * 
	 * @param entity
	 * @return
	 */
	public D mapToDto(E entity);
}
