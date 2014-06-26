/**
 * 
 */
package com.polaris.psi.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.Constants;
import com.polaris.psi.resource.dto.OrderSegmentDto;
import com.polaris.pwf.session.SessionHelper;

/**
 * @author bericks
 *
 */
@Component
@Path("/details")
public class ProfileDetailsResource {
	
	@Autowired
	SessionHelper sessionHelper;
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String saveQuantities(List<OrderSegmentDto> records) {
		if(records.size() == 0) return Constants.NO_RECORDS;
		
		int dealerId = records.get(0).getDealerId();
		if(dealerId != sessionHelper.getUserData().getDealerId())
			return Constants.NOT_AUTHORIZED;
		
		return Constants.SAVE_SUCCESSFUL;
	}
	
}
