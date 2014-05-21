package com.polaris.psi.config.spring;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.polaris.pwf.config.spring.PwfSpringConfig;
import com.polaris.pwf.config.spring.datasource.H2ExampleDataSource;
import com.polaris.pwf.config.spring.datasource.PolarisDealersCommonDataSource;
import com.polaris.pwf.config.spring.datasource.PolarisDealersDataSource;
import com.polaris.pwf.util.JndiUtil;

@Configuration
@ComponentScan(basePackages = {"com.polaris.psi"})
@EnableTransactionManagement(proxyTargetClass = true)
@Import({PwfSpringConfig.class, /*PolarisDealersDataSource.class,*/ H2ExampleDataSource.class/*, PolarisDealersCommonDataSource.class*/})
@EnableCaching
public class AppSpringConfig {

	@Bean(name = "polarisDealersAppEntityPackage")
	public String polarisDealersAppEntityPackage() {
		return "com.polaris.psi";
	}

	@Bean(name = PolarisDealersCommonDataSource.DATASOURCE_BEAN_NAME)
	public DataSource polarisDealersCommonDataSource() {
		return JndiUtil.lookupDataSource("sql/PolarisDealersCommon");
	}
	
	@Bean(name = PolarisDealersDataSource.DATASOURCE_BEAN_NAME)
	public DataSource polarisDealersDataSource() {
		return JndiUtil.lookupDataSource("sql/PolarisDealers");
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
