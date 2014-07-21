package com.polaris.psi.service.mapper;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.resource.dto.OrderSegmentDto;

public class PSILogMapperTest {

	private PSILogMapper mapper;
	@Mock private DealerProfileHeader mockHeader;
	@Mock private OrderSegmentDto mockOS;
	private String userName, adminComments, dealerComments, orderSegmentName;
	private Date date;
	private int headerId, adminQty, adminReasonCode, dealerQty, dealerReasonCode, detailId;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userName = "UTUser";
		headerId = 999;
		adminComments = "admin comments";
		adminQty = 1;
		adminReasonCode = 9;
		dealerComments = "dealer comments";
		dealerQty = 1;
		dealerReasonCode = 9;
		detailId = 888;
		orderSegmentName = "os name";
		
		date = Calendar.getInstance().getTime();
		
		when(mockHeader.getApprovedDate()).thenReturn(date);
		when(mockHeader.getApprovedTime()).thenReturn(date);
		when(mockHeader.getId()).thenReturn(headerId);
		when(mockHeader.getSubmittedDate()).thenReturn(date);
		when(mockHeader.getSubmittedTime()).thenReturn(date);
		when(mockHeader.getChangeUser()).thenReturn(userName);
		
		when(mockOS.getAdminComments()).thenReturn(adminComments);
		when(mockOS.getAdminQty()).thenReturn(adminQty);
		when(mockOS.getAdminReasonCode()).thenReturn(adminReasonCode);
		when(mockOS.getDealerComments()).thenReturn(dealerComments);
		when(mockOS.getActual()).thenReturn(dealerQty);
		when(mockOS.getReasonCode()).thenReturn(dealerReasonCode);
		when(mockOS.getSubmittedDate()).thenReturn(date);
		when(mockOS.getSubmittedDate()).thenReturn(date);
		when(mockOS.getId()).thenReturn(detailId);
		when(mockOS.getName()).thenReturn(orderSegmentName);
		
		mapper = new PSILogMapper();
	}

	@Test
	public void testMapDealerDataToLogWithOrderSegmentDto() {

		mapper.mapDealerDataToLog(mockHeader, mockOS);
		
		verify(mockHeader).getApprovedDate();
		verify(mockHeader).getApprovedTime();
		verify(mockHeader).getId();
		verify(mockHeader).getSubmittedDate();
		verify(mockHeader).getSubmittedTime();
		verify(mockHeader, times(3)).getChangeUser();
		verify(mockOS).getAdminComments();
		verify(mockOS).getAdminQty();
		verify(mockOS).getAdminReasonCode();
		verify(mockOS).getDealerComments();
		verify(mockOS).getActual();
		verify(mockOS).getReasonCode();
		verify(mockOS, times(2)).getSubmittedDate();
		verify(mockOS).getId();
		verify(mockOS).getName();
		
		verifyNoMoreInteractions(mockHeader, mockOS);
	}

}
