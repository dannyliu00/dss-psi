package com.polaris.psi.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RestfulServiceHelper {
	public static void throwUnauthorizedException() throws WebApplicationException {
		throw new WebApplicationException(Response.Status.UNAUTHORIZED);
	}
}
