package com.polaris.psi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polaris.psi.util.PolarisIdentity;
import com.polaris.psi.util.SplunkLogger;
import com.polaris.pwf.session.SessionHelper;
import com.polaris.pwf.webservice.client.MenuDataWebServiceClient;

/**
 * @author pceder
 *
 */
@Component
@Path("/menu")
@Produces(MediaType.APPLICATION_JSON)
public class MenuProxyResource {
	
	@Autowired
	SessionHelper sessionHelper;	
	
	@Autowired
	MenuDataWebServiceClient menuDataWebServiceClient;
	
	private static final SplunkLogger LOG = new SplunkLogger(MenuProxyResource.class);
	
	@GET
    @Path("/{menulinks}")
    @Produces(MediaType.APPLICATION_JSON)
	public String getMenu() {
		
		try {
			LOG.methodStart(PolarisIdentity.get(),"getMenu");
			
			String dealerId = String.valueOf(SessionHelper.getUserDataForJsp().getDealerId());
			
			String region = SessionHelper.getUserDataForJsp().getSessionDetail().get("Region");
			String language = SessionHelper.getUserDataForJsp().getSessionDetail().get("DealerLanguage");
			String salesman = SessionHelper.getUserDataForJsp().getSessionDetail().get("Salesman");
			String userRole = SessionHelper.getUserDataForJsp().getSessionDetail().get("UserRole");
			String empId = SessionHelper.getUserDataForJsp().getSessionDetail().get("empId");
			
			String data = menuDataWebServiceClient.getMenuData(dealerId, language, region, salesman, empId, userRole);
			
			LOG.methodEnd(PolarisIdentity.get(),"getMenu");
			
			return data;
		} catch (Exception e) {
			LOG.error(PolarisIdentity.get(), "getMenu", e);
			throw e;
		}
	}
}
