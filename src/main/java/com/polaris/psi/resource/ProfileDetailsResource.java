/**
 * 
 */
package com.polaris.psi.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.service.OrderSegmentService;
import com.polaris.pwf.session.SessionHelper;

/**
 * @author bericks
 *
 */
@Component
@Path("/details")
public class ProfileDetailsResource {
	
	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	OrderSegmentService service;
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto saveQuantities(List<OrderSegmentDto> records) {
		ProfileDetailsDto response = new ProfileDetailsDto();
		
		if(records.size() == 0) {
			response.setSuccessful(false);
			response.setMessage(Constants.NO_RECORDS);
			response.setOrderSegments(records);
			return response;
		}
		
		int dealerId = records.get(0).getDealerId();
		if(dealerId != sessionHelper.getUserData().getDealerId()) {
			response.setSuccessful(false);
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setOrderSegments(records);
			return response;
		}
		
		List<OrderSegmentDto> orderSegments = service.saveOrderSegmentQuantities(records);
		response.setSuccessful(true);
		response.setMessage(Constants.SAVE_SUCCESSFUL);
		response.setOrderSegments(orderSegments);
		
		return response;
	}
	
}
