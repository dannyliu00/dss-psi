package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.PSISegment;
import com.polaris.psi.resource.dto.SegmentDto;

public class PSISegmentMapperTest {

	private PSISegmentMapper mapper;
	@Mock PSISegment mockEntity;
	@Mock SegmentDto mockDto;
	private int expectedId, expectedMin, expectedMax, expectedOSCount;
	private String expectedName, expectedSubsegment;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		expectedName = "UT Name";
		expectedSubsegment = "UT SubSegment";
		expectedMin = 1;
		expectedMax = 5;
		expectedOSCount = 2;
		
		when(mockEntity.getId()).thenReturn(expectedId);
		when(mockEntity.getName()).thenReturn(expectedName);
		when(mockEntity.getRecMaximum()).thenReturn(expectedMax);
		when(mockEntity.getRecMinimum()).thenReturn(expectedMin);
		when(mockEntity.getRecOsCount()).thenReturn(expectedOSCount);
		when(mockEntity.getSubSegment()).thenReturn(expectedSubsegment);
		
		mapper = new PSISegmentMapper();
	}

	@Test
	public void testMapToDtoPSISegment() {
		SegmentDto result = mapper.mapToDto(mockEntity);
		
		verify(mockEntity).getId();
		verify(mockEntity).getName();
		verify(mockEntity).getRecMaximum();
		verify(mockEntity).getRecMinimum();
		verify(mockEntity).getRecOsCount();
		verify(mockEntity).getSubSegment();
		
		assertEquals(expectedId, result.getSegmentId());
		assertEquals(expectedName, result.getName());
		assertEquals(expectedMax, result.getRecMaximum());
		assertEquals(expectedMin, result.getRecMinimum());
		assertEquals(expectedOSCount, result.getRecommendedOSCount());
		assertEquals(expectedSubsegment, result.getSubSegments().get(0));
	}
	
	@Test
	public void testDoesListContainTrue() {
		List<SegmentDto> segments = new ArrayList<SegmentDto>();
		SegmentDto segment = new SegmentDto();
		segment.setName(expectedName);
		segments.add(segment);
		
		when(mockDto.getName()).thenReturn(expectedName);
		
		assertEquals(true, mapper.doesListContain(segments, mockDto));
	}
	
	@Test
	public void testDoesListContainFalse() {
		List<SegmentDto> segments = new ArrayList<SegmentDto>();
		SegmentDto segment = new SegmentDto();
		segment.setName(expectedName + " unique");
		segments.add(segment);
		
		when(mockDto.getName()).thenReturn(expectedName);
		
		assertEquals(false, mapper.doesListContain(segments, mockDto));
	}
	
	@Test
	public void testMapSubSegmentToSegment() {
		List<SegmentDto> segments = new ArrayList<SegmentDto>();
		SegmentDto segment = new SegmentDto();
		segment.setName(expectedName);
		String subSegment = expectedSubsegment + " unique";
		segment.addSubSegment(subSegment);
		segments.add(segment);
		
		mapper.mapSubSegmentToSegment(segments, mockEntity);
		List<String> results = segments.get(0).getSubSegments();
		assertEquals(2, results.size());
	}

}
