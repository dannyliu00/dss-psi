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

	@Path("/approveWChanges")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto approveWithChanges(ProfileDetailsDto dto) {
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

	@Path("/approveRequested")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto approveAsRequested(ProfileDetailsDto dto) {
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			ProfileDetailsDto response = new ProfileDetailsDto();
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setSuccessful(false);
			return response;
		}
		
		return service.dsmApproveAsRequested(dto);
	}

	@Path("/approveException")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto submitForException(ProfileDetailsDto dto) {
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			ProfileDetailsDto response = new ProfileDetailsDto();
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setSuccessful(false);
			return response;
		}
		
		return service.dsmSubmitForException(dto);
	}

	@Path("/toDealer")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto sendToDealer(ProfileDetailsDto dto) {
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			ProfileDetailsDto response = new ProfileDetailsDto();
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setSuccessful(false);
			return response;
		}
		
		return service.dsmSendToDealer(dto);
	}

}
