/**
 * 
 */
package com.polaris.psi.service.mapper;

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
		header.setCreatedProgram(Constants.PROGRAM_CODE);
		header.setCreatedDate(new Date());
		header.setCreatedTime(new Date());
		header.setCreatedUser(dto.getModifiedUserName());
		header.setDealerId(dto.getDealerId());
		header.setEmailAddress(dto.getDealerEmail());
		header.setProfileId(dto.getProfileId());
		header.setStatus(status);
		header.setSubmittedDate(setDate(null));
		header.setSubmittedTime(setDate(null));
		header.setApprovedDate(setDate(null));
		header.setApprovedTime(setDate(null));
		header.setChangedDate(new Date());
		header.setChangedTime(new Date());
		header.setChangedProgram(Constants.PROGRAM_CODE);
		header.setChangeUser(dto.getModifiedUserName());
		
		return header;
	}

	protected Date setDate(Date date) {
		return date != null ? date : Constants.DEFAULT_DATE.getTime();
	}
}
