/**
 * 
 */
package com.polaris.psi.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
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
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			ProfileDetailsDto response = new ProfileDetailsDto();
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setSuccessful(false);
			return response;
		}
		
		String userName = userData.getUserName();
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}
		return service.dsmApproveWithChanges(dto, userName);
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
		
		String userName = userData.getUserName();
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}
		
		return service.dsmApproveAsRequested(dto, userName);
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
		
		String userName = userData.getUserName();
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}

		return service.dsmSubmitForException(dto, userName);
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
		
		String userName = userData.getUserName();
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}
		
		return service.dsmSendToDealer(dto, userName);
	}

	@Path("/save")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto saveChanges(ProfileDetailsDto dto) {
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			ProfileDetailsDto response = new ProfileDetailsDto();
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setSuccessful(false);
			return response;
		}
		
		String userName = userData.getUserName();
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}
		
		return service.dsmSaveChanges(dto, userName);
	}

}
