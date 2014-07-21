/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.DealerProfileDetail;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.PSILog;
import com.polaris.psi.repository.entity.PSIOrderSegment;
import com.polaris.psi.util.CommonUtils;

/**
 * @author bericks
 *
 */
@Component
public class PSILogMapper {

	public PSILog mapDealerDataToLog(DealerProfileHeader header, DealerProfileDetail detail, PSIOrderSegment os, String userName) {
		PSILog log = new PSILog();
		
		log.setAdminChangedDate(CommonUtils.setDate(null));
		log.setAdminChangeTime(CommonUtils.setDate(null));
		log.setAdminComments(detail.getAdminComments());
		log.setAdminLoggedInId(CommonUtils.setStringValue(null));
		log.setAdminQty(CommonUtils.setIntegerValue(detail.getAdminApprovedQty()));
		log.setAdminReasonCode(CommonUtils.setIntegerValue(detail.getAdminReasonCode()));
		log.setApprovedDate(CommonUtils.setDate(header.getApprovedDate()));
		log.setApprovedTime(CommonUtils.setDate(header.getApprovedTime()));

		Date date = Calendar.getInstance().getTime();
		log.setChangedDate(date);
		log.setChangedProgram(Constants.PROGRAM_CODE);
		log.setChangedTime(date);
		log.setChangedUser(userName);
		log.setCreatedDate(date);
		log.setCreatedProgram(Constants.PROGRAM_CODE);
		log.setCreatedTime(date);
		log.setCreatedUser(userName);
		
		log.setDealerComments(detail.getDealerComments());
		log.setDealerLoggedInId(userName);
		log.setDealerReasonCode(detail.getDealerReasonCode());
		log.setDetailChangeDate(CommonUtils.setDate(detail.getChangedDate()));
		log.setDetailChangeTime(CommonUtils.setDate(detail.getChangedTime()));
		log.setDetailId(detail.getId());
		log.setDsmChangeDate(CommonUtils.setDate(null));
		log.setDsmChangeTime(CommonUtils.setDate(null));
		log.setDsmComments(CommonUtils.setStringValue(null));
		log.setDsmLoggedInId(CommonUtils.setStringValue(null));
		log.setDsmQty(CommonUtils.setIntegerValue(null));
		log.setDsmReasonCode(CommonUtils.setIntegerValue(null));
		log.setFinalQty(CommonUtils.setIntegerValue(null));
		log.setHeaderId(header.getId());
		log.setLogTimestamp(date);
		log.setOrderSegment(os.getName());
		log.setQuantity(detail.getActual());
//		log.setRowNumber(rowNumber);
		log.setSubmittedDate(header.getSubmittedDate());
		log.setSubmittedTime(header.getSubmittedTime());
		
		return log;
	}
}
