package com.polaris.psi.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.dao.DealerProfileHeaderStatusDao;
import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;
import com.polaris.psi.resource.dto.OrderSegmentDto;

public class OrderSegmentServiceTest {

	private int id;
	private String name;
	private String subSegment;
	private int recMinimum;
	private int recommended;
	private int recMaximum;
	private int actual;
	private String periodCode;
	private int periodId;
	private Date periodStartDate;
	
	private Integer complianceId;
	private Integer dealerId;
	private Integer sort;
	
	private String adminComments;
	private String dealerComments;
	private String dsmComments;
	
	private Integer adminQty;
	private Integer dsmQty;
	private Integer finalQty;
	
	private Integer adminReasonCode;
	private Integer dsmReasonCode;
	private Integer reasonCode;
	
	private Integer headerId;
	private Integer profileOrderSegmentId;
	
	private OrderSegmentService service;
	@Mock DealerProfileHeaderStatusDao mockStatusDao;
	@Mock DealerProfileHeaderStatus mockStatus;
	private Integer statusId;
	private List<DealerProfileHeaderStatus> mockAllStatii;
	private List<OrderSegmentDto> recordsToSave;
	@Mock private OrderSegmentDto mockOrderSegment;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		statusId = 999;
		recordsToSave = new ArrayList<OrderSegmentDto>();
		recordsToSave.add(mockOrderSegment);
		
		mockAllStatii = new ArrayList<DealerProfileHeaderStatus>();
		mockAllStatii.add(mockStatus);
		
		when(mockStatusDao.retrieveAll()).thenReturn(mockAllStatii);
		
		service = new OrderSegmentService();
		service.statusDao = mockStatusDao;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockStatusDao, mockStatus);
	}

	@Test
	public void testSaveQuantitiesCreateRecord() {
		when(mockOrderSegment.getHeaderId()).thenReturn(null);
		
		service.saveOrderSegments(recordsToSave);
		
		verify(mockStatusDao).retrieveAll();
	}

	@Test
	public void testSaveQuantitiesUpdateRecord() {
		
//		verify(mockStatusDao).retrieveById(statusId);
	}

}
