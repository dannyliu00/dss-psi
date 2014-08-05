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
@Path("/rsm/profile")
public class RsmProfileResource {

	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	OrderSegmentService service;

	@Path("/approveAsCompliant")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto approveAsCompliant(ProfileDetailsDto dto) {
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isRsm()) {
			dto.setMessage(Constants.NOT_AUTHORIZED);
			dto.setSuccessful(false);
			return dto;
		}
		
		return service.rsmApproveAsCompliant(dto, userData.getUserName());
	}

	@Path("/approveAsNonCompliant")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto approveAsNonCompliant(ProfileDetailsDto dto) {
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isRsm()) {
			dto.setMessage(Constants.NOT_AUTHORIZED);
			dto.setSuccessful(false);
			return dto;
		}
		
		return service.rsmApproveAsNonCompliant(dto, userData.getUserName());
	}

	@Path("/toDsm")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto sendToDsm(ProfileDetailsDto dto) {
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isRsm()) {
			dto.setMessage(Constants.NOT_AUTHORIZED);
			dto.setSuccessful(false);
			return dto;
		}
		
		return service.rsmSendToDsm(dto, userData.getUserName());
	}

	@Path("/save")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto saveChanges(ProfileDetailsDto dto) {
		UserData userData = sessionHelper.getUserData();
		
		if(!userData.isRsm()) {
			dto.setMessage(Constants.NOT_AUTHORIZED);
			dto.setSuccessful(false);
			return dto;
		}
		
		return service.rsmSaveChanges(dto, userData.getUserName());
	}

}
