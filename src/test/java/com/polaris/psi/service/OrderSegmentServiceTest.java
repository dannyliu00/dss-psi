package com.polaris.psi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.dao.DealerProfileDetailDao;
import com.polaris.psi.repository.dao.DealerProfileHeaderDao;
import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.service.mapper.DetailDataMapper;
import com.polaris.psi.service.mapper.HeaderDataMapper;

public class OrderSegmentServiceTest {

	private OrderSegmentService service;
	@Mock private StatusService mockStatusService;
	@Mock private DealerProfileHeaderStatus mockStatus;
	@Mock private DealerProfileHeaderDao mockHeaderDao;
	@Mock private DealerProfileHeader mockHeader;
	@Mock private DealerProfileDetailDao mockDetailDao;
	@Mock private DealerProfileDetail mockDetail, mockReturnedDetail;
	@Mock private HeaderDataMapper mockHeaderMapper;
	@Mock private DetailDataMapper mockDetailMapper;
	private Integer statusId, profileId, profileOrderSegmentId, headerId, detailId, dealerId, adminQty, dsmQty, finalQty, 
					adminReasonCode, dsmReasonCode, reasonCode;
	private String dealerEmail, userName, adminComments, dealerComments, dsmComments;
	private Date submittedDate, approvedDate;
	private List<DealerProfileHeaderStatus> mockAllStatii;
	private List<OrderSegmentDto> recordsToSave;
	@Mock private OrderSegmentDto mockOrderSegment;
	@Mock private ProfileDetailsDto mockProfileDetailsDto;
	private int actual;
	private boolean nonCompliant;
	
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
		nonCompliant = true;
		
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
		
		when(mockStatusService.getAllStatus()).thenReturn(mockAllStatii);
		when(mockStatusService.getPendingStatus()).thenReturn(mockStatus);
		when(mockStatusService.getApprovedWithChangesStatus()).thenReturn(mockStatus);
		when(mockHeaderDao.insert((DealerProfileHeader) anyObject())).thenReturn(mockHeader);
		when(mockHeaderDao.select(headerId)).thenReturn(mockHeader);
		when(mockDetailDao.insert((DealerProfileDetail) anyObject())).thenReturn(mockReturnedDetail);
		when(mockDetailDao.select(detailId)).thenReturn(mockDetail);
		
		when(mockProfileDetailsDto.getOrderSegments()).thenReturn(recordsToSave);

		// header data
		when(mockOrderSegment.getProfileId()).thenReturn(profileId);
		when(mockOrderSegment.getDealerId()).thenReturn(dealerId);
		when(mockOrderSegment.getDealerEmail()).thenReturn(dealerEmail);
		when(mockOrderSegment.getSubmittedDate()).thenReturn(submittedDate);
		when(mockOrderSegment.getApprovedDate()).thenReturn(approvedDate);
		when(mockOrderSegment.getModifiedUserName()).thenReturn(userName);
		when(mockOrderSegment.getHeaderId()).thenReturn(headerId);
		when(mockOrderSegment.isNonCompliant()).thenReturn(nonCompliant);
		
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
		
		when(mockHeader.getSubmittedDate()).thenReturn(submittedDate);
		when(mockHeader.getApprovedDate()).thenReturn(approvedDate);
		
		when(mockHeaderMapper.createNewNonSubmittedNonApprovedHeader(mockOrderSegment, mockStatus)).thenReturn(mockHeader);
		when(mockHeaderMapper.createNewSubmittedHeader(mockOrderSegment, mockStatus, nonCompliant)).thenReturn(mockHeader);
		when(mockDetailMapper.createInitialDetail(mockOrderSegment, mockHeader)).thenReturn(mockDetail);
		
		service = new OrderSegmentService();
		service.statusService = mockStatusService;
		service.headerDao = mockHeaderDao;
		service.detailDao = mockDetailDao;
		service.headerDataMapper = mockHeaderMapper;
		service.detailDataMapper = mockDetailMapper;
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSaveQuantitiesCreateRecordNoRecords() {
		when(mockProfileDetailsDto.getOrderSegments()).thenReturn(new ArrayList<OrderSegmentDto>());
		
		service.saveOrderSegmentQuantities(mockProfileDetailsDto);
		
		verify(mockProfileDetailsDto).getOrderSegments();
		
		verifyNoMoreInteractions(mockProfileDetailsDto);
		verifyZeroInteractions(mockDetail, mockReturnedDetail, mockStatus, mockOrderSegment, mockHeader, 
				mockDetailMapper, mockHeaderMapper, mockDetailDao, mockHeaderDao, mockStatusService);
	}
	
	@Test
	public void testSaveQuantitiesCreateRecord() throws Exception {
		when(mockOrderSegment.getHeaderId()).thenReturn(null);
		when(mockStatus.getDescription()).thenReturn(Constants.IN_PROGRESS_STATUS);
		
		service.saveOrderSegmentQuantities(mockProfileDetailsDto);
		
		verify(mockProfileDetailsDto).getOrderSegments();
		verify(mockStatusService).getAllStatus();
		verify(mockStatus).getDescription();
		verify(mockHeaderDao).insert((DealerProfileHeader) anyObject());
		verify(mockDetailDao).insert((DealerProfileDetail) anyObject());
		verify(mockHeader).getId();
		verify(mockReturnedDetail).getId();
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);
		verify(mockProfileDetailsDto).setOrderSegments(recordsToSave);
		
		verifyNoMoreInteractions(mockProfileDetailsDto, mockStatusService, mockStatus);
	}

	@Test
	public void testSaveQuantitiesUpdateRecord() throws Exception {
		when(mockOrderSegment.getHeaderId()).thenReturn(headerId);
		
		service.saveOrderSegmentQuantities(mockProfileDetailsDto);
		
		verify(mockProfileDetailsDto).getOrderSegments();
		verify(mockDetailDao).select(detailId);
		verify(mockDetailDao).update(mockDetail);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);
		verify(mockProfileDetailsDto).setOrderSegments(recordsToSave);
		
		verifyNoMoreInteractions(mockStatusService, mockStatus, mockProfileDetailsDto);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testSubmitQuantitiesCreateRecordNoRecords() {
		when(mockProfileDetailsDto.getOrderSegments()).thenReturn(new ArrayList<OrderSegmentDto>());
		
		service.submitOrderSegmentQuantities(mockProfileDetailsDto);
		
		verify(mockStatusService).getPendingStatus();
		
		verifyNoMoreInteractions(mockStatusService, mockProfileDetailsDto);
		verifyZeroInteractions(mockDetail, mockReturnedDetail, mockStatus, mockOrderSegment, mockHeader, 
				mockDetailMapper, mockHeaderMapper, mockDetailDao, mockHeaderDao);
	}
	
	@Test
	public void testSubmitQuantitiesCreateRecord() throws Exception {
		when(mockOrderSegment.getHeaderId()).thenReturn(null);
		when(mockStatusService.getPendingStatus()).thenReturn(mockStatus);

		service.submitOrderSegmentQuantities(mockProfileDetailsDto);

		verify(mockProfileDetailsDto).getOrderSegments();
		verify(mockOrderSegment).getHeaderId();
		verify(mockStatusService).getPendingStatus();
		verify(mockHeaderDao).insert((DealerProfileHeader) anyObject());
		verify(mockDetailMapper).createInitialDetail(mockOrderSegment, mockHeader);
		verify(mockDetailDao).insert((DealerProfileDetail) anyObject());
		verify(mockHeader).getId();
		verify(mockReturnedDetail).getId();
		verify(mockOrderSegment).setId(anyInt());
		verify(mockOrderSegment).setHeaderId(anyInt());
		verify(mockOrderSegment).isNonCompliant();
		verify(mockProfileDetailsDto).setOrderSegments(recordsToSave);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);

		verifyNoMoreInteractions(mockOrderSegment, mockStatusService, mockHeaderDao, mockDetailDao, 
				mockHeader, mockDetail, mockProfileDetailsDto);
	}

	@Test
	public void testSubmitQuantitiesUpdateRecord() throws Exception {
		when(mockStatusService.getPendingStatus()).thenReturn(mockStatus);

		service.submitOrderSegmentQuantities(mockProfileDetailsDto);

		verify(mockProfileDetailsDto).getOrderSegments();
		verify(mockStatusService).getPendingStatus();
		verify(mockOrderSegment, times(2)).getHeaderId();
		verify(mockDetailDao).select(detailId);
		verify(mockDetailMapper).updateDealerEnteredDetails(mockDetail, mockOrderSegment);
		verify(mockDetailDao).update(mockDetail);
		verify(mockHeaderDao).select(headerId);
		verify(mockHeaderMapper).updateExistingSubmittedHeader(mockHeader, mockStatus, nonCompliant);
		verify(mockHeaderDao).update(mockHeader);
		verify(mockHeader).getSubmittedDate();
		verify(mockOrderSegment).setSubmittedDate(submittedDate);
		verify(mockOrderSegment).getId();
		verify(mockOrderSegment).isNonCompliant();
		verify(mockProfileDetailsDto).setOrderSegments(recordsToSave);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);

		verifyNoMoreInteractions(mockOrderSegment, mockStatusService, mockHeaderDao, mockDetailDao, 
				mockHeader, mockDetail, mockProfileDetailsDto);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testDsmApproveWithChangesNoRecords() {
		
		service.dsmApproveWithChanges(new ArrayList<OrderSegmentDto>());
		
		verify(mockStatusService).getApprovedWithChangesStatus();
		
		verifyZeroInteractions(mockDetail, mockReturnedDetail, mockStatus, mockOrderSegment, mockHeader, 
				mockDetailMapper, mockHeaderMapper, mockDetailDao, mockHeaderDao);
	}
	
	@Test
	public void testDsmApproveWithChanges() throws Exception {
		
		service.dsmApproveWithChanges(recordsToSave);

		verify(mockOrderSegment).getId();
		verify(mockOrderSegment).getDsmQty();
		verify(mockOrderSegment, times(2)).getHeaderId();
		verify(mockOrderSegment).getModifiedUserName();
		verify(mockOrderSegment).isNonCompliant();
		verify(mockOrderSegment).setApprovedDate(approvedDate);
		verify(mockStatusService).getApprovedWithChangesStatus();
		verify(mockHeaderDao).select(headerId);
		verify(mockHeaderMapper).updateApprovedHeader(mockHeader, mockStatus, userName);
		verify(mockHeaderDao).update(mockHeader);
		verify(mockDetailDao).select(detailId);
		verify(mockDetailMapper).updateDsmEnteredDetails(mockDetail, mockOrderSegment);
		verify(mockDetailDao).update(mockDetail);
		verify(mockDetail).setFinalQty(dsmQty);
		verify(mockHeader).setNonCompliant(nonCompliant);
		verify(mockHeader).getApprovedDate();
		
		verifyNoMoreInteractions(mockOrderSegment, mockHeader, mockDetailMapper, mockHeaderMapper, mockDetailDao, mockHeaderDao, mockStatusService);
		verifyZeroInteractions(mockDetail, mockReturnedDetail, mockStatus);
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
