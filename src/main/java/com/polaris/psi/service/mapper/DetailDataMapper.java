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

/**
 * @author bericks
 *
 */
@Component
public class DetailDataMapper {

	public List<DealerProfileDetail> createInitialDetail(List<OrderSegmentDto> dtos, DealerProfileHeader header) {
		List<DealerProfileDetail> details = new ArrayList<DealerProfileDetail>();
		for (OrderSegmentDto dto : dtos) {
			details.add(createInitialDetail(dto, header));
		}
		
		return details;
	}
	
	public DealerProfileDetail createInitialDetail(OrderSegmentDto dto, DealerProfileHeader header) {
		DealerProfileDetail detail = new DealerProfileDetail();
		detail.setActual(dto.getActual());
		detail.setCreatedDate(new Date());
		detail.setCreatedProgram(Constants.PROGRAM_CODE);
		detail.setCreatedTime(new Date());
		detail.setCreatedUser(dto.getModifiedUserName());
		detail.setDealerComments(setStringValue(dto.getDealerComments()));
		detail.setDealerReasonCode(setIntegerValue(dto.getReasonCode()));
		detail.setHeader(header);
		detail.setPeriodCode(dto.getPeriodCode());
		detail.setProfileOrderSegmentId(dto.getProfileOrderSegmentId());
		detail.setAdminApprovedQty(setIntegerValue(null));
		detail.setAdminComments(setStringValue(null));
		detail.setAdminReasonCode(setIntegerValue(null));
		detail.setChangedDate(new Date());
		detail.setChangedProgram(Constants.PROGRAM_CODE);
		detail.setChangedTime(new Date());
		detail.setChangedUser(dto.getModifiedUserName());
		detail.setDsmComments(setStringValue(null));
		detail.setDsmReasonCode(setIntegerValue(null));
		detail.setDsmRecommendedQty(setIntegerValue(null));
		detail.setFinalQty(setIntegerValue(null));
		
		return detail;
	}
	
	public void updateDealerEnteredDetails(DealerProfileDetail detail, OrderSegmentDto dto) {
		detail.setActual(dto.getActual());
		detail.setDealerReasonCode(dto.getReasonCode());
		detail.setDealerComments(setStringValue(dto.getDealerComments()));
		detail.setChangedDate(new Date());
		detail.setChangedProgram(Constants.PROGRAM_CODE);
		detail.setChangedTime(new Date());
		detail.setChangedUser(dto.getModifiedUserName());
	}
	
	public void updateDsmEnteredDetails(DealerProfileDetail detail, OrderSegmentDto dto) {
		Date date = Calendar.getInstance().getTime();
		detail.setDsmRecommendedQty(dto.getDsmQty());
		detail.setDsmReasonCode(dto.getDsmReasonCode());
		detail.setDsmComments(dto.getDsmComments());
		detail.setChangedDate(date);
		detail.setChangedProgram(Constants.PROGRAM_CODE);
		detail.setChangedTime(date);
		detail.setChangedUser(dto.getModifiedUserName());
	}

	protected Date setDate(Date date) {
		return date != null ? date : Constants.DEFAULT_DATE.getTime();
	}
	
	protected int setIntegerValue(Integer value) {
		if(value == null) return -1;
		return value; 
	}
	
	protected String setStringValue(String value) {
		if(value == null || value.length() == 0) return "";
		return value;
	}
	
}
