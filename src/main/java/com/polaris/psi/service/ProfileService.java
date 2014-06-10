/**
 * 
 */
package com.polaris.psi.service;

import java.util.LinkedList;
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
public class ProfileService {

	@Autowired
	DealerProfileHeaderDao headerDao;
	
	public List<Profile> getDealerProfiles(int dealerId) {
		List<DealerProfileHeader> profileHeaders = headerDao.getDealerHeaders(dealerId);
		List<Profile> profiles = new LinkedList<Profile>();
		
		for (DealerProfileHeader header : profileHeaders) {
			profiles.add(header.getProfile());
		}
		
		return profiles;
	}
}
