/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;
import com.polaris.psi.resource.dto.OrderSegmentDto;

/**
 * @author bericks
 *
 */
@Component
public class HeaderDataMapper {
	
	public DealerProfileHeader createNewNonSubmittedNonApprovedHeader(OrderSegmentDto dto, DealerProfileHeaderStatus status) {
		DealerProfileHeader header = new DealerProfileHeader();
		Date date = Calendar.getInstance().getTime();
		header.setCreatedProgram(Constants.PROGRAM_CODE);
		header.setCreatedDate(date);
		header.setCreatedTime(date);
		header.setCreatedUser(dto.getModifiedUserName());
		header.setDealerId(dto.getDealerId());
		header.setEmailAddress(setStringValue(dto.getDealerEmail()));
		header.setProfileId(dto.getProfileId());
		header.setStatus(status);
		header.setSubmittedDate(setDate(null));
		header.setSubmittedTime(setDate(null));
		header.setApprovedDate(setDate(null));
		header.setApprovedTime(setDate(null));
		header.setChangedDate(date);
		header.setChangedTime(date);
		header.setChangedProgram(Constants.PROGRAM_CODE);
		header.setChangeUser(dto.getModifiedUserName());
		
		return header;
	}
	
	public DealerProfileHeader createNewSubmittedHeader(OrderSegmentDto dto, DealerProfileHeaderStatus status, boolean nonCompliant) {
		DealerProfileHeader header = createNewNonSubmittedNonApprovedHeader(dto, status);
		Date date = Calendar.getInstance().getTime();
		header.setSubmittedDate(date);
		header.setSubmittedTime(date);
		header.setNonCompliant(nonCompliant);
		
		return header;
	}
	
	public void updateExistingSubmittedHeader(DealerProfileHeader header, DealerProfileHeaderStatus status, boolean nonCompliant) {
		Date date = Calendar.getInstance().getTime();
		header.setSubmittedDate(date);
		header.setSubmittedTime(date);
		header.setStatus(status);
		header.setNonCompliant(nonCompliant);
	}
	
	public void updateApprovedHeader(DealerProfileHeader header, DealerProfileHeaderStatus status, String user) {
		Date date = Calendar.getInstance().getTime();
		header.setApprovedDate(date);
		header.setApprovedTime(date);
		header.setChangedDate(date);
		header.setChangedProgram(Constants.PROGRAM_CODE);
		header.setChangedTime(date);
		header.setChangeUser(user);
		header.setStatus(status);
	}
	
	public void updateChangedAttributes(DealerProfileHeader header, DealerProfileHeaderStatus status, String user, boolean nonCompliant) {
		Date date = Calendar.getInstance().getTime();
		header.setChangedDate(date);
		header.setChangedProgram(Constants.PROGRAM_CODE);
		header.setChangedTime(date);
		header.setChangeUser(user);
		header.setStatus(status);
		header.setNonCompliant(nonCompliant);
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
