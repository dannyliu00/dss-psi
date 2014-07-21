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

import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.PSIOrderSegment;

public class PSILogMapperTest {

	private PSILogMapper mapper;
	@Mock private DealerProfileHeader mockHeader;
	@Mock private DealerProfileDetail mockDetail;
	@Mock private PSIOrderSegment mockOS;
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
		
		when(mockDetail.getAdminComments()).thenReturn(adminComments);
		when(mockDetail.getAdminApprovedQty()).thenReturn(adminQty);
		when(mockDetail.getAdminReasonCode()).thenReturn(adminReasonCode);
		when(mockDetail.getDealerComments()).thenReturn(dealerComments);
		when(mockDetail.getActual()).thenReturn(dealerQty);
		when(mockDetail.getDealerReasonCode()).thenReturn(dealerReasonCode);
		when(mockDetail.getChangedDate()).thenReturn(date);
		when(mockDetail.getChangedTime()).thenReturn(date);
		when(mockDetail.getId()).thenReturn(detailId);
		
		when(mockOS.getName()).thenReturn(orderSegmentName);
		
		mapper = new PSILogMapper();
	}

	@Test
	public void testMapDealerDataToLog() {

		mapper.mapDealerDataToLog(mockHeader, mockDetail, mockOS, userName);
		
		verify(mockHeader).getApprovedDate();
		verify(mockHeader).getApprovedTime();
		verify(mockHeader).getId();
		verify(mockHeader).getSubmittedDate();
		verify(mockHeader).getSubmittedTime();
		verify(mockDetail).getAdminComments();
		verify(mockDetail).getAdminApprovedQty();
		verify(mockDetail).getAdminReasonCode();
		verify(mockDetail).getDealerComments();
		verify(mockDetail).getActual();
		verify(mockDetail).getDealerReasonCode();
		verify(mockDetail).getChangedDate();
		verify(mockDetail).getChangedTime();
		verify(mockDetail).getId();
		verify(mockOS).getName();
		
		verifyNoMoreInteractions(mockHeader, mockDetail, mockOS);
	}

}
