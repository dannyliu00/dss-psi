/**
 * 
 */
package com.polaris.psi.resource;

import groovy.util.logging.Slf4j;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.polaris.psi.resource.dto.InventoryProfileDTO;

/**
 * @author bericks
 *
 */
@Slf4j
@Component
@Path("/profiles")
public class ProfilesResource {

	@GET
    @Path("/{dealerId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<InventoryProfileDTO> getProfiles(@PathParam("dealerId") int dealerId) {
		
		return buildProfiles(dealerId);
	}
	
	private List<InventoryProfileDTO> buildProfiles(int dealerId) {
		List<InventoryProfileDTO> profiles = new ArrayList<InventoryProfileDTO>();
		InventoryProfileDTO victory = new InventoryProfileDTO();
		Calendar victoryModified = Calendar.getInstance();
		victoryModified.set(2014, 3, 30);
		victory.setModifiedDate(victoryModified.getTime());
		victory.setType("motorcycle");
		victory.setName("Victory Inventory Profile 04/30/14");
		victory.setProfileId(999);
		victory.setStatus("Not Started");
		profiles.add(victory);
		
		InventoryProfileDTO rangerXP = new InventoryProfileDTO();
		Calendar rangerModified = Calendar.getInstance();
		rangerModified.set(2014, 3, 30);
		rangerXP.setModifiedDate(rangerModified.getTime());
		rangerXP.setType("atv");
		rangerXP.setName("Ranger XP Inventory Profile 04/30/14");
		rangerXP.setProfileId(998);
		rangerXP.setStatus("Not Started");
		profiles.add(rangerXP);
		
		return profiles;
	}
	
}
