package com.polaris.psi.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.resource.dto.DealerDto;
import com.polaris.psi.service.DealerService;

public class DealerResourceTest {

	private DealerResource resource;
	@Mock private DealerService mockService;
	@Mock private DealerDto mockDto;
	private int id;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(mockService.getDealer(anyInt())).thenReturn(mockDto);
		
		id = 1111;

		resource = new DealerResource();
		resource.service = mockService;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockService);
	}

	@Test
	public void testGetDealer() {
		DealerDto actual = resource.getDealer(id);
		
		verify(mockService).getDealer(id);
		
		assertEquals(actual, mockDto);
	}

}
