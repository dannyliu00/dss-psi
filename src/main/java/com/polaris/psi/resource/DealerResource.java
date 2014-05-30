/**
 * Copyright Polaris 2014
 */
package com.polaris.psi.resource;

import groovy.util.logging.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.polaris.psi.resource.dto.DealerDTO;

/**
 * @author bericks
 *
 */
@Slf4j
@Component
@Path("/dealers")
public class DealerResource {

	@GET
    @Path("/{dealerId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public DealerDTO getDealer(@PathParam("dealerId") int dealerId) {
		
		return buildDealer();
	}
	
	private DealerDTO buildDealer() {
		DealerDTO dealer = new DealerDTO();
		dealer.setCity("MADISON");
		dealer.setCompany(null);
		dealer.setDealerId(2021900);
		dealer.setName("ENGELHART MOTORSPORTS");
		dealer.setState("WI");
		dealer.setZip("53713");
		
		return dealer;
	}
	
}
