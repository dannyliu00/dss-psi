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

import com.polaris.psi.resource.dto.DsmDealerProfilesDto;
import com.polaris.psi.service.DsmService;
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
	DsmService service;
	
	@GET
    @Path("/{dsmId}/{type}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<DsmDealerProfilesDto> getDsmProfiles(@PathParam("dsmId") int dsmId, @PathParam("type") String type) {
		UserData userData = sessionHelper.getUserData();
		if(!userData.isDsm() || userData.getDealerId() != dsmId) {
			return new ArrayList<DsmDealerProfilesDto>();
		}
		if(type.equals(null)) type = "2";
		return service.getDsmProfiles(dsmId, type);
	}
	
}
