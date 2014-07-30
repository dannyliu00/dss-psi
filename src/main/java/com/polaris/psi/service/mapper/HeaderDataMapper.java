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
import com.polaris.psi.util.CommonUtils;

/**
 * @author bericks
 *
 */
@Component
public class HeaderDataMapper {
	
	public DealerProfileHeader createNewNonSubmittedNonApprovedHeader(OrderSegmentDto dto, DealerProfileHeaderStatus status, boolean nonCompliant) {
		DealerProfileHeader header = new DealerProfileHeader();
		Date date = Calendar.getInstance().getTime();
		header.setCreatedProgram(Constants.PROGRAM_CODE);
		header.setCreatedDate(date);
		header.setCreatedTime(date);
		header.setCreatedUser(dto.getModifiedUserName());
		header.setDealerId(dto.getDealerId());
		header.setEmailAddress(CommonUtils.setStringValue(dto.getDealerEmail()));
		header.setNonCompliant(nonCompliant);
		header.setProfileId(dto.getProfileId());
		header.setStatus(status);
		header.setSubmittedDate(CommonUtils.setDate(null));
		header.setSubmittedTime(CommonUtils.setDate(null));
		header.setApprovedDate(CommonUtils.setDate(null));
		header.setApprovedTime(CommonUtils.setDate(null));
		header.setChangedDate(date);
		header.setChangedTime(date);
		header.setChangedProgram(Constants.PROGRAM_CODE);
		header.setChangeUser(dto.getModifiedUserName());
		
		return header;
	}
	
	public DealerProfileHeader createNewSubmittedHeader(OrderSegmentDto dto, DealerProfileHeaderStatus status, boolean nonCompliant) {
		DealerProfileHeader header = createNewNonSubmittedNonApprovedHeader(dto, status, nonCompliant);
		Date date = Calendar.getInstance().getTime();
		header.setSubmittedDate(date);
		header.setSubmittedTime(date);
		header.setNonCompliant(nonCompliant);
		
		return header;
	}
	
	public void updateExistingSubmittedHeader(DealerProfileHeader header, DealerProfileHeaderStatus status, String email, boolean nonCompliant) {
		Date date = Calendar.getInstance().getTime();
		header.setEmailAddress(email);
		header.setSubmittedDate(date);
		header.setSubmittedTime(date);
		header.setStatus(status);
		header.setNonCompliant(nonCompliant);
	}
	
	public void updateApprovedHeader(DealerProfileHeader header, DealerProfileHeaderStatus status, String user, boolean nonCompliant) {
		Date date = Calendar.getInstance().getTime();
		updateChangedAttributes(header, status, user, nonCompliant);
		header.setApprovedDate(date);
		header.setApprovedTime(date);
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

}
