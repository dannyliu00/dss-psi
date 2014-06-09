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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.resource.dto.DealerDto;
import com.polaris.psi.service.DealerService;
import com.polaris.pwf.session.SessionHelper;

/**
 * @author bericks
 *
 */
@Slf4j
@Component
@Path("/dealers")
public class DealerResource {
	
	@Autowired
	SessionHelper sessionHelper;
	
	@Autowired
	DealerService service;

	@GET
    @Path("/{dealerId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public DealerDto getDealer(@PathParam("dealerId") int dealerId) {
		
		return service.getDealer(dealerId);
	}
	
}
