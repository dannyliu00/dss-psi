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
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.psi.resource.dto.ProfileDetailsDto;
import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.OrderSegmentService;
import com.polaris.psi.service.ProfileService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

/**
 * @author bericks
 *
 */
@Component
@Path("/profile")
public class ProfileResource {
	
	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	ProfileService service;
	
	@Autowired
	OrderSegmentService osService;
	
	@GET
    @Path("/{profileId}/{dealerId}")
    @Produces(MediaType.APPLICATION_JSON)
	public ProfileDto getProfile(@PathParam("profileId") int profileId, @PathParam("dealerId") int dealerId) {

		if(sessionHelper.getUserData().isDealer()) {
			return service.getDealerProfile(profileId, sessionHelper.getUserData().getDealerId());
		} else {
			return service.getDealerProfile(profileId, dealerId);
		}
	}

	@Path("/{profileId}/{dealerId}/save")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto saveQuantities(@PathParam("profileId") int profileId, @PathParam("dealerId") int dealerId, ProfileDetailsDto dto) {
		UserData userData = sessionHelper.getUserData();
		
		List<OrderSegmentDto> records = dto.getOrderSegments();
		if(records.size() == 0) {
			ProfileDetailsDto response = new ProfileDetailsDto();
			response.setSuccessful(false);
			response.setMessage(Constants.NO_RECORDS);
			response.setOrderSegments(records);
			return response;
		}
		
		int expectedDealerId = userData.getDealerId();
		if(dealerId != expectedDealerId) {
			ProfileDetailsDto response = new ProfileDetailsDto();
			response.setSuccessful(false);
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setOrderSegments(records);
			return response;
		}
		
		setModifiedUserName(records, userData.getUserName());
		
		return osService.saveOrderSegmentQuantities(dto);
	}
	
	@Path("/{profileId}/{dealerId}/submit")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto submitQuantities(@PathParam("profileId") int profileId, @PathParam("dealerId") int dealerId, ProfileDetailsDto dto) {
		ProfileDetailsDto response = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		List<OrderSegmentDto> records = dto.getOrderSegments();
		if(records.size() == 0) {
			response.setSuccessful(false);
			response.setMessage(Constants.NO_RECORDS);
			response.setOrderSegments(records);
			return response;
		}
		
		int expectedDealerId = userData.getDealerId();
		if(dealerId != expectedDealerId) {
			response.setSuccessful(false);
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setOrderSegments(records);
			return response;
		}
		
		setModifiedUserName(records, userData.getUserName());
		
		return osService.submitOrderSegmentQuantities(dto);
	}
	
	protected void setModifiedUserName(List<OrderSegmentDto> dtos, String userName) {
		for (OrderSegmentDto dto : dtos) {
			dto.setModifiedUserName(userName);
		}
	}

}
