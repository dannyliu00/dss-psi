package com.polaris.app.config.spring;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.sourceforge.jtds.jdbcx.JtdsDataSource;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.polaris.app.config.spring.TestAppSpringConfig;
import com.polaris.pwf.config.spring.PwfSpringConfig;
import com.polaris.pwf.config.spring.datasource.H2ExampleDataSource;
import com.polaris.pwf.config.spring.datasource.PolarisDealersCommonDataSource;
import com.polaris.pwf.config.spring.datasource.PolarisDealersDataSource;

@Configuration
@ComponentScan(basePackages = {"com.polaris.psi"})
@EnableTransactionManagement(proxyTargetClass = true)
@Import({PwfSpringConfig.class, PolarisDealersDataSource.class, H2ExampleDataSource.class, PolarisDealersCommonDataSource.class})
@EnableCaching
public class TestAppSpringConfig {
	
	private static final Logger logger = Logger.getLogger(TestAppSpringConfig.class);
	
	@Bean(name = PolarisDealersCommonDataSource.DATASOURCE_BEAN_NAME)
	public DataSource polarisDealersCommonDataSource() {
		  JtdsDataSource dataSource = new JtdsDataSource();
		  dataSource.setServerName("mpl1devsql033.polarisdev.com");
		  dataSource.setUser("PolarisWebExtn");
		  dataSource.setPassword("G7v2x3i1");
		  dataSource.setPortNumber(1433);
		  dataSource.setDatabaseName("PolarisDealersCommon");
		  dataSource.setAppName("ExampleApp");	  
		  return dataSource;
	}
	
	@Bean(name = PolarisDealersDataSource.DATASOURCE_BEAN_NAME)
	public DataSource polarisDealersDataSource() {
		  JtdsDataSource dataSource = new JtdsDataSource();
		  dataSource.setServerName("mpl1devsql003.polarisind.com");
		  dataSource.setUser("PolarisWeb");
		  dataSource.setPassword("webguest");
		  dataSource.setPortNumber(1433);
		  dataSource.setDatabaseName("PolarisDealers");
		  dataSource.setAppName("ExampleApp");	  
		  return dataSource;
	}
	
	@Bean(name = H2ExampleDataSource.DATASOURCE_BEAN_NAME)
	public DataSource h2ExampleDataSource() {
	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	    builder.setType(EmbeddedDatabaseType.H2);
	    
	    for(String script : getDatabaseScripts()) {
	    	builder.addScript(script);
	    }
	    
	    return builder.build();
	}
	
	private List<String> getDatabaseScripts() {
		List<String> scriptList = new ArrayList<String>();
		scriptList.add("/ddl.sql");
		scriptList.add("/dml.sql");
		return scriptList;
	}

}
