package com.polaris.psi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.dao.ReasonCodeDao;
import com.polaris.psi.repository.entity.ReasonCode;
import com.polaris.psi.resource.dto.ReasonCodeDto;
import com.polaris.psi.service.mapper.ReasonCodeMapper;

public class ReasonCodeServiceTest {

	private ReasonCodeService service;
	@Mock ReasonCodeDao mockDao;
	@Mock ReasonCodeMapper mockMapper;
	private List<ReasonCode> reasonCodes;
	@Mock ReasonCode mockReasonCode;
	private ReasonCodeDto dto;
	private int expectedRole;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedRole = 1;
		
		reasonCodes = new LinkedList<ReasonCode>();
		reasonCodes.add(mockReasonCode);
		when(mockDao.getReasonCodes(anyInt())).thenReturn(reasonCodes);
		when(mockMapper.mapToDto(mockReasonCode)).thenReturn(dto);
		
		service = new ReasonCodeService();
		service.dao = mockDao;
		service.mapper = mockMapper;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockDao, mockReasonCode);
	}

	@Test
	public void testGetReasonCodes() {
		List<ReasonCodeDto> results = service.getReasonCodes(expectedRole);
		
		verify(mockDao).getReasonCodes(expectedRole);
		verify(mockMapper).mapToDto(mockReasonCode);
		
		assertEquals(1, results.size());
		
	}

}
