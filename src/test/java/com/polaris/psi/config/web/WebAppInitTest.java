package com.polaris.psi.config.web;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

public class WebAppInitTest {

	private WebAppInit appInit;
	@Mock private ServletContext mockServletContext;
	@Mock private ServletRegistration.Dynamic mockDispatcherServlet;
	@Mock private Dynamic mockDynamic;
	private String jerseyServlet, servletMappingPath, protectedUrls;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		jerseyServlet = "jerseyDispatcherServlet";
		servletMappingPath = "/webapi/*";
		protectedUrls = "/*";
		
		when(mockServletContext.addServlet(jerseyServlet, SpringServlet.class)).thenReturn(mockDispatcherServlet);
		when(mockServletContext.addFilter(anyString(), any(DelegatingFilterProxy.class))).thenReturn(mockDynamic);
		
		appInit = new WebAppInit();
	}

	@Test
	public void testOnStartup() {
		appInit.onStartup(mockServletContext);
		
		verify(mockServletContext).addListener(any(ContextLoaderListener.class));
		verify(mockServletContext).addFilter(anyString(), any(DelegatingFilterProxy.class));
		verify(mockServletContext).addServlet(jerseyServlet, SpringServlet.class);
		verify(mockDispatcherServlet).setLoadOnStartup(1);
		verify(mockDispatcherServlet).addMapping(servletMappingPath);
		verify(mockDynamic).addMappingForUrlPatterns(null, false, protectedUrls);
	}

}
