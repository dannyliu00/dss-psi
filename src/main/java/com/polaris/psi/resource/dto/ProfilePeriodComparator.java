/**
 * 
 */
package com.polaris.psi.resource.dto;

import org.springframework.stereotype.Component;

/**
 * @author bericks
 *
 */
@Component
public class ProfilePeriodComparator implements IProfilePeriodComparator {

	@Override
	public int compare(ProfilePeriodDto lhs, ProfilePeriodDto rhs) {
		return lhs.getStartDate().compareTo(rhs.getStartDate());
	}

}
