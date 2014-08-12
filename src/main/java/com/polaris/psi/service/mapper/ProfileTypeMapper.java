/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * @author bericks
 *
 */
@Component
public class ProfileTypeMapper {

	private static final SplunkLogger LOG = new SplunkLogger(ProfileTypeMapper.class);
	
	public void mapTypesToProfiles(Map<Integer, String> map, List<ProfileDto> dtos) {
		LOG.methodStart(PolarisIdentity.get(), "mapTypesToProfiles");
		
		for (ProfileDto dto : dtos) {
			String type = map.get(dto.getProfileId());
			dto.setType(getProductType(type));
		}

		LOG.methodEnd(PolarisIdentity.get(), "mapTypesToProfiles");
	}
	
	public void mapTypeToProfile(String type, ProfileDto dto) {
		
		LOG.methodStart(PolarisIdentity.get(), "mapTypeToProfile");
		dto.setType(getProductType(type));
		LOG.methodEnd(PolarisIdentity.get(), "mapTypeToProfile");
		
	}
	
	protected String getProductType(String type) {
		LOG.methodStart(PolarisIdentity.get(), "getProductType");

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
		
		LOG.methodEnd(PolarisIdentity.get(), "getProductType");

		return productType;
	}
	
}
