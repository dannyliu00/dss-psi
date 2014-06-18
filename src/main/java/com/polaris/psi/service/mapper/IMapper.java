package com.polaris.psi.service.mapper;

import java.util.List;

public interface IMapper<E, D> {
	
	public List<D> mapToDto(List<E> entities);
	public D mapToDto(E entity);
}
