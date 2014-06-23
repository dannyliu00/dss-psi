package com.polaris.psi.config.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.polaris.psi.config.spring.AppSpringConfig;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

public class WebAppInit implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) {

		// Create the 'root' Spring application context.
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppSpringConfig.class);

		// Manage the lifecycle of the root application context.
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// SessionFilter configuration
		String[] protectedUrls = new String[] { "/*" };
		servletContext.addFilter("sessionFilter", new DelegatingFilterProxy("sessionFilter")).addMappingForUrlPatterns(null, false, protectedUrls);

		// Register and map the dispatcher servlet.
		ServletRegistration.Dynamic jerseyDispatcherServlet = servletContext.addServlet("jerseyDispatcherServlet", SpringServlet.class);
		jerseyDispatcherServlet.setLoadOnStartup(1);
		jerseyDispatcherServlet.addMapping("/webapi/*");

		rootContext.registerShutdownHook();
	}
}
