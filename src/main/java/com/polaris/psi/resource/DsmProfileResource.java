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
		ProfileDetailsDto response = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setSuccessful(false);
			response.setOrderSegments(dto.getOrderSegments());
			return response;
		}
		
		String userName = userData.getUserName();
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}
		
		try {
			response = service.dsmApproveWithChanges(dto, userName);
		} catch (Exception e) {
			response.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
			response.setSuccessful(false);
		}
		
		return response;
	}

	@Path("/approveRequested")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto approveAsRequested(ProfileDetailsDto dto) {
		ProfileDetailsDto response = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setSuccessful(false);
			response.setOrderSegments(dto.getOrderSegments());
			return response;
		}
		
		String userName = userData.getUserName();
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}
		
		try {
			response = service.dsmApproveAsRequested(dto, userName);
		} catch (Exception e) {
			response.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
			response.setSuccessful(false);
		}
		
		return response;
	}

	@Path("/approveException")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto submitForException(ProfileDetailsDto dto) {
		ProfileDetailsDto response = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setSuccessful(false);
			response.setOrderSegments(dto.getOrderSegments());
			return response;
		}
		
		String userName = userData.getUserName();
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}

		try {
			response = service.dsmSubmitForException(dto, userName);
		} catch (Exception e) {
			response.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
			response.setSuccessful(false);
		}
		
		return response;
	}

	@Path("/toDealer")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto sendToDealer(ProfileDetailsDto dto) {
		ProfileDetailsDto response = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setSuccessful(false);
			response.setOrderSegments(dto.getOrderSegments());
			return response;
		}
		
		String userName = userData.getUserName();
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}
		
		try {
			response = service.dsmSendToDealer(dto, userName);
		} catch (Exception e) {
			response.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
			response.setSuccessful(false);
		}
		
		return response;
	}

	@Path("/save")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto saveChanges(ProfileDetailsDto dto) {
		ProfileDetailsDto response = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isDsm()) {
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setSuccessful(false);
			response.setOrderSegments(dto.getOrderSegments());
			return response;
		}
		
		String userName = userData.getUserName();
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}
		
		try {
			response = service.dsmSaveChanges(dto, userName);
		} catch (Exception e) {
			response.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
			response.setSuccessful(false);
		}
	
		return response;
	}

}
