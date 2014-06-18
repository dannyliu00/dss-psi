/**
 * 
 */
package com.polaris.psi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polaris.psi.repository.dao.DealerProfileHeaderDao;
import com.polaris.psi.repository.entity.DealerProfileHeader;
import com.polaris.psi.repository.entity.Profile;

/**
 * @author bericks
 *
 */
@Service
public class ProfileHeaderService {

	@Autowired
	DealerProfileHeaderDao dao;
	
	public List<DealerProfileHeader> retrieveByProfile(Profile profile) {
		
		return dao.getDealerHeadersByProfile(profile);
	}
}
