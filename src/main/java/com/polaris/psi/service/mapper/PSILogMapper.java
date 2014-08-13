/**
 * 
 */
package com.polaris.psi.service.mapper;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.PSILog;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.util.CommonUtils;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;

/**
 * PSILogMapper maps inventory profile data specific to a dealer and workflow step to an loggable PSILog object.
 * 
 * @author bericks
 *
 */
@Component
public class PSILogMapper {

	private static final SplunkLogger LOG = new SplunkLogger(PSILogMapper.class);
	
	/**
	 * Maps dealer-entered data from inventory header (DealerProfileHeader) and detail (OrderSegmentDto) 
	 * objects to a PSILog object.
	 * 
	 * @param header
	 * @param detail
	 * @return
	 */
	public PSILog mapDealerDataToLog(DealerProfileHeader header, OrderSegmentDto detail) {
		LOG.methodStart(PolarisIdentity.get(), "mapDealerDataToLog");
		
		PSILog log = new PSILog();
		
		Date date = Calendar.getInstance().getTime();
		log.setChangedDate(date);
		log.setChangedProgram(Constants.PROGRAM_CODE);
		log.setChangedTime(date);
		log.setChangedUser(header.getChangeUser());
		log.setCreatedDate(date);
		log.setCreatedProgram(Constants.PROGRAM_CODE);
		log.setCreatedTime(date);
		log.setCreatedUser(header.getChangeUser());
		
		log.setAdminChangedDate(CommonUtils.setDate(null));
		log.setAdminChangeTime(CommonUtils.setDate(null));
		log.setAdminComments(CommonUtils.setStringValue(detail.getAdminComments()));
		log.setAdminLoggedInId(CommonUtils.setStringValue(null));
		log.setAdminQty(CommonUtils.setIntegerValue(detail.getAdminQty()));
		log.setAdminReasonCode(CommonUtils.setIntegerValue(detail.getAdminReasonCode()));

		log.setDealerComments(CommonUtils.setStringValue(detail.getDealerComments()));
		log.setDealerLoggedInId(header.getChangeUser());
		log.setQuantity(detail.getActual());
		log.setDealerReasonCode(CommonUtils.setIntegerValue(detail.getReasonCode()));

		log.setDsmChangeDate(CommonUtils.setDate(null));
		log.setDsmChangeTime(CommonUtils.setDate(null));
		log.setDsmComments(CommonUtils.setStringValue(detail.getDsmComments()));
		log.setDsmLoggedInId(CommonUtils.setStringValue(null));
		log.setDsmQty(CommonUtils.setIntegerValue(detail.getDsmQty()));
		log.setDsmReasonCode(CommonUtils.setIntegerValue(detail.getDsmReasonCode()));

		log.setSubmittedDate(header.getSubmittedDate());
		log.setSubmittedTime(header.getSubmittedTime());

		log.setApprovedDate(CommonUtils.setDate(header.getApprovedDate()));
		log.setApprovedTime(CommonUtils.setDate(header.getApprovedTime()));

		log.setDetailChangeDate(date);
		log.setDetailChangeTime(date);
		log.setDetailId(detail.getId());
		log.setFinalQty(CommonUtils.setIntegerValue(detail.getFinalQty()));
		log.setHeaderId(header.getId());
		log.setLogTimestamp(date);
		log.setOrderSegment(detail.getOsCode());
		
		LOG.methodEnd(PolarisIdentity.get(), "mapDealerDataToLog");

		return log;
	}
	
	/**
	 * Maps DSM-entered data from inventory header (DealerProfileHeader) and detail (OrderSegmentDto) 
	 * objects to a PSILog object.
	 * 
	 * @param header
	 * @param detail
	 * @return
	 */
	public PSILog mapDsmDataToLog(DealerProfileHeader header, OrderSegmentDto detail) {
		LOG.methodStart(PolarisIdentity.get(), "mapDsmDataToLog");
		
		PSILog log = new PSILog();
		
		Date date = Calendar.getInstance().getTime();
		log.setChangedDate(date);
		log.setChangedProgram(Constants.PROGRAM_CODE);
		log.setChangedTime(date);
		log.setChangedUser(header.getChangeUser());
		log.setCreatedDate(date);
		log.setCreatedProgram(Constants.PROGRAM_CODE);
		log.setCreatedTime(date);
		log.setCreatedUser(header.getChangeUser());
		log.setLogTimestamp(date);
		
		log.setAdminChangedDate(CommonUtils.setDate(null));
		log.setAdminChangeTime(CommonUtils.setDate(null));
		log.setAdminComments(CommonUtils.setStringValue(detail.getAdminComments()));
		log.setAdminLoggedInId(CommonUtils.setStringValue(null));
		log.setAdminQty(CommonUtils.setIntegerValue(detail.getAdminQty()));
		log.setAdminReasonCode(CommonUtils.setIntegerValue(detail.getAdminReasonCode()));

		log.setDealerComments(CommonUtils.setStringValue(detail.getDealerComments()));
		log.setDealerLoggedInId(CommonUtils.setStringValue(null));
		log.setQuantity(detail.getActual());
		log.setDealerReasonCode(CommonUtils.setIntegerValue(detail.getReasonCode()));

		log.setDsmChangeDate(CommonUtils.setDate(null));
		log.setDsmChangeTime(CommonUtils.setDate(null));
		log.setDsmComments(CommonUtils.setStringValue(detail.getDsmComments()));
		log.setDsmLoggedInId(header.getChangeUser());
		log.setDsmQty(CommonUtils.setIntegerValue(detail.getDsmQty()));
		log.setDsmReasonCode(CommonUtils.setIntegerValue(detail.getDsmReasonCode()));

		log.setSubmittedDate(header.getSubmittedDate());
		log.setSubmittedTime(header.getSubmittedTime());

		log.setApprovedDate(CommonUtils.setDate(header.getApprovedDate()));
		log.setApprovedTime(CommonUtils.setDate(header.getApprovedTime()));

		log.setDetailChangeDate(date);
		log.setDetailChangeTime(date);
		log.setDetailId(detail.getId());
		log.setFinalQty(CommonUtils.setIntegerValue(detail.getFinalQty()));
		log.setHeaderId(header.getId());
		log.setOrderSegment(detail.getOsCode());
		
		LOG.methodEnd(PolarisIdentity.get(), "mapDsmDataToLog");

		return log;
	}
	
	/**
	 * Maps RSM-entered data from inventory header (DealerProfileHeader) and detail (OrderSegmentDto) 
	 * objects to a PSILog object.
	 * 
	 * @param header
	 * @param detail
	 * @return
	 */
	public PSILog mapRsmDataToLog(DealerProfileHeader header, OrderSegmentDto detail) {
		LOG.methodStart(PolarisIdentity.get(), "mapRsmDataToLog");

		PSILog log = new PSILog();
		
		Date date = Calendar.getInstance().getTime();
		log.setChangedDate(date);
		log.setChangedProgram(Constants.PROGRAM_CODE);
		log.setChangedTime(date);
		log.setChangedUser(header.getChangeUser());
		log.setCreatedDate(date);
		log.setCreatedProgram(Constants.PROGRAM_CODE);
		log.setCreatedTime(date);
		log.setCreatedUser(header.getChangeUser());
		log.setLogTimestamp(date);
		
		log.setAdminChangedDate(CommonUtils.setDate(null));
		log.setAdminChangeTime(CommonUtils.setDate(null));
		log.setAdminComments(CommonUtils.setStringValue(detail.getAdminComments()));
		log.setAdminLoggedInId(header.getChangeUser());
		log.setAdminQty(CommonUtils.setIntegerValue(detail.getAdminQty()));
		log.setAdminReasonCode(CommonUtils.setIntegerValue(detail.getAdminReasonCode()));

		log.setDealerComments(CommonUtils.setStringValue(detail.getDealerComments()));
		log.setDealerLoggedInId(CommonUtils.setStringValue(null));
		log.setQuantity(detail.getActual());
		log.setDealerReasonCode(CommonUtils.setIntegerValue(detail.getReasonCode()));

		log.setDsmChangeDate(CommonUtils.setDate(null));
		log.setDsmChangeTime(CommonUtils.setDate(null));
		log.setDsmComments(CommonUtils.setStringValue(detail.getDsmComments()));
		log.setDsmLoggedInId(CommonUtils.setStringValue(null));
		log.setDsmQty(CommonUtils.setIntegerValue(detail.getDsmQty()));
		log.setDsmReasonCode(CommonUtils.setIntegerValue(detail.getDsmReasonCode()));

		log.setSubmittedDate(header.getSubmittedDate());
		log.setSubmittedTime(header.getSubmittedTime());

		log.setApprovedDate(CommonUtils.setDate(header.getApprovedDate()));
		log.setApprovedTime(CommonUtils.setDate(header.getApprovedTime()));

		log.setDetailChangeDate(date);
		log.setDetailChangeTime(date);
		log.setDetailId(detail.getId());
		log.setFinalQty(CommonUtils.setIntegerValue(detail.getFinalQty()));
		log.setHeaderId(header.getId());
		log.setOrderSegment(detail.getOsCode());
		
		LOG.methodEnd(PolarisIdentity.get(), "mapRsmDataToLog");
		
		return log;
	}
	
}
