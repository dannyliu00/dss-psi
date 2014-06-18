/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.polaris.psi.resource.dto.ProfileDto;

/**
 * @author bericks
 *
 */
@Component
public class ProfileTypeMapper {

	public void mapTypesToProfiles(Map<Integer, String> map, List<ProfileDto> dtos) {
		for (ProfileDto dto : dtos) {
			String type = map.get(dto.getProfileId());
			dto.setType(getProductType(type));
		}
	}
	
	public void mapTypeToProfile(String type, ProfileDto dto) {
		
		dto.setType(getProductType(type));
		
	}
	
	protected String getProductType(String type) {
		String mc = "motorcycle";
		String atv = "atv";
		String productType;
		
		if(type == null) type = "";
		
		switch (type) {
		case "2":
			productType = atv;
			break;

		case "5":
			productType = mc;
			break;
			
		case "6":
			productType = atv;
			break;
			
		case "9":
			productType = atv;
			break;
			
		case "D":
			productType = atv;
			break;
			
		case "F":
			productType = mc;
			break;
			
		case "Z":
			productType = atv;
			break;
			
		default:
			productType = atv;
			break;
		}
		
		return productType;
	}
	
}
