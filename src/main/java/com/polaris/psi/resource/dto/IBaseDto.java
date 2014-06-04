package com.polaris.psi.resource.dto;

public interface IBaseDto {
	
	public int getActual();
	public void setActual(int actual);
	public int getRecMinimum();
	public void setRecMinimum(int min);
	public int getRecMaximum();
	public void setRecMaximum(int max);
	public int getRecommended();
	public void setRecommended(int rec);
}
