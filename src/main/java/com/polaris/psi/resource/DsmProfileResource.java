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
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

/**
 * @author bericks
 *
 */
@Component
@Path("/dsm/profile")
public class DsmProfileResource {
	
	private static final SplunkLogger LOG = new SplunkLogger(DsmProfileResource.class);

	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	OrderSegmentService service;

	@Path("/approveWChanges")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto approveWithChanges(ProfileDetailsDto dto) {
		LOG.methodStart(PolarisIdentity.get(), "approveWithChanges");
		
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
			LOG.error(PolarisIdentity.get(), "approveWithChanges", e);
			response.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
			response.setSuccessful(false);
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "approveWithChanges");

		return response;
	}

	@Path("/approveRequested")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto approveAsRequested(ProfileDetailsDto dto) {
		LOG.methodStart(PolarisIdentity.get(), "approveAsRequested");

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
			LOG.error(PolarisIdentity.get(), "approveAsRequested", e);
			response.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
			response.setSuccessful(false);
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "approveAsRequested");

		return response;
	}

	@Path("/approveException")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto submitForException(ProfileDetailsDto dto) {
		LOG.methodStart(PolarisIdentity.get(), "submitForException");

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
			LOG.error(PolarisIdentity.get(), "submitForException", e);
			response.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
			response.setSuccessful(false);
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "submitForException");

		return response;
	}

	@Path("/toDealer")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto sendToDealer(ProfileDetailsDto dto) {
		LOG.methodStart(PolarisIdentity.get(), "sendToDealer");

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
			LOG.error(PolarisIdentity.get(), "sendToDealer", e);
			response.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
			response.setSuccessful(false);
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "sendToDealer");

		return response;
	}

	@Path("/save")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto saveChanges(ProfileDetailsDto dto) {
		LOG.methodStart(PolarisIdentity.get(), "saveChanges");

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
			LOG.error(PolarisIdentity.get(), "saveChanges", e);
			response.setMessage(Constants.COULD_NOT_UPDATE_DSM_VALUES);
			response.setSuccessful(false);
		}
	
		LOG.methodEnd(PolarisIdentity.get(), "saveChanges");

		return response;
	}

}
