/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.SegmentStockingProfile;
import com.polaris.psi.repository.entity.SegmentStockingProfileOrder;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.util.CommonUtils;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * SegmentStockingProfileOrderMapper maps attributes from a SegmentStockingProfile and an OrderSegmentDto to a 
 * SegmentStockingProfileOrder object.
 * 
 * @author bericks
 *
 */
@Component
public class SegmentStockingProfileOrderMapper {

	private static final SplunkLogger LOG = new SplunkLogger(SegmentStockingProfileOrderMapper.class);
	
	/**
	 * Creates a new SegmentStockingProfileOrder object from a SegmentStockingProfile and OrderSegmentDto.
	 * 
	 * @param profile
	 * @param orderSegment
	 * @param userName
	 * @return
	 */
	public SegmentStockingProfileOrder createNewOrder(SegmentStockingProfile profile, OrderSegmentDto orderSegment, String userName) {
		LOG.methodStart(PolarisIdentity.get(), "createNewOrder");

		SegmentStockingProfileOrder order = new SegmentStockingProfileOrder();
		
		Date date = Calendar.getInstance().getTime();
		
		order.setChangeDate(date);
		order.setChangeProgram(Constants.PROGRAM_CODE);
		order.setChangeTime(date);
		order.setChangeUser(userName);
		order.setCreateDate(date);
		order.setCreateProgram(Constants.PROGRAM_CODE);
		order.setCreateTime(date);
		order.setCreateUser(userName);
		order.setDetailToken(profile.getSegmentCode());
		order.setProfileName(profile.getProfileCode());
		order.setProfilePeriod(orderSegment.getPeriodId());
		order.setRecommendedMinQty(orderSegment.getFinalQty());
		order.setRequiredToStock("Y");
		order.setUserDef1(CommonUtils.setStringValue(null));
		order.setUserDef2(CommonUtils.setStringValue(null));
		order.setUserDef3(CommonUtils.setStringValue(null));
		order.setUserDef4(CommonUtils.setDate(null));
		order.setUserDef5(CommonUtils.setDate(null));
		order.setUserDef6(CommonUtils.setStringValue(null));
		order.setUserDef7(CommonUtils.setIntegerValue(null));
		order.setUserDef8(CommonUtils.setIntegerValue(null));
		order.setUserDef9(CommonUtils.setIntegerValue(null));
		order.setUserDef10(CommonUtils.setStringValue(null));
		
		LOG.methodEnd(PolarisIdentity.get(), "createNewOrder");

		return order;
	}
	
	/**
	 * Updates the 'changed' and minimum quantity attributes of an existing SegmentStockingProfileOrder object.
	 * 
	 * @param order
	 * @param orderSegment
	 * @param userName
	 * @return
	 */
	public SegmentStockingProfileOrder updateExistingOrder(SegmentStockingProfileOrder order, OrderSegmentDto orderSegment, String userName) {
		
		LOG.methodStart(PolarisIdentity.get(), "updateExistingOrder");

		Date date = Calendar.getInstance().getTime();
		
		order.setChangeDate(date);
		order.setChangeProgram(Constants.PROGRAM_CODE);
		order.setChangeTime(date);
		order.setChangeUser(userName);
		order.setRecommendedMinQty(orderSegment.getFinalQty());
		
		LOG.methodEnd(PolarisIdentity.get(), "updateExistingOrder");

		return order;
	}
}
