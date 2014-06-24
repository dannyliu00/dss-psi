package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.PSISegment;
import com.polaris.psi.resource.dto.SegmentDto;

public class PSISegmentMapperTest {

	private PSISegmentMapper mapper;
	@Mock PSISegment mockEntity;
	private int expectedId;
	private String expectedName, expectedSubsegment;

	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		expectedName = "UT Name";
		expectedSubsegment = "UT SubSegment";
		
		when(mockEntity.getId()).thenReturn(expectedId);
		when(mockEntity.getName()).thenReturn(expectedName);
		when(mockEntity.getSubSegment()).thenReturn(expectedSubsegment);
		
		mapper = new PSISegmentMapper();
	}

	@Test
	public void testMapToDtoPSISegment() {
		SegmentDto result = mapper.mapToDto(mockEntity);
		
		verify(mockEntity).getId();
		verify(mockEntity).getName();
		verify(mockEntity).getSubSegment();
		
		assertEquals(expectedId, result.getSegmentId());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedSubsegment, result.getSubSegment());
	}

}
