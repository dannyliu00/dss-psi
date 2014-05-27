package com.polaris.psi.config.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.polaris.psi.config.spring.AppSpringConfig;
import com.polaris.psi.config.spring.MvcConfig;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

public class WebAppInit implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) {

		// Create the 'root' Spring application context.
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppSpringConfig.class);

		// Manage the lifecycle of the root application context.
		servletContext.addListener(new ContextLoaderListener(rootContext));

		//Uncomment the following to have session filtering based on your DEX login
		//String[] protectedUrls = new String[] { "/webapi/*" };
		//servletContext.addFilter("sessionFilter", new DelegatingFilterProxy("sessionFilter")).addMappingForUrlPatterns(null, false, protectedUrls);

		// Register and map the dispatcher servlet.
		ServletRegistration.Dynamic jerseyDispatcherServlet = servletContext.addServlet("jerseyDispatcherServlet", SpringServlet.class);
		jerseyDispatcherServlet.setLoadOnStartup(1);
		jerseyDispatcherServlet.addMapping("/webapi/*");

		// MVC Configuration
//		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
//		dispatcherContext.register(MvcConfig.class);
//
//		ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
//		registration.setLoadOnStartup(1);
//		registration.addMapping("/*");

		rootContext.registerShutdownHook();
	}
}
