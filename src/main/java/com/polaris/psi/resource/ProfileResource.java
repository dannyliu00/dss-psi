/**
 * 
 */
package com.polaris.psi.resource;

import groovy.util.logging.Slf4j;

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

/**
 * @author bericks
 *
 */
@Slf4j
@Component
@Path("/profile")
public class ProfileResource {
	
	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	ProfileService service;
	
	@GET
    @Path("/{profileId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public ProfileDto getProfile(@PathParam("profileId") int profileId, @PathParam("dealerId") int dealerId) {

		if(sessionHelper.getUserData().isDealer()) {
			return service.getDealerProfile(profileId, sessionHelper.getUserData().getDealerId());
		} else {
			return service.getDealerProfile(profileId, dealerId);
		}
	}

}
