/**
 * Copyright Polaris 2014
 */
package com.polaris.psi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.resource.dto.DealerDto;
import com.polaris.psi.service.DealerService;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

/**
 * @author bericks
 *
 */
@Component
@Path("/dealer")
public class DealerResource {
	
	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	DealerService service;

	@GET
    @Path("/{dealerId}/{type}")
    @Produces(MediaType.APPLICATION_JSON)
	public DealerDto getDealerInfo(@PathParam("dealerId") int dealerId, @PathParam("type") String type) {
		UserData userData = sessionHelper.getUserData();
		
		// if logged in user is a dealer use dealerId in the session rather than what was passed in
		if(userData.isDealer()) return service.getDealer(userData.getDealerId(), type);
		
		// user is non-dealer (DSM or RSM likely) so retrieve the dealer requested
		return service.getDealer(dealerId, type);
	}
	
	@GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
	public DealerDto getDealerInfo(@PathParam("type") String type) {
		UserData userData = sessionHelper.getUserData();
		
		// if logged in user is a dealer use dealerId in the session rather than what was passed in
		return service.getDealer(userData.getDealerId(), type);
	}
}
