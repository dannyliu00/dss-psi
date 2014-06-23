/**
 * 
 */
package com.polaris.psi.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.polaris.psi.repository.dao.DealerAndDsmDao;
import com.polaris.psi.repository.dao.DealerInfoDao;
import com.polaris.psi.repository.entity.Dealer;
import com.polaris.psi.repository.entity.DealerAndDsm;
import com.polaris.psi.resource.dto.DealerDto;
import com.polaris.psi.service.mapper.DealerMapper;

/**
 * @author bericks
 *
 */
public class DealerServiceTest {

	private DealerService service;
	@Mock private DealerInfoDao mockDao;
	@Mock private DealerAndDsmDao mockDsmDao;
	@Mock private Dealer mockDealer;
	@Mock private DealerAndDsm mockDsm;
	@Mock private DealerMapper mockMapper;
	@Mock private DealerDto mockDto;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		when(mockDao.select(anyInt())).thenReturn(mockDealer);
		when(mockDsmDao.selectByDealerId(anyInt())).thenReturn(mockDsm);
		when(mockMapper.mapToDto(mockDealer, mockDsm)).thenReturn(mockDto);
		
		service = new DealerService();
		service.dealerInfoDao = mockDao;
		service.dsmDao = mockDsmDao;
		service.mapper = mockMapper;
	}

	@Test
	public void testGetDealer() {
		int id = 9999;
		Integer idObject = new Integer(id);
		
		DealerDto result = service.getDealer(id);
		
		verify(mockDao).select(idObject);
		verify(mockDsmDao).selectByDealerId(idObject);

		verify(mockMapper).mapToDto(mockDealer, mockDsm);
		assertEquals(result, mockDto);
	}

}
