package com.polaris.psi.service;

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
	@Mock private LogService mockLogService;
	@Mock private StatusService mockStatusService;
	@Mock private DealerProfileHeaderStatus mockStatus;
	@Mock private DealerProfileHeaderDao mockHeaderDao;
	@Mock private DealerProfileHeader mockHeader;
	@Mock private DealerProfileDetailDao mockDetailDao;
	@Mock private DealerProfileDetail mockDetail, mockReturnedDetail;
	@Mock private HeaderDataMapper mockHeaderMapper;
	@Mock private DetailDataMapper mockDetailMapper;
	private Integer statusId, headerId, detailId;
	private String userName, email;
	private Date submittedDate, approvedDate;
	private List<OrderSegmentDto> recordsToSave;
	@Mock private OrderSegmentDto mockOrderSegment;
	@Mock private ProfileDetailsDto mockProfileDetailsDto;
	private boolean nonCompliant;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		statusId = 999;
		headerId = 998;
		detailId = 997;
		submittedDate = Calendar.getInstance().getTime();
		approvedDate = Calendar.getInstance().getTime();
		userName = "UT User";
		nonCompliant = false;
		email = "UT@local";
		
		recordsToSave = new ArrayList<OrderSegmentDto>();
		recordsToSave.add(mockOrderSegment);
		
		when(mockStatusService.getInProgressStatus()).thenReturn(mockStatus);
		when(mockStatusService.getPendingStatus()).thenReturn(mockStatus);
		when(mockStatusService.getApprovedWithChangesStatus()).thenReturn(mockStatus);
		when(mockStatusService.getApprovedAsRequestedStatus()).thenReturn(mockStatus);
		when(mockStatusService.getExceptionRequestedStatus()).thenReturn(mockStatus);
		when(mockStatusService.getSendToDealerStatus()).thenReturn(mockStatus);
		when(mockHeaderDao.insert((DealerProfileHeader) anyObject())).thenReturn(mockHeader);
		when(mockHeaderDao.select(headerId)).thenReturn(mockHeader);
		when(mockDetailDao.insert((DealerProfileDetail) anyObject())).thenReturn(mockReturnedDetail);
		when(mockDetailDao.select(detailId)).thenReturn(mockDetail);
		
		when(mockProfileDetailsDto.getOrderSegments()).thenReturn(recordsToSave);
		when(mockProfileDetailsDto.isNonCompliant()).thenReturn(nonCompliant);

		when(mockOrderSegment.getSubmittedDate()).thenReturn(submittedDate);
		when(mockOrderSegment.getModifiedUserName()).thenReturn(userName);
		when(mockOrderSegment.getHeaderId()).thenReturn(headerId);
		when(mockOrderSegment.getDealerEmail()).thenReturn(email);
		
		when(mockOrderSegment.getId()).thenReturn(detailId);
		
		when(mockStatus.getId()).thenReturn(statusId);
		when(mockHeader.getId()).thenReturn(headerId);
		when(mockDetail.getId()).thenReturn(detailId);
		
		when(mockHeader.getSubmittedDate()).thenReturn(submittedDate);
		when(mockHeader.getApprovedDate()).thenReturn(approvedDate);
		
		when(mockHeaderMapper.createNewNonSubmittedNonApprovedHeader(mockOrderSegment, mockStatus, nonCompliant)).thenReturn(mockHeader);
		when(mockHeaderMapper.createNewSubmittedHeader(mockOrderSegment, mockStatus, nonCompliant)).thenReturn(mockHeader);
		when(mockDetailMapper.createInitialDetail(mockOrderSegment, mockHeader)).thenReturn(mockDetail);
		
		service = new OrderSegmentService();
		service.logService = mockLogService;
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
		verify(mockProfileDetailsDto).isNonCompliant();
		verify(mockStatusService).getInProgressStatus();
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
		verify(mockProfileDetailsDto).isNonCompliant();
		verify(mockDetailDao).select(detailId);
		verify(mockDetailDao).update(mockDetail);
		verify(mockStatusService).getInProgressStatus();
		verify(mockHeaderDao).select(headerId);
		verify(mockHeaderMapper).updateChangedAttributes(mockHeader, mockStatus, userName, nonCompliant);
		verify(mockHeaderDao).update(mockHeader);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);
		verify(mockProfileDetailsDto).setOrderSegments(recordsToSave);
		
		verifyNoMoreInteractions(mockStatusService, mockStatus, mockProfileDetailsDto, mockHeaderDao, mockHeaderMapper);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testSubmitQuantitiesCreateRecordNoRecords() {
		when(mockProfileDetailsDto.getOrderSegments()).thenReturn(new ArrayList<OrderSegmentDto>());
		
		service.submitOrderSegmentQuantities(mockProfileDetailsDto);
		
		verify(mockStatusService).getPendingStatus();
		
		verifyNoMoreInteractions(mockStatusService, mockProfileDetailsDto);
		verifyZeroInteractions(mockDetail, mockReturnedDetail, mockStatus, mockOrderSegment, mockHeader, 
				mockDetailMapper, mockHeaderMapper, mockDetailDao, mockHeaderDao, mockLogService);
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
		verify(mockLogService).writeDealerChangesToLog(mockHeader, mockOrderSegment);
		verify(mockProfileDetailsDto).isNonCompliant();
		verify(mockProfileDetailsDto).setOrderSegments(recordsToSave);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);

		verifyNoMoreInteractions(mockOrderSegment, mockStatusService, mockHeaderDao, mockDetailDao, 
				mockHeader, mockDetail, mockProfileDetailsDto, mockLogService);
	}

	@Test
	public void testSubmitQuantitiesUpdateRecord() throws Exception {
		when(mockStatusService.getPendingStatus()).thenReturn(mockStatus);

		service.submitOrderSegmentQuantities(mockProfileDetailsDto);

		verify(mockProfileDetailsDto).getOrderSegments();
		verify(mockStatusService).getPendingStatus();
		verify(mockOrderSegment, times(2)).getHeaderId();
		verify(mockOrderSegment).getDealerEmail();
		verify(mockDetailDao).select(detailId);
		verify(mockDetailMapper).updateDealerEnteredDetails(mockDetail, mockOrderSegment);
		verify(mockDetailDao).update(mockDetail);
		verify(mockHeaderDao).select(headerId);
		verify(mockHeaderMapper).updateExistingSubmittedHeader(mockHeader, mockStatus, email, nonCompliant);
		verify(mockHeaderDao).update(mockHeader);
		verify(mockHeader).getSubmittedDate();
		verify(mockOrderSegment).setSubmittedDate(submittedDate);
		verify(mockOrderSegment).getId();
		verify(mockLogService).writeDealerChangesToLog(mockHeader, mockOrderSegment);
		verify(mockProfileDetailsDto).isNonCompliant();
		verify(mockProfileDetailsDto).setOrderSegments(recordsToSave);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);

		verifyNoMoreInteractions(mockOrderSegment, mockStatusService, mockHeaderDao, mockDetailDao, 
				mockHeader, mockDetail, mockProfileDetailsDto, mockLogService, mockHeaderMapper);
	}
	
	@Test
	public void testDsmApproveWithChangesNoRecords() {
		when(mockProfileDetailsDto.getOrderSegments()).thenReturn(new ArrayList<OrderSegmentDto>());
		
		service.dsmApproveWithChanges(mockProfileDetailsDto, userName);
		
		verify(mockStatusService).getApprovedWithChangesStatus();
		
		verifyNoMoreInteractions(mockStatusService);

		verifyZeroInteractions(mockDetail, mockReturnedDetail, mockStatus, mockOrderSegment, mockHeader, 
				mockDetailMapper, mockHeaderMapper, mockDetailDao, mockHeaderDao, mockLogService);
	}
	
	@Test
	public void testDsmApproveWithChanges() throws Exception {
		when(mockStatus.getDescription()).thenReturn(Constants.APPROVED_W_CHANGES);
		
		service.dsmApproveWithChanges(mockProfileDetailsDto, userName);

		verify(mockDetailDao).select(detailId);
		verify(mockDetailMapper).updateDsmEnteredDetails(mockDetail, mockOrderSegment, userName);
		verify(mockDetailDao).update(mockDetail);
		verify(mockStatusService).getApprovedWithChangesStatus();
		verify(mockStatus).getDescription();
		verify(mockProfileDetailsDto, times(2)).getOrderSegments();
		verify(mockProfileDetailsDto).isNonCompliant();
		verify(mockOrderSegment).getHeaderId();
		verify(mockOrderSegment).isNonCompliant();
		verify(mockOrderSegment).getId();
		verify(mockHeaderDao).select(headerId);
		verify(mockHeaderDao).update(mockHeader);
		verify(mockHeaderMapper).updateChangedAttributes(mockHeader, mockStatus, userName, nonCompliant);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);
		verify(mockLogService).writeDsmChangesToLog(mockHeader, mockOrderSegment);
		
		verifyNoMoreInteractions(mockStatusService, mockProfileDetailsDto, mockOrderSegment, mockHeaderDao, 
				mockHeaderMapper, mockLogService, mockStatus);
	}
	
	@Test
	public void testDsmSendToDealerNoRecords() {
		when(mockProfileDetailsDto.getOrderSegments()).thenReturn(new ArrayList<OrderSegmentDto>());
		
		service.dsmSendToDealer(mockProfileDetailsDto, userName);
		
		verify(mockStatusService).getSendToDealerStatus();
		verify(mockProfileDetailsDto).getOrderSegments();
		verify(mockProfileDetailsDto).setMessage(Constants.NO_RECORDS);
		verify(mockProfileDetailsDto).setSuccessful(false);
		
		verifyNoMoreInteractions(mockStatusService);

		verifyZeroInteractions(mockDetail, mockReturnedDetail, mockStatus, mockOrderSegment, mockHeader, 
				mockDetailMapper, mockHeaderMapper, mockDetailDao, mockHeaderDao, mockLogService);
	}
	
	@Test
	public void testDsmSendToDealer() {
		when(mockStatusService.getSendToDealerStatus()).thenReturn(mockStatus);
		when(mockStatus.getDescription()).thenReturn(Constants.RETURNED_TO_DEALER);
		
		service.dsmSendToDealer(mockProfileDetailsDto, userName);
		
		verify(mockDetailDao).select(detailId);
		verify(mockDetailMapper).updateDsmEnteredDetails(mockDetail, mockOrderSegment, userName);
		verify(mockDetail).setDsmRecommendedQty(-1);
		verify(mockDetailDao).update(mockDetail);
		verify(mockStatusService).getSendToDealerStatus();
		verify(mockStatus).getDescription();
		verify(mockProfileDetailsDto, times(2)).getOrderSegments();
		verify(mockProfileDetailsDto).isNonCompliant();
		verify(mockOrderSegment).getHeaderId();
		verify(mockOrderSegment).isNonCompliant();
		verify(mockOrderSegment).getId();
		verify(mockHeaderDao).select(headerId);
		verify(mockHeaderDao).update(mockHeader);
		verify(mockHeaderMapper).updateChangedAttributes(mockHeader, mockStatus, userName, nonCompliant);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);
		verify(mockLogService).writeDsmChangesToLog(mockHeader, mockOrderSegment);
		
		verifyNoMoreInteractions(mockStatusService, mockProfileDetailsDto, mockOrderSegment, mockHeaderDao, 
				mockHeaderMapper, mockLogService, mockStatus);
	}
	
	@Test
	public void testDsmApproveAsRequestedNoRecords() {
		when(mockProfileDetailsDto.getOrderSegments()).thenReturn(new ArrayList<OrderSegmentDto>());
		
		service.dsmApproveAsRequested(mockProfileDetailsDto, userName);
		
		verify(mockStatusService).getApprovedAsRequestedStatus();
		verify(mockProfileDetailsDto).getOrderSegments();
		verify(mockProfileDetailsDto).setMessage(Constants.NO_RECORDS);
		verify(mockProfileDetailsDto).setSuccessful(false);

		verifyNoMoreInteractions(mockStatusService);

		verifyZeroInteractions(mockDetail, mockReturnedDetail, mockStatus, mockOrderSegment, mockHeader, 
				mockDetailMapper, mockHeaderMapper, mockDetailDao, mockHeaderDao, mockLogService);
	}
	
	@Test
	public void testDsmApproveAsRequested() {
		when(mockStatusService.getSendToDealerStatus()).thenReturn(mockStatus);
		when(mockStatus.getDescription()).thenReturn(Constants.APPROVED_AS_REQUESTED);
		
		service.dsmApproveAsRequested(mockProfileDetailsDto, userName);
		
		verify(mockDetailDao).select(detailId);
		verify(mockDetailMapper).updateDsmEnteredDetails(mockDetail, mockOrderSegment, userName);
		verify(mockDetailDao).update(mockDetail);
		verify(mockStatusService).getApprovedAsRequestedStatus();
		verify(mockStatus).getDescription();
		verify(mockProfileDetailsDto, times(2)).getOrderSegments();
		verify(mockProfileDetailsDto).isNonCompliant();
		verify(mockOrderSegment).getHeaderId();
		verify(mockOrderSegment).isNonCompliant();
		verify(mockOrderSegment).getId();
		verify(mockHeaderDao).select(headerId);
		verify(mockHeaderDao).update(mockHeader);
		verify(mockHeaderMapper).updateChangedAttributes(mockHeader, mockStatus, userName, nonCompliant);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);
		verify(mockLogService).writeDsmChangesToLog(mockHeader, mockOrderSegment);
		
		verifyNoMoreInteractions(mockStatusService, mockProfileDetailsDto, mockOrderSegment, mockHeaderDao, 
				mockHeaderMapper, mockLogService, mockStatus);
	}
	
	@Test
	public void testDsmSubmitForExceptionNoRecords() {
		when(mockProfileDetailsDto.getOrderSegments()).thenReturn(new ArrayList<OrderSegmentDto>());
		
		service.dsmSubmitForException(mockProfileDetailsDto, userName);
		
		verify(mockStatusService).getExceptionRequestedStatus();
		verify(mockProfileDetailsDto).getOrderSegments();
		verify(mockProfileDetailsDto).setMessage(Constants.NO_RECORDS);
		verify(mockProfileDetailsDto).setSuccessful(false);

		verifyNoMoreInteractions(mockStatusService);

		verifyZeroInteractions(mockDetail, mockReturnedDetail, mockStatus, mockOrderSegment, mockHeader, 
				mockDetailMapper, mockHeaderMapper, mockDetailDao, mockHeaderDao, mockLogService);
	}
	
	@Test
	public void testDsmSubmitForException() {
		when(mockStatus.getDescription()).thenReturn(Constants.EXCEPTION_REQUESTED);
		
		service.dsmSubmitForException(mockProfileDetailsDto, userName);
		
		verify(mockDetailDao).select(detailId);
		verify(mockDetailMapper).updateDsmEnteredDetails(mockDetail, mockOrderSegment, userName);
		verify(mockDetailDao).update(mockDetail);
		verify(mockStatusService).getExceptionRequestedStatus();
		verify(mockStatus).getDescription();
		verify(mockProfileDetailsDto, times(2)).getOrderSegments();
		verify(mockProfileDetailsDto).isNonCompliant();
		verify(mockOrderSegment).getHeaderId();
		verify(mockOrderSegment).isNonCompliant();
		verify(mockOrderSegment).getId();
		verify(mockHeaderDao).select(headerId);
		verify(mockHeaderDao).update(mockHeader);
		verify(mockHeaderMapper).updateChangedAttributes(mockHeader, mockStatus, userName, nonCompliant);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);
		verify(mockLogService).writeDsmChangesToLog(mockHeader, mockOrderSegment);
		
		verifyNoMoreInteractions(mockStatusService, mockProfileDetailsDto, mockOrderSegment, 
				mockHeaderDao, mockHeaderMapper, mockLogService, mockStatus);
	}
	
	@Test
	public void testDsmSaveChangesNoRecords() {
		when(mockProfileDetailsDto.getOrderSegments()).thenReturn(new ArrayList<OrderSegmentDto>());
		
		service.dsmSaveChanges(mockProfileDetailsDto, userName);
		
		verify(mockStatusService).getPendingStatus();
		verify(mockProfileDetailsDto).getOrderSegments();
		verify(mockProfileDetailsDto).setMessage(Constants.NO_RECORDS);
		verify(mockProfileDetailsDto).setSuccessful(false);

		verifyNoMoreInteractions(mockStatusService);

		verifyZeroInteractions(mockDetail, mockReturnedDetail, mockStatus, mockOrderSegment, mockHeader, 
				mockDetailMapper, mockHeaderMapper, mockDetailDao, mockHeaderDao, mockLogService);
	}
	
	@Test
	public void testDsmSaveChanges() {
		when(mockStatus.getDescription()).thenReturn(Constants.PENDING_STATUS);
		
		service.dsmSaveChanges(mockProfileDetailsDto, userName);
		
		verify(mockDetailDao).select(detailId);
		verify(mockDetailMapper).updateDsmEnteredDetails(mockDetail, mockOrderSegment, userName);
		verify(mockDetailDao).update(mockDetail);
		verify(mockStatusService).getPendingStatus();
		verify(mockStatus).getDescription();
		verify(mockProfileDetailsDto, times(2)).getOrderSegments();
		verify(mockProfileDetailsDto).isNonCompliant();
		verify(mockOrderSegment).getHeaderId();
		verify(mockOrderSegment).isNonCompliant();
		verify(mockOrderSegment).getId();
		verify(mockHeaderDao).select(headerId);
		verify(mockHeaderDao).update(mockHeader);
		verify(mockHeaderMapper).updateChangedAttributes(mockHeader, mockStatus, userName, nonCompliant);
		verify(mockProfileDetailsDto).setMessage(Constants.SAVE_SUCCESSFUL);
		verify(mockProfileDetailsDto).setSuccessful(true);
		verify(mockLogService).writeDsmChangesToLog(mockHeader, mockOrderSegment);
		
		verifyNoMoreInteractions(mockStatusService, mockProfileDetailsDto, mockOrderSegment, mockHeaderDao, 
				mockHeaderMapper, mockLogService, mockStatus);
	}
	
}
