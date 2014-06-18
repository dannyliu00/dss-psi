package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.mapper.ProfileTypeMapper;

public class ProfileTypeMapperTest {

	private ProfileTypeMapper mapper;
	private Map<Integer, String> mockMap;
	private List<ProfileDto> mockDtos;
	private ProfileDto mockDto;
	private Integer expectedId;
	private String expectedType, expectedTypeCode;
	
	@Before
	public void setUp() throws Exception {
		expectedId = 999;
		expectedTypeCode = "F";
		expectedType = "motorcycle";
		mockMap = new HashMap<Integer, String>();
		mockMap.put(expectedId, expectedTypeCode);
		
		mockDto = new ProfileDto();
		mockDto.setProfileId(expectedId);

		mockDtos = new LinkedList<ProfileDto>();
		mockDtos.add(mockDto);
		
		mapper = new ProfileTypeMapper();
	}

	@Test
	public void testMapTypesToProfiles() {
		
		mapper.mapTypesToProfiles(mockMap, mockDtos);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}
	
	@Test
	public void testMapTypeToProfile() {
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

}
