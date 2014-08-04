/**
 * 
 */
package com.polaris.psi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.Constants;
import com.polaris.psi.repository.dao.DealerProfileHeaderStatusDao;
import com.polaris.psi.repository.entity.DealerProfileHeaderStatus;

/**
 * @author bericks
 *
 */
@Service
public class StatusService {
	
	@Autowired
	DealerProfileHeaderStatusDao statusDao;
	


	public DealerProfileHeaderStatus getInProgressStatus() {
		return statusDao.getStatus(Constants.IN_PROGRESS_STATUS);
	}
	
	public DealerProfileHeaderStatus getPendingStatus() {
		return statusDao.getStatus(Constants.PENDING_STATUS);
	}
	
	public DealerProfileHeaderStatus getApprovedWithChangesStatus() {
		return statusDao.getStatus(Constants.APPROVED_W_CHANGES);
	}
	
	public DealerProfileHeaderStatus getApprovedAsRequestedStatus() {
		return statusDao.getStatus(Constants.APPROVED_AS_REQUESTED);
	}
	
	public DealerProfileHeaderStatus getExceptionRequestedStatus() {
		return statusDao.getStatus(Constants.EXCEPTION_REQUESTED);
	}
	
	public DealerProfileHeaderStatus getSendToDealerStatus() {
		return statusDao.getStatus(Constants.RETURNED_TO_DEALER);
	}
	
	public DealerProfileHeaderStatus getSendToDsmStatus() {
		return statusDao.getStatus(Constants.RETURNED_TO_DSM);
	}
	
	public DealerProfileHeaderStatus getApproveAsCompliantStatus() {
		return statusDao.getStatus(Constants.APPROVED_COMPLIANT);
	}
	
	public DealerProfileHeaderStatus getApproveAsNonCompliantStatus() {
		return statusDao.getStatus(Constants.APPROVED_NONCOMPLIANT);
	}
	
	public List<DealerProfileHeaderStatus> getAllStatus() {
		return statusDao.selectAll();
	}
	
}
