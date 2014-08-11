/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.util.CommonUtils;

/**
 * DetailDataMapper handles mapping data from an OrderSegmentDto and DealerProfileHeader to 
 * a DealerProfileDetail object.  There are methods for mapping an entire DealerProfileDetail object and others
 * for mapping subsets of data (like just dealer-entered data or dsm-entered data).
 * 
 * @author bericks
 *
 */
@Component
public class DetailDataMapper {

	/**
	 * Creates a list of new DealerProfileDetail objects from a list of OrderSegmentDtos and a DealerProfileHeader
	 * 
	 * @param dtos
	 * @param header
	 * @return
	 */
	public List<DealerProfileDetail> createInitialDetail(List<OrderSegmentDto> dtos, DealerProfileHeader header) {
		List<DealerProfileDetail> details = new ArrayList<DealerProfileDetail>();
		for (OrderSegmentDto dto : dtos) {
			details.add(createInitialDetail(dto, header));
		}
		
		return details;
	}
	
	/**
	 * Creates a new DealerProfileDetail object from an OrderSegmentDto and a DealerProfileHeader
	 * 
	 * @param dto
	 * @param header
	 * @return
	 */
	public DealerProfileDetail createInitialDetail(OrderSegmentDto dto, DealerProfileHeader header) {
		DealerProfileDetail detail = new DealerProfileDetail();
		detail.setActual(dto.getActual());
		detail.setCreatedDate(new Date());
		detail.setCreatedProgram(Constants.PROGRAM_CODE);
		detail.setCreatedTime(new Date());
		detail.setCreatedUser(dto.getModifiedUserName());
		detail.setDealerComments(CommonUtils.setStringValue(dto.getDealerComments()));
		detail.setDealerReasonCode(CommonUtils.setIntegerValue(dto.getReasonCode()));
		detail.setHeader(header);
		detail.setPeriodCode(dto.getPeriodCode());
		detail.setProfileOrderSegmentId(dto.getProfileOrderSegmentId());
		detail.setAdminApprovedQty(CommonUtils.setIntegerValue(null));
		detail.setAdminComments(CommonUtils.setStringValue(null));
		detail.setAdminReasonCode(CommonUtils.setIntegerValue(null));
		detail.setChangedDate(new Date());
		detail.setChangedProgram(Constants.PROGRAM_CODE);
		detail.setChangedTime(new Date());
		detail.setChangedUser(dto.getModifiedUserName());
		detail.setDsmComments(CommonUtils.setStringValue(null));
		detail.setDsmReasonCode(CommonUtils.setIntegerValue(null));
		detail.setDsmRecommendedQty(CommonUtils.setIntegerValue(null));
		detail.setFinalQty(CommonUtils.setIntegerValue(null));
		
		return detail;
	}
	
	/**
	 * Updates an existing DealerProfileDetail object with recently changed dealer-entered data recorded in 
	 * an OrderSegmentDto object.
	 * 
	 * @param detail
	 * @param dto
	 */
	public void updateDealerEnteredDetails(DealerProfileDetail detail, OrderSegmentDto dto) {
		detail.setActual(dto.getActual());
		detail.setDealerReasonCode(CommonUtils.setIntegerValue(dto.getReasonCode()));
		detail.setDealerComments(CommonUtils.setStringValue(dto.getDealerComments()));
		detail.setChangedDate(new Date());
		detail.setChangedProgram(Constants.PROGRAM_CODE);
		detail.setChangedTime(new Date());
		detail.setChangedUser(dto.getModifiedUserName());
	}
	
	/**
	 * Updates an existing DealerProfileDetail object with recently changed DSM-entered data recorded in 
	 * an OrderSegmentDto object.
	 * 
	 * @param detail
	 * @param dto
	 * @param userName
	 */
	public void updateDsmEnteredDetails(DealerProfileDetail detail, OrderSegmentDto dto, String userName) {
		Date date = Calendar.getInstance().getTime();
		detail.setDsmRecommendedQty(CommonUtils.setIntegerValue(dto.getDsmQty()));
		detail.setDsmReasonCode(CommonUtils.setIntegerValue(dto.getDsmReasonCode()));
		detail.setDsmComments(CommonUtils.setStringValue(dto.getDsmComments()));
		detail.setChangedDate(date);
		detail.setChangedProgram(Constants.PROGRAM_CODE);
		detail.setChangedTime(date);
		detail.setChangedUser(userName);
	}

	/**
	 * Updates an existing DealerProfileDetail object with recently changed RSM-entered data recorded in 
	 * an OrderSegmentDto object.
	 * 
	 * @param detail
	 * @param dto
	 * @param userName
	 */
	public void updateRsmEnteredDetails(DealerProfileDetail detail, OrderSegmentDto dto, String userName) {
		Date date = Calendar.getInstance().getTime();
		detail.setAdminApprovedQty(CommonUtils.setIntegerValue(dto.getAdminQty()));
		detail.setAdminReasonCode(CommonUtils.setIntegerValue(dto.getAdminReasonCode()));
		detail.setAdminComments(CommonUtils.setStringValue(dto.getAdminComments()));
		detail.setChangedDate(date);
		detail.setChangedProgram(Constants.PROGRAM_CODE);
		detail.setChangedTime(date);
		detail.setChangedUser(userName);
	}
	
	/**
	 * Updates the finalQty field of a DealerProfileDetail object with the data entered from a DSM or RSM(Admin) as 
	 * determined by the approval status passed in.
	 * 
	 * @param detail
	 * @param dto
	 * @param status
	 */
	public void updateApprovedDetails(DealerProfileDetail detail, OrderSegmentDto dto, String status) {
		int finalValue = -1;
		
		switch (status) {
		case Constants.APPROVED_AS_REQUESTED:
			finalValue = dto.getActual();
			break;
		case Constants.APPROVED_W_CHANGES:
			finalValue = dto.getDsmQty();
			break;
		case Constants.APPROVED_COMPLIANT:
			finalValue = dto.getAdminQty();
			break;
		case Constants.APPROVED_NONCOMPLIANT:
			finalValue = dto.getAdminQty();
			break;
		default:
			break;
		}
		
		detail.setFinalQty(finalValue);
	}
	
}
