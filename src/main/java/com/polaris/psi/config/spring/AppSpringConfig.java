package com.polaris.psi.config.spring;

import javax.sql.DataSource;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.polaris.psi.Constants;
import com.polaris.pwf.config.spring.PwfSpringConfig;
import com.polaris.pwf.config.spring.datasource.PolarisDealersCommonDataSource;
import com.polaris.pwf.config.spring.datasource.PolarisDealersDataSource;
import com.polaris.pwf.util.JndiUtil;

@Configuration
@ComponentScan(basePackages = {"com.polaris.psi"})
@EnableTransactionManagement(proxyTargetClass = true)
@Import({PwfSpringConfig.class, PolarisDealersDataSource.class, PolarisDealersCommonDataSource.class})
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

	
	@Bean
	public ConcurrentMapCache ValidValuesCache() {
		return new ConcurrentMapCache(Constants.VALID_VALUES_CACHE, true);
	}

	@Bean
	public ConcurrentMapCache validValuesMapCache() {
		return new ConcurrentMapCache(Constants.VALID_VALUES_MAP_CACHE, true);
	}

	@Bean
	public ConcurrentMapCache allValidValuesMapCache() {
		return new ConcurrentMapCache(Constants.VALID_VALUES_MAP_ALL_CACHE, true);
	}
}
