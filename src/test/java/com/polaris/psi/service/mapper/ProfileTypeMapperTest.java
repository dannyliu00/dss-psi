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
	public void testMapTypeToProfileDefault() {
		expectedTypeCode = "";
		expectedType = "atv";
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

	@Test
	public void testMapTypeToProfile2() {
		expectedTypeCode = "2";
		expectedType = "atv";
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

	@Test
	public void testMapTypeToProfile6() {
		expectedTypeCode = "6";
		expectedType = "atv";
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

	@Test
	public void testMapTypeToProfile7() {
		expectedTypeCode = "7";
		expectedType = "atv";
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

	@Test
	public void testMapTypeToProfile9() {
		expectedTypeCode = "9";
		expectedType = "atv";
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

	@Test
	public void testMapTypeToProfileD() {
		expectedTypeCode = "D";
		expectedType = "atv";
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

	@Test
	public void testMapTypeToProfileZ() {
		expectedTypeCode = "Z";
		expectedType = "atv";
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

	@Test
	public void testMapTypeToProfile5() {
		expectedTypeCode = "5";
		expectedType = "motorcycle";
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

	@Test
	public void testMapTypeToProfileE() {
		expectedTypeCode = "E";
		expectedType = "motorcycle";
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

	@Test
	public void testMapTypeToProfileF() {
		expectedTypeCode = "F";
		expectedType = "motorcycle";
		
		mapper.mapTypeToProfile(expectedTypeCode, mockDto);
		
		assertEquals(expectedId.intValue(), mockDto.getProfileId());
		assertEquals(expectedType, mockDto.getType());
	}

}
