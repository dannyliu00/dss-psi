/**
 * 
 */
package com.polaris.psi.resource;

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

/**
 * @author bericks
 *
 */
@Component
@Path("/profiles")
public class ProfilesResource {

	@Autowired
	ProfileService service;
	
	@GET
    @Path("/{dealerId}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<ProfileDto> getProfiles(@PathParam("dealerId") int dealerId) {
		
		return service.getDealerProfiles(dealerId);
	}
	
}
