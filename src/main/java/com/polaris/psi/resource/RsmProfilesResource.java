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
@Path("/rsm/profiles")
public class RsmProfilesResource {

	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	ProfileService service;
	
	@GET
    @Path("/{rsmId}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<ProfileDto> getRsmProfiles(@PathParam("rsmId") int rsmId) {
		UserData userData = sessionHelper.getUserData();
		if(!userData.isRsm() || userData.getDealerId() != rsmId) {
			return new ArrayList<ProfileDto>();
		}
		
		return service.getDealerProfiles(rsmId);
	}
	
}
