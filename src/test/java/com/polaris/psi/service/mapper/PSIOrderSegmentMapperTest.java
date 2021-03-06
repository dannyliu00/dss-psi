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
	private Integer expectedId, expectedSort, expectedComplianceId, expectedDealerId, expectedProfileId, expectedRecMinimum, 
		expectedRecommended, expectedRecMaximum;
	private Integer expectedHeaderId, expectedProfileOrderSegmentId, expectedRequestedQty, expectedReasonCode, 
		expectedDsmQty, expectedDsmReasonCode, expectedAdminQty, expectedAdminReasonCode, expectedFinalQty;
	private String expectedDealerComments, expectedDsmComments, expectedAdminComments, expectedOsCode, 
		expectedOsName, expectedPeriodCode, expectedDealerEmail;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		expectedId = 999;
		expectedSort = 1;
		expectedComplianceId = 11;
		expectedProfileId = 1000;
		expectedDealerId = 888;
		expectedRecMinimum = 1;
		expectedRecommended = 3;
		expectedRecMaximum = 5;
		expectedOsCode = "UT Name";
		expectedOsName = "UT SubSegment";
		expectedPeriodCode = "UT CODE";
		expectedDealerEmail = "UT@local";
		
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
		when(mockOS.getProfileId()).thenReturn(expectedProfileId);
		when(mockOS.getDealerId()).thenReturn(expectedDealerId);
		when(mockOS.getId()).thenReturn(expectedId);
		when(mockOS.getOsCode()).thenReturn(expectedOsCode);
		when(mockOS.getPeriodCode()).thenReturn(expectedPeriodCode);
		when(mockOS.getRecMaximum()).thenReturn(expectedRecMaximum);
		when(mockOS.getRecMinimum()).thenReturn(expectedRecMinimum);
		when(mockOS.getRecommended()).thenReturn(expectedRecommended);
		when(mockOS.getSort()).thenReturn(expectedSort);
		when(mockOS.getOsName()).thenReturn(expectedOsName);
		
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
		when(mockDetail.getPeriodCode()).thenReturn(expectedPeriodCode);
		
		mapper = new PSIOrderSegmentMapper();
	}

	@Test
	public void testMapToDtoPSIOrderSegmentPSIProfileDetail() {
		OrderSegmentDto result = mapper.mapToDto(mockOS, mockDetail, expectedDealerEmail);
		
		assertEquals(expectedDealerEmail, result.getDealerEmail());
		
		verify(mockOS).getComplianceId();
		verify(mockOS).getDealerId();
		verify(mockOS).getProfileId();
		verify(mockOS).getOsCode();
		verify(mockOS).getPeriodCode();
		verify(mockOS).getRecMaximum();
		verify(mockOS).getRecMinimum();
		verify(mockOS).getRecommended();
		verify(mockOS).getSort();
		verify(mockOS).getOsName();
		
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
		verify(mockDetail).getPeriodCode();
	}

	@Test
	public void testMapToDtoPSIOrderSegment() {
		OrderSegmentDto result = mapper.mapToDto(mockOS, null, expectedDealerEmail);
		
		assertEquals(expectedDealerEmail, result.getDealerEmail());
		
		verify(mockOS).getProfileId();
		verify(mockOS).getComplianceId();
		verify(mockOS).getDealerId();
		verify(mockOS).getOsCode();
		verify(mockOS).getPeriodCode();
		verify(mockOS).getRecMaximum();
		verify(mockOS).getRecMinimum();
		verify(mockOS).getRecommended();
		verify(mockOS).getSort();
		verify(mockOS).getOsName();
		
		verifyZeroInteractions(mockDetail);
	}
	
	@Test
	public void testMapToDtoLists() {
		List<PSIOrderSegment> oses = new ArrayList<PSIOrderSegment>();
		oses.add(mockOS);
		List<PSIProfileDetail> details = new ArrayList<PSIProfileDetail>();
		details.add(mockDetail);
		
		List<OrderSegmentDto> results = mapper.mapToDto(oses, details, expectedDealerEmail);
		
		assertEquals(1, results.size());
		
		verify(mockOS).getProfileId();
		verify(mockOS).getComplianceId();
		verify(mockOS).getDealerId();
		verify(mockOS).getOsCode();
		verify(mockOS, times(2)).getPeriodCode();
		verify(mockOS).getRecMaximum();
		verify(mockOS).getRecMinimum();
		verify(mockOS).getRecommended();
		verify(mockOS).getSort();
		verify(mockOS).getOsName();
		
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
		verify(mockDetail, times(2)).getPeriodCode();
	}

	@Test
	public void testMapToDtoListsNoOS() {
		List<PSIOrderSegment> oses = new ArrayList<PSIOrderSegment>();
		List<PSIProfileDetail> details = new ArrayList<PSIProfileDetail>();
		details.add(mockDetail);
		
		List<OrderSegmentDto> results = mapper.mapToDto(oses, details, null);
		
		assertEquals(0, results.size());
		
		verifyZeroInteractions(mockOS, mockDetail);
	}

	@Test
	public void testMapToDtoListsNullOSes() {
		List<PSIOrderSegment> oses = new ArrayList<PSIOrderSegment>();
		List<PSIProfileDetail> details = new ArrayList<PSIProfileDetail>();
		details.add(mockDetail);
		
		List<OrderSegmentDto> results = mapper.mapToDto(oses, details, null);
		
		assertEquals(0, results.size());
		
		verifyZeroInteractions(mockOS, mockDetail);
	}

	@Test
	public void testMapToDtoListsNullDetails() {
		List<PSIOrderSegment> oses = new ArrayList<PSIOrderSegment>();
		oses.add(mockOS);
		
		List<OrderSegmentDto> results = mapper.mapToDto(oses, new ArrayList<PSIProfileDetail>(), null);
		
		assertEquals(1, results.size());
		
		verify(mockOS).getProfileId();
		verify(mockOS).getComplianceId();
		verify(mockOS).getDealerId();
		verify(mockOS).getOsCode();
		verify(mockOS).getPeriodCode();
		verify(mockOS).getRecMaximum();
		verify(mockOS).getRecMinimum();
		verify(mockOS).getRecommended();
		verify(mockOS).getSort();
		verify(mockOS).getOsName();
		
		verifyZeroInteractions(mockDetail);
	}

}
