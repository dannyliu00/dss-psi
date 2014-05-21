/**
 * Copyright Polaris 2014
 */
package com.polaris.psi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.polaris.psi.model.Dealer;

/**
 * @author bericks
 *
 */
@Path("/dealers")
public class DealerResource {

	@GET
    @Path("/{dealerId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Dealer getDealer(@PathParam("dealerId") int dealerId) {
		
		return buildDealer();
	}
	
	private Dealer buildDealer() {
		Dealer dealer = new Dealer();
		dealer.setCity("MADISON");
		dealer.setCompany(null);
		dealer.setDealerId(2021900);
		dealer.setName("ENGELHART MOTORSPORTS");
		dealer.setState("WI");
		dealer.setZip("53713");
		
		return dealer;
	}
	
}
