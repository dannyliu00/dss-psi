package com.polaris.psi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.dao.DealerProfileDetailDao;
import com.polaris.psi.repository.dao.DealerProfileHeaderDao;
import com.polaris.psi.repository.dao.DealerProfileHeaderStatusDao;
import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.psi.repository.entity.DealerProfileHeader;
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
	private Integer sort;
	
	private Integer dealerId;
	
	private String adminComments;
	private String dealerComments;
	private String dsmComments;
	
	private Integer adminQty;
	private Integer dsmQty;
	private Integer finalQty;
	
	private Integer adminReasonCode;
	private Integer dsmReasonCode;
	private Integer reasonCode;
	
	private OrderSegmentService service;
	@Mock DealerProfileHeaderStatusDao mockStatusDao;
	@Mock DealerProfileHeaderStatus mockStatus;
	@Mock DealerProfileHeaderDao mockHeaderDao;
	@Mock DealerProfileHeader mockHeader;
	@Mock DealerProfileDetailDao mockDetailDao;
	@Mock DealerProfileDetail mockDetail;
	private Integer statusId, profileId, profileOrderSegmentId, headerId, detailId;
	private String dealerEmail, userName;
	private Date submittedDate, approvedDate;
	private List<DealerProfileHeaderStatus> mockAllStatii;
	private List<OrderSegmentDto> recordsToSave;
	@Mock private OrderSegmentDto mockOrderSegment;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		statusId = 999;
		headerId = 998;
		detailId = 997;
		profileId = 1;
		dealerId = 2;
		dealerEmail = "dealer@local";
		submittedDate = Calendar.getInstance().getTime();
		approvedDate = Calendar.getInstance().getTime();
		userName = "UT User";
		
		profileOrderSegmentId = 88;
		reasonCode = 3;
		dealerComments = "UT dealer comments";
		
		actual = 3;
		dsmQty = 5;
		dsmReasonCode = 10;
		dsmComments = "UT dsm comments";
		
		adminQty = 6;
		adminReasonCode = 10;
		adminComments = "UT admin comments";
		
		finalQty = 6;
		
		recordsToSave = new ArrayList<OrderSegmentDto>();
		recordsToSave.add(mockOrderSegment);
		
		mockAllStatii = new ArrayList<DealerProfileHeaderStatus>();
		mockAllStatii.add(mockStatus);
		
		when(mockStatusDao.selectAll()).thenReturn(mockAllStatii);
		when(mockHeaderDao.insert((DealerProfileHeader) anyObject())).thenReturn(mockHeader);
		when(mockDetailDao.insert((DealerProfileDetail) anyObject())).thenReturn(mockDetail);
		when(mockDetailDao.select(detailId)).thenReturn(mockDetail);

		// header data
		when(mockOrderSegment.getProfileId()).thenReturn(profileId);
		when(mockOrderSegment.getDealerId()).thenReturn(dealerId);
		when(mockOrderSegment.getDealerEmail()).thenReturn(dealerEmail);
		when(mockOrderSegment.getSubmittedDate()).thenReturn(submittedDate);
		when(mockOrderSegment.getApprovedDate()).thenReturn(approvedDate);
		when(mockOrderSegment.getModifiedUserName()).thenReturn(userName);
		
		// detail data
		when(mockOrderSegment.getId()).thenReturn(detailId);
		when(mockOrderSegment.getProfileOrderSegmentId()).thenReturn(profileOrderSegmentId);
		when(mockOrderSegment.getActual()).thenReturn(actual);
		when(mockOrderSegment.getReasonCode()).thenReturn(reasonCode);
		when(mockOrderSegment.getDealerComments()).thenReturn(dealerComments);
		when(mockOrderSegment.getDsmQty()).thenReturn(dsmQty);
		when(mockOrderSegment.getDsmReasonCode()).thenReturn(dsmReasonCode);
		when(mockOrderSegment.getDsmComments()).thenReturn(dsmComments);
		when(mockOrderSegment.getAdminQty()).thenReturn(adminQty);
		when(mockOrderSegment.getAdminReasonCode()).thenReturn(adminReasonCode);
		when(mockOrderSegment.getAdminComments()).thenReturn(adminComments);
		when(mockOrderSegment.getFinalQty()).thenReturn(finalQty);
		
		when(mockStatus.getId()).thenReturn(statusId);
		when(mockHeader.getId()).thenReturn(headerId);
		when(mockDetail.getId()).thenReturn(detailId);
		
		service = new OrderSegmentService();
		service.statusDao = mockStatusDao;
		service.headerDao = mockHeaderDao;
		service.detailDao = mockDetailDao;
	}
	
	@After
	public void tearDown() {
		verifyNoMoreInteractions(mockStatusDao, mockStatus);
	}

	@Test
	public void testSaveQuantitiesCreateRecord() {
		when(mockOrderSegment.getHeaderId()).thenReturn(null);
		when(mockStatus.getDescription()).thenReturn(Constants.IN_PROGRESS_STATUS);
		
		service.saveOrderSegmentQuantities(recordsToSave);
		
		verify(mockStatusDao).selectAll();
		verify(mockStatus).getDescription();
		verify(mockHeaderDao).insert((DealerProfileHeader) anyObject());
		verify(mockDetailDao).insert((DealerProfileDetail) anyObject());
		verify(mockOrderSegment).getProfileId();
		verify(mockOrderSegment).getDealerId();
		verify(mockOrderSegment).getDealerEmail();
		verify(mockOrderSegment, times(2)).getModifiedUserName();
		verify(mockOrderSegment).getProfileOrderSegmentId();
		verify(mockOrderSegment).getActual();
		verify(mockOrderSegment).getReasonCode();
		verify(mockOrderSegment).getDealerComments();
		verify(mockHeader).getId();
		verify(mockDetail).getId();
	}

	@Test
	public void testSaveQuantitiesUpdateRecord() {
		when(mockOrderSegment.getHeaderId()).thenReturn(headerId);
		
		service.saveOrderSegmentQuantities(recordsToSave);
		
		verify(mockHeaderDao).select(headerId);
		verify(mockDetailDao).select(detailId);
		verify(mockDetail).setActual(actual);
		verify(mockDetail).setDealerComments(dealerComments);
		verify(mockDetail).setDealerReasonCode(reasonCode);
		verify(mockOrderSegment).getActual();
		verify(mockOrderSegment).getDealerComments();
		verify(mockOrderSegment).getReasonCode();
		verify(mockOrderSegment).getModifiedUserName();
		verify(mockDetailDao).update(mockDetail);
	}

	@Test
	public void testGetDefaultStatus() {
		when(mockStatus.getDescription()).thenReturn(Constants.IN_PROGRESS_STATUS);
		
		DealerProfileHeaderStatus result = service.getDefaultStatus(mockAllStatii);
		
		verify(mockStatus).getDescription();
		
		assertEquals(mockStatus, result);
	}

	@Test
	public void testGetDefaultStatusReturnNull() {
		when(mockStatus.getDescription()).thenReturn("Status");
		
		DealerProfileHeaderStatus result = service.getDefaultStatus(mockAllStatii);
		
		verify(mockStatus).getDescription();
		
		assertEquals(null, result);
	}
	
}
