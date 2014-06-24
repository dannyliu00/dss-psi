package com.polaris.psi.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.PSIOrderSegment;
import com.polaris.psi.repository.entity.PSIProfileDetail;
import com.polaris.psi.resource.dto.OrderSegmentDto;

public class PSIOrderSegmentMapperTest {

	private PSIOrderSegmentMapper mapper;
	@Mock private PSIOrderSegment mockOS;
	@Mock private PSIProfileDetail mockDetail;
	private Integer expectedId, expectedSort, expectedComplianceId, expectedDealerId, expectedRecMinimum, 
		expectedRecommended, expectedRecMaximum;
	private Integer expectedHeaderId, expectedProfileOrderSegmentId, expectedRequestedQty, expectedReasonCode, 
		expectedDsmQty, expectedDsmReasonCode, expectedAdminQty, expectedAdminReasonCode, expectedFinalQty;
	private String expectedDealerComments, expectedDsmComments, expectedAdminComments, expectedName, 
		expectedSubSegment, expectedPeriodCode;	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		expectedSort = 1;
		expectedComplianceId = 11;
		expectedDealerId = 888;
		expectedRecMinimum = 1;
		expectedRecommended = 3;
		expectedRecMaximum = 5;
		expectedName = "UT Name";
		expectedSubSegment = "UT SubSegment";
		expectedPeriodCode = "UT CODE";
		
		expectedHeaderId = 222;
		expectedProfileOrderSegmentId = 999;
		expectedRequestedQty = 4;
		expectedReasonCode = 1;
		expectedDsmQty = 4;
		expectedDsmReasonCode = 1;
		expectedAdminQty = 4;
		expectedAdminReasonCode = 1;
		expectedFinalQty = 4;
		
		expectedDealerComments = "UT Dealer comments";
		expectedDsmComments = "UT DSM comments";
		expectedAdminComments = "UT Admin comments";
		
		when(mockOS.getComplianceId()).thenReturn(expectedComplianceId);
		when(mockOS.getDealerId()).thenReturn(expectedDealerId);
		when(mockOS.getId()).thenReturn(expectedId);
		when(mockOS.getName()).thenReturn(expectedName);
		when(mockOS.getPeriodCode()).thenReturn(expectedPeriodCode);
		when(mockOS.getRecMaximum()).thenReturn(expectedRecMaximum);
		when(mockOS.getRecMinimum()).thenReturn(expectedRecMinimum);
		when(mockOS.getRecommended()).thenReturn(expectedRecommended);
		when(mockOS.getSort()).thenReturn(expectedSort);
		when(mockOS.getSubSegment()).thenReturn(expectedSubSegment);
		
		when(mockDetail.getAdminComments()).thenReturn(expectedAdminComments);
		when(mockDetail.getAdminQty()).thenReturn(expectedAdminQty);
		when(mockDetail.getAdminReasonCode()).thenReturn(expectedAdminReasonCode);
		when(mockDetail.getDealerComments()).thenReturn(expectedDealerComments);
		when(mockDetail.getDsmComments()).thenReturn(expectedDsmComments);
		when(mockDetail.getDsmQty()).thenReturn(expectedDsmQty);
		when(mockDetail.getDsmReasonCode()).thenReturn(expectedDsmReasonCode);
		when(mockDetail.getFinalQty()).thenReturn(expectedFinalQty);
		when(mockDetail.getHeaderId()).thenReturn(expectedHeaderId);
		when(mockDetail.getId()).thenReturn(expectedId);
		when(mockDetail.getProfileOrderSegmentId()).thenReturn(expectedProfileOrderSegmentId);
		when(mockDetail.getReasonCode()).thenReturn(expectedReasonCode);
		when(mockDetail.getRequestedQty()).thenReturn(expectedRequestedQty);
		
		mapper = new PSIOrderSegmentMapper();
	}

	@Test
	public void testMapToDtoPSIOrderSegmentPSIProfileDetail() {
		mapper.mapToDto(mockOS, mockDetail);
		
		verify(mockOS).getComplianceId();
		verify(mockOS).getDealerId();
		verify(mockOS).getName();
		verify(mockOS).getPeriodCode();
		verify(mockOS).getRecMaximum();
		verify(mockOS).getRecMinimum();
		verify(mockOS).getRecommended();
		verify(mockOS).getSort();
		verify(mockOS).getSubSegment();
		
		verify(mockDetail).getAdminComments();
		verify(mockDetail).getAdminQty();
		verify(mockDetail).getAdminReasonCode();
		verify(mockDetail).getDealerComments();
		verify(mockDetail).getDsmComments();
		verify(mockDetail).getDsmQty();
		verify(mockDetail).getDsmReasonCode();
		verify(mockDetail).getFinalQty();
		verify(mockDetail).getHeaderId();
		verify(mockDetail).getId();
		verify(mockDetail).getProfileOrderSegmentId();
		verify(mockDetail).getReasonCode();
		verify(mockDetail).getRequestedQty();
	}

	@Test
	public void testMapToDtoPSIOrderSegment() {
		mapper.mapToDto(mockOS, null);
		
		verify(mockOS).getComplianceId();
		verify(mockOS).getDealerId();
		verify(mockOS).getName();
		verify(mockOS).getPeriodCode();
		verify(mockOS).getRecMaximum();
		verify(mockOS).getRecMinimum();
		verify(mockOS).getRecommended();
		verify(mockOS).getSort();
		verify(mockOS).getSubSegment();
		
		verifyZeroInteractions(mockDetail);
	}
	
	@Test
	public void testMapToDtoLists() {
		List<PSIOrderSegment> oses = new ArrayList<PSIOrderSegment>();
		oses.add(mockOS);
		List<PSIProfileDetail> details = new ArrayList<PSIProfileDetail>();
		details.add(mockDetail);
		
		List<OrderSegmentDto> results = mapper.mapToDto(oses, details);
		
		assertEquals(1, results.size());
		
		verify(mockOS).getComplianceId();
		verify(mockOS).getDealerId();
		verify(mockOS).getName();
		verify(mockOS).getPeriodCode();
		verify(mockOS).getRecMaximum();
		verify(mockOS).getRecMinimum();
		verify(mockOS).getRecommended();
		verify(mockOS).getSort();
		verify(mockOS).getSubSegment();
		
		verify(mockDetail).getAdminComments();
		verify(mockDetail).getAdminQty();
		verify(mockDetail).getAdminReasonCode();
		verify(mockDetail).getDealerComments();
		verify(mockDetail).getDsmComments();
		verify(mockDetail).getDsmQty();
		verify(mockDetail).getDsmReasonCode();
		verify(mockDetail).getFinalQty();
		verify(mockDetail).getHeaderId();
		verify(mockDetail).getId();
		verify(mockDetail, times(2)).getProfileOrderSegmentId();
		verify(mockDetail).getReasonCode();
		verify(mockDetail).getRequestedQty();
	}

	@Test
	public void testMapToDtoListsNullDetails() {
		List<PSIOrderSegment> oses = new ArrayList<PSIOrderSegment>();
		oses.add(mockOS);
		
		List<OrderSegmentDto> results = mapper.mapToDto(oses, new ArrayList<PSIProfileDetail>());
		
		assertEquals(1, results.size());
		
		verify(mockOS).getComplianceId();
		verify(mockOS).getDealerId();
		verify(mockOS).getName();
		verify(mockOS).getPeriodCode();
		verify(mockOS).getRecMaximum();
		verify(mockOS).getRecMinimum();
		verify(mockOS).getRecommended();
		verify(mockOS).getSort();
		verify(mockOS).getSubSegment();
		
		verifyZeroInteractions(mockDetail);
	}

}
