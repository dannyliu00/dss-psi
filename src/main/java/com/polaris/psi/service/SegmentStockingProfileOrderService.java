/**
 * 
 */
package com.polaris.psi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.dao.DealerProfileOrderDao;
import com.polaris.psi.repository.entity.PSIProfile;
import com.polaris.psi.repository.entity.SegmentStockingProfile;
import com.polaris.psi.repository.entity.SegmentStockingProfileOrder;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.service.mapper.SegmentStockingProfileOrderMapper;
import com.polaris.psi.util.AttributeHelper;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.dao.PSIProfileDao;
import com.polaris.pwf.dao.SegmentStockingProfileDao;

/**
 * @author bericks
 *
 */
@Service
public class SegmentStockingProfileOrderService {

	private static final SplunkLogger LOG = new SplunkLogger(SegmentStockingProfileOrderService.class);

    @Autowired
    AttributeHelper attributeHelper;

    @Autowired
	PSIProfileDao psiProfileDao;
	
	@Autowired
	SegmentStockingProfileDao stockingDao;
	
	@Autowired
	DealerProfileOrderDao orderDao;
	
	@Autowired
	SegmentStockingProfileOrderMapper mapper;
	
	public ProfileDetailsDto saveStockingProfiles(ProfileDetailsDto dto, String userName) {
		LOG.methodStart(PolarisIdentity.get(), "saveStockingProfiles");

		// check app property before trying to write the stocking profile
		Boolean sendToStockingProfile = new Boolean(attributeHelper.getAttribute("sendToStockingProfile"));

		if(sendToStockingProfile == null || !sendToStockingProfile) {
			LOG.trace(PolarisIdentity.get(), "saveStockingProfiles", 
					"sendToStockingProfile attribute is set to false in Attribute table.  Will not be "
					+ "sending the Inventory Profile data to OT003F");
			dto.setSuccessful(true);
			dto.setMessage(Constants.SAVE_SUCCESSFUL);
			LOG.methodEnd(PolarisIdentity.get(), "saveStockingProfiles");

			return dto;
		}
		
		LOG.trace(PolarisIdentity.get(), "saveStockingProfiles", 
				"sendToStockingProfile attribute is set to true in Attribute table.  Will now attempt to "
				+ "send the Inventory Profile data to OT003F");
		
		// get representative OrderSegment to use for data retrieval
		OrderSegmentDto representative = dto.getOrderSegments().get(0);
		int profileId = representative.getProfileId();
		int dealerId = representative.getDealerId();
		
		PSIProfile psiProfile = psiProfileDao.retrieveProfileById(profileId, dealerId);
		String type = psiProfile.getType();
		
		List<SegmentStockingProfile> stockingProfiles = retrieveStockingProfiles(dealerId, type);
		
		try {
			for (OrderSegmentDto orderSegment : dto.getOrderSegments()) {
				SegmentStockingProfile stockingProfile = getMatchingStockingProfile(stockingProfiles, orderSegment);
				
				if(isExistingStockingProfile(stockingProfile)) {
					String profileCode = stockingProfile.getStockingProfileProfileCode();
					String segmentCode = stockingProfile.getStockingProfileSegmentCode();
					int periodId = stockingProfile.getStockingProfilePeriodId();
					SegmentStockingProfileOrder profileOrder = orderDao.retrieve(profileCode, segmentCode, periodId);
					mapper.updateExistingOrder(profileOrder, orderSegment, userName);
					orderDao.update(profileOrder);
				} else {
					SegmentStockingProfileOrder profileOrder = mapper.createNewOrder(stockingProfile, orderSegment, userName);
					orderDao.insert(profileOrder);
				}
			}
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "saveStockingProfiles", e.getMessage());
			LOG.methodEnd(PolarisIdentity.get(), "saveStockingProfiles");

			throw e;
		}
		
		dto.setSuccessful(true);
		dto.setMessage(Constants.SAVE_SUCCESSFUL);
		LOG.methodEnd(PolarisIdentity.get(), "saveStockingProfiles");

		return dto;
	}
	
	protected List<SegmentStockingProfile> retrieveStockingProfiles(int dealerId, String type) {
		return stockingDao.retrieveSegmentProfilesList(dealerId, type);
	}
	
	protected SegmentStockingProfile getMatchingStockingProfile(List<SegmentStockingProfile> stockingProfiles, OrderSegmentDto orderSegment) {
		for (SegmentStockingProfile stockingProfile : stockingProfiles) {
			String orderSegmentCode = stockingProfile.getOrderSegmentCode();
			if(orderSegmentCode.equals(orderSegment.getOsCode())) 
				return stockingProfile;
		}
		
		return null;
	}
	
	protected boolean isExistingStockingProfile(SegmentStockingProfile profile) {
		return profile != null && 
				profile.getStockingProfileProfileCode() != null && 
				profile.getStockingProfileSegmentCode() != null && 
				profile.getStockingProfilePeriodId() != 0;
	}
	
}
