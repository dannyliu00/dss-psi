package com.polaris.psi.service.mapper;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.repository.entity.PSIProfile;

public class DsmDealerProfileMapperTest {

	private DsmDealerProfileMapper mapper;
	@Mock private PSIProfile mockProfile;
	@Mock private DealerAndDsm mockDealerAndDsm;
	private Integer dealerId, dsmId, profileId;
	private String dealerName, dsmName, productLine;
	private Date modifiedDate;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		dealerId = 999;
		dsmId = 888;
		profileId = 777;
		dealerName = "UT Dealer Name";
		dsmName = "UT DSM Name";
		productLine = "UT product line";
		modifiedDate = Calendar.getInstance().getTime();
		
		when(mockDealerAndDsm.getDealerId()).thenReturn(dealerId);
		when(mockDealerAndDsm.getDealerName()).thenReturn(dealerName);
		when(mockDealerAndDsm.getDsmId()).thenReturn(dsmId);
		when(mockDealerAndDsm.getDsmName()).thenReturn(dsmName);
		when(mockDealerAndDsm.getProductLine()).thenReturn(productLine);
		when(mockProfile.getId()).thenReturn(profileId);
		when(mockProfile.getLastModifiedDate()).thenReturn(modifiedDate);
		
		mapper = new DsmDealerProfileMapper();
	}

	@Test
	public void testMapToDto() {
		mapper.mapToDto(mockDealerAndDsm, mockProfile);
		
		verify(mockDealerAndDsm).getDealerId();
		verify(mockDealerAndDsm).getDealerName();
		verify(mockDealerAndDsm).getDsmId();
		verify(mockDealerAndDsm).getDsmName();
		verify(mockDealerAndDsm).getProductLine();
		verify(mockProfile).getId();
		verify(mockProfile).getLastModifiedDate();
	}

}
