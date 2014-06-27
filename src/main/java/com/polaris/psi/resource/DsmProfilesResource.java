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
@Path("/dsm/profiles")
public class DsmProfilesResource {

	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	ProfileService service;
	
	@GET
    @Path("/{dsmId}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<ProfileDto> getDsmProfiles(@PathParam("dsmId") int dsmId) {
		UserData userData = sessionHelper.getUserData();
		if(!userData.isDsm() || userData.getDealerId() != dsmId) {
			return new ArrayList<ProfileDto>();
		}
		
		return service.getDealerProfiles(dsmId);
	}
	
}
