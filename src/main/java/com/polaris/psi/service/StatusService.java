/**
 * 
 */
package com.polaris.psi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public DealerProfileHeaderStatus getPendingStatus() {
		return statusDao.getPendingStatus();
	}
	
	public DealerProfileHeaderStatus getApprovedWithChangesStatus() {
		return statusDao.getApprovedWithChangesStatus();
	}
	
	public List<DealerProfileHeaderStatus> getAllStatus() {
		return statusDao.selectAll();
	}
	
}
