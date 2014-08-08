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

/**
 * @author bericks
 *
 */
@Component
public class SegmentStockingProfileOrderMapper {

	public SegmentStockingProfileOrder createNewOrder(SegmentStockingProfile profile, OrderSegmentDto orderSegment, String userName) {
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
		order.setProfilePeriod(profile.getStockingProfilePeriodId());
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
		
		return order;
	}
	
	public SegmentStockingProfileOrder updateExistingOrder(SegmentStockingProfileOrder order, OrderSegmentDto orderSegment, String userName) {
		
		Date date = Calendar.getInstance().getTime();
		
		order.setChangeDate(date);
		order.setChangeProgram(Constants.PROGRAM_CODE);
		order.setChangeTime(date);
		order.setChangeUser(userName);
		order.setRecommendedMinQty(orderSegment.getFinalQty());
		
		return order;
	}
}
