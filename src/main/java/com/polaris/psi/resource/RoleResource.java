/**
 * 
 */
package com.polaris.psi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.session.UserData;

/**
 * @author bericks
 *
 */
@Component
@Path("/roles")
public class RoleResource {
	
	@Autowired
	SessionHelper sessionHelper;

	@GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public UserData getRole() {
		
		return sessionHelper.getUserData();
	}
	
}
