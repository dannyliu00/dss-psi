/**
 * 
 */
package com.polaris.psi.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.exception.UnknownHeaderException;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.OrderSegmentService;
import com.polaris.psi.service.ProfileService;
import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

/**
 * @author bericks
 *
 */
@Component
@Path("/profile")
public class ProfileResource {
	
	private static final SplunkLogger LOG = new SplunkLogger(ProfileResource.class);

	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	ProfileService service;
	
	@Autowired
	OrderSegmentService osService;
	
	@GET
    @Path("/nonDealer/{profileId}/{dealerId}")
    @Produces(MediaType.APPLICATION_JSON)
	public ProfileDto getProfile(@PathParam("profileId") int profileId, @PathParam("dealerId") int dealerId) {
		
		LOG.methodStart(PolarisIdentity.get(), "getProfile");

		UserData userData = sessionHelper.getUserData();
		
		LOG.methodEnd(PolarisIdentity.get(), "getProfile");

		if(userData.isDealer()) {
			return service.getDealerProfile(profileId, userData.getDealerId(), true);
		} else {
			return service.getDealerProfile(profileId, dealerId, false);
		}
	}
	
	@GET
    @Path("/{profileId}")
    @Produces(MediaType.APPLICATION_JSON)
	public ProfileDto getProfile(@PathParam("profileId") int profileId) {

		LOG.methodStart(PolarisIdentity.get(), "getProfile");

		UserData userData = sessionHelper.getUserData();
		
		LOG.methodEnd(PolarisIdentity.get(), "getProfile");

		return service.getDealerProfile(profileId, userData.getDealerId(), true);
	}

	@Path("/save")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto saveQuantities(ProfileDetailsDto dto) {
		LOG.methodStart(PolarisIdentity.get(), "saveQuantities");

		ProfileDetailsDto response = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		List<OrderSegmentDto> records = dto.getOrderSegments();
		if(records.size() == 0) {
			response.setSuccessful(false);
			response.setMessage(Constants.NO_RECORDS);
			response.setOrderSegments(records);
			return response;
		}
		
		if(!isCorrectDealer(userData, dto)) {
			response.setSuccessful(false);
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setOrderSegments(records);
			return response;
		}
		
		setModifiedUserName(records, userData.getUserName());
		
		try {
			response = osService.saveOrderSegmentQuantities(dto);
		} catch (UnknownHeaderException uhe) {
			LOG.error(PolarisIdentity.get(), "saveQuantities", uhe);
			response.setSuccessful(false);
			response.setMessage(Constants.PROFILE_STATUS_CHANGED);
			response.setOrderSegments(records);
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "saveQuantities", e);
			response.setSuccessful(false);
			response.setMessage(Constants.COULD_NOT_UPDATE_DLR_VALUES);
			response.setOrderSegments(records);
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "saveQuantities");
		
		return response;
	}
	
	@Path("/submit")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto submitQuantities(ProfileDetailsDto dto) {
		LOG.methodStart(PolarisIdentity.get(), "submitQuantities");

		ProfileDetailsDto response = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		List<OrderSegmentDto> records = dto.getOrderSegments();
		if(records.size() == 0) {
			response.setSuccessful(false);
			response.setMessage(Constants.NO_RECORDS);
			response.setOrderSegments(records);
			return response;
		}
		
		if(!isCorrectDealer(userData, dto)) {
			response.setSuccessful(false);
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setOrderSegments(records);
			return response;
		}
		
		setModifiedUserName(records, userData.getUserName());
		
		try {
			response = osService.submitOrderSegmentQuantities(dto);
		} catch (UnknownHeaderException uhe) {
			LOG.error(PolarisIdentity.get(), "submitQuantities", uhe);
			response.setSuccessful(false);
			response.setMessage(Constants.PROFILE_STATUS_CHANGED);
			response.setOrderSegments(records);
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "submitQuantities", e);
			response.setSuccessful(false);
			response.setMessage(Constants.COULD_NOT_UPDATE_DLR_VALUES);
			response.setOrderSegments(records);
		}
		
		LOG.methodEnd(PolarisIdentity.get(), "submitQuantities");

		return response;
	}
	
	protected void setModifiedUserName(List<OrderSegmentDto> dtos, String userName) {
		if(userName.length() > 10) {
			userName = userName.substring(0, 10);
		}

		for (OrderSegmentDto dto : dtos) {
			dto.setModifiedUserName(userName);
		}
	}
	
	protected boolean isCorrectDealer(UserData userData, ProfileDetailsDto dto) {
		boolean isCorrectDealer = true;
		
		if(!userData.isDealer()) {
			isCorrectDealer = false;
		}
		
		List<OrderSegmentDto> orderSegments = dto.getOrderSegments();
		int expectedDealerId = userData.getDealerId();
		for (OrderSegmentDto orderSegment : orderSegments) {
			if(expectedDealerId != orderSegment.getDealerId()) {
				isCorrectDealer = false;
			}
		}

		return isCorrectDealer;
	}

}
