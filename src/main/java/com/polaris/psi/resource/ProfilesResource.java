/**
 * 
 */
package com.polaris.psi.resource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.polaris.psi.model.InventoryProfile;

/**
 * @author bericks
 *
 */
@Path("/profiles")
public class ProfilesResource {

	@GET
    @Path("/{dealerId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<InventoryProfile> getProfiles(@PathParam("dealerId") int dealerId) {
		
		return buildProfiles(dealerId);
	}
	
	private List<InventoryProfile> buildProfiles(int dealerId) {
		List<InventoryProfile> profiles = new ArrayList<InventoryProfile>();
		InventoryProfile victory = new InventoryProfile();
		Calendar modified = Calendar.getInstance();
		modified.set(2014, 3, 30);
		victory.setModifiedDate(modified.getTime());
		victory.setName("Victory Inventory Profile 04/30/14");
		victory.setProfileId(999);
		victory.setStatus("Not Started");
		profiles.add(victory);
		
		return profiles;
	}
	
}
