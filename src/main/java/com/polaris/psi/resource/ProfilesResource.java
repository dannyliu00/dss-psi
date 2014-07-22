/**
 * 
 */
package com.polaris.psi.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.resource.dto.ProfileDto;
import com.polaris.psi.service.ProfileService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

/**
 * @author bericks
 *
 */
@Component
@Path("/profiles")
public class ProfilesResource {

	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	ProfileService service;
	
	@GET
    @Path("/{dealerId}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<ProfileDto> getDealerProfiles(@PathParam("dealerId") int dealerId) {
		UserData userData = sessionHelper.getUserData();
		if(!userData.isDealer() || userData.getDealerId() != dealerId) {
			return new ArrayList<ProfileDto>();
		}
		
		return service.getCurrentDealerProfiles(dealerId);
	}
	
	@GET
    @Path("/{dealerId}/current")
    @Produces(MediaType.APPLICATION_JSON)
	public List<ProfileDto> getCurrentDealerProfiles(@PathParam("dealerId") int dealerId) {
		UserData userData = sessionHelper.getUserData();
		if(!userData.isDealer() || userData.getDealerId() != dealerId) {
			return new ArrayList<ProfileDto>();
		}
		
		return service.getCurrentDealerProfiles(dealerId);
	}
	
	@GET
    @Path("/{dealerId}/history")
    @Produces(MediaType.APPLICATION_JSON)
	public List<ProfileDto> getHistoricalDealerProfiles(@PathParam("dealerId") int dealerId) {
		UserData userData = sessionHelper.getUserData();
		if(!userData.isDealer() || userData.getDealerId() != dealerId) {
			return new ArrayList<ProfileDto>();
		}
		
		return service.getHistoricalDealerProfiles(dealerId);
	}
	
}
