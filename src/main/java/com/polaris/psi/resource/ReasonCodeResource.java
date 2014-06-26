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

import com.polaris.psi.resource.dto.ReasonCodeDto;
import com.polaris.psi.service.ReasonCodeService;
import com.polaris.pwf.session.SessionHelper;

/**
 * @author bericks
 *
 */
@Component
@Path("/reasons")
public class ReasonCodeResource {

	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	ReasonCodeService service;
	
	@GET
	@Path("/{roleId}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<ReasonCodeDto> getReasonCodes(@PathParam("roleId") int roleId) {
		return service.getReasonCodes(roleId);
	}
	
}
