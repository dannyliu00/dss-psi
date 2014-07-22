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
@Path("/rsm/profiles")
public class RsmProfilesResource {

	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	DsmService service;
	
	@GET
    @Path("/{rsmId}/{type}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<DsmDealerProfilesDto> getRsmProfiles(@PathParam("rsmId") int rsmId, @PathParam("type") String type) {
		UserData userData = sessionHelper.getUserData();
		if(!userData.isRsm() || userData.getDealerId() != rsmId) {
			return new ArrayList<DsmDealerProfilesDto>();
		}
		return service.getRsmProfiles(rsmId, type);
	}
	
	@GET
    @Path("/{rsmId}/{type}/current")
    @Produces(MediaType.APPLICATION_JSON)
	public List<DsmDealerProfilesDto> getRsmCurrentProfiles(@PathParam("rsmId") int rsmId, @PathParam("type") String type) {
		UserData userData = sessionHelper.getUserData();
		if(!userData.isRsm() || userData.getDealerId() != rsmId) {
			return new ArrayList<DsmDealerProfilesDto>();
		}
		return service.getRsmCurrentProfiles(rsmId, type);
	}
	
	@GET
    @Path("/{rsmId}/{type}/history")
    @Produces(MediaType.APPLICATION_JSON)
	public List<DsmDealerProfilesDto> getRsmHistoricalProfiles(@PathParam("rsmId") int rsmId, @PathParam("type") String type) {
		UserData userData = sessionHelper.getUserData();
		if(!userData.isRsm() || userData.getDealerId() != rsmId) {
			return new ArrayList<DsmDealerProfilesDto>();
		}
		return service.getRsmHistoricalProfiles(rsmId, type);
	}
	
}
