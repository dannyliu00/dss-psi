/**
 * 
 */
package com.polaris.psi.resource.dto.util;

import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.resource.dto.ProfilePeriodDto;
import com.polaris.psi.resource.dto.SegmentDto;
import com.polaris.psi.resource.dto.SegmentQuantityDto;

/**
 * @author bericks
 *
 */
public class ProfilePeriodTotalsCalculator implements IProfilePeriodTotalsCalculator {

	/* (non-Javadoc)
	 * @see com.polaris.psi.resource.dto.util.IProfilePeriodTotalsCalculator#calculateProfilePeriodTotals(com.polaris.psi.resource.dto.ProfileDto)
	 */
	@Override
	public void calculateProfilePeriodTotals(ProfileDto profile) {
		
		for (int i = 0; i < profile.getPeriods().size(); i++) {
			ProfilePeriodDto period = (ProfilePeriodDto) profile.getPeriods().get(i);
			int recMinimum = 0;
			int recMaximum = 0;
			int recommended = 0;
			int actual = 0;
			
			for (int j = 0; j < profile.getSegments().size(); j++) {
				SegmentDto segment = (SegmentDto) profile.getSegments().get(j);
				
				for (int k = 0; k < segment.getOrderSegments().size(); k++) {
					OrderSegmentDto orderSegment = (OrderSegmentDto) segment.getOrderSegments().get(k);

//					for (int l = 0; l < orderSegment.getQuantities().size(); l++) {
//						if(l == i) {
//							SegmentQuantityDto qty = (SegmentQuantityDto) orderSegment.getQuantities().get(i);
//							recMinimum += qty.getRecMinimum();
//							recMaximum += qty.getRecMaximum();
//							recommended += qty.getRecommended();
//							actual += qty.getActual();
//						}
//					}
				}
			}
			period.setActual(actual);
			period.setRecMaximum(recMaximum);
			period.setRecMinimum(recMinimum);
			period.setRecommended(recommended);
		}
		
	}

}
