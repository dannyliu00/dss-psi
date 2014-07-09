/**
 * 
 */
package com.polaris.psi.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.service.OrderSegmentService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

/**
 * @author bericks
 *
 */
@Component
@Path("/dsm/profile")
public class DsmProfileResource {
	
	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	OrderSegmentService service;

	@Path("{:profileId}/toDealer")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto sendToDealer(@PathParam("profileId") int profileId, List<OrderSegmentDto> records) {
		
		return null;
	}

	@Path("{:profileId}/approveWChanges")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto approveWithChanges(@PathParam("profileId") int profileId, ProfileDetailsDto dto) {
		ProfileDetailsDto returnDto = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			returnDto.setMessage(Constants.NOT_AUTHORIZED);
			returnDto.setSuccessful(false);
			return returnDto;
		}
		
		List<OrderSegmentDto> records = dto.getOrderSegments();
		if(records.size() == 0) {
			returnDto.setMessage(Constants.NO_RECORDS);
			returnDto.setSuccessful(false);
			return returnDto;
		}
		
		returnDto.setMessage(Constants.SAVE_SUCCESSFUL);
		returnDto.setSuccessful(true);
		returnDto.setOrderSegments(service.dsmApproveWithChanges(records));
		return returnDto;
	}

	@Path("{:profileId}/approveRequested")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto approveAsRequested(@PathParam("profileId") int profileId, List<OrderSegmentDto> records) {
		
		return null;
	}

	@Path("{:profileId}/approveException")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto submitForException(@PathParam("profileId") int profileId, List<OrderSegmentDto> records) {
		
		return null;
	}

}
