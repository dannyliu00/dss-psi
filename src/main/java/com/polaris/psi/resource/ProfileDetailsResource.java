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
@Path("/details")
public class ProfileDetailsResource {
	
	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	OrderSegmentService service;
	
	@Path("/save")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto saveQuantities(List<OrderSegmentDto> records) {
		ProfileDetailsDto response = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		if(records.size() == 0) {
			response.setSuccessful(false);
			response.setMessage(Constants.NO_RECORDS);
			response.setOrderSegments(records);
			return response;
		}
		
		int dealerId = records.get(0).getDealerId();
		int expectedDealerId = userData.getDealerId();
		if(dealerId != expectedDealerId) {
			response.setSuccessful(false);
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setOrderSegments(records);
			return response;
		}
		
		setModifiedUserName(records, userData.getUserName());
		
		List<OrderSegmentDto> orderSegments = service.saveOrderSegmentQuantities(records);
		response.setSuccessful(true);
		response.setMessage(Constants.SAVE_SUCCESSFUL);
		response.setOrderSegments(orderSegments);
		
		return response;
	}
	
	@Path("/submit")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsDto submitQuantities(List<OrderSegmentDto> records) {
		ProfileDetailsDto response = new ProfileDetailsDto();
		UserData userData = sessionHelper.getUserData();
		
		if(records.size() == 0) {
			response.setSuccessful(false);
			response.setMessage(Constants.NO_RECORDS);
			response.setOrderSegments(records);
			return response;
		}
		
		int dealerId = records.get(0).getDealerId();
		int expectedDealerId = userData.getDealerId();
		if(dealerId != expectedDealerId) {
			response.setSuccessful(false);
			response.setMessage(Constants.NOT_AUTHORIZED);
			response.setOrderSegments(records);
			return response;
		}
		
		setModifiedUserName(records, userData.getUserName());
		
		List<OrderSegmentDto> orderSegments = service.submitOrderSegmentQuantities(records);
		response.setSuccessful(true);
		response.setMessage(Constants.SAVE_SUCCESSFUL);
		response.setOrderSegments(orderSegments);
		
		return response;
	}
	
	protected void setModifiedUserName(List<OrderSegmentDto> dtos, String userName) {
		for (OrderSegmentDto dto : dtos) {
			dto.setModifiedUserName(userName);
		}
	}

}
