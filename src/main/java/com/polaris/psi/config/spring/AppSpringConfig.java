package com.polaris.psi.config.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.polaris.pwf.config.spring.PwfSpringConfig;
import com.polaris.pwf.config.spring.datasource.PolarisDealersCommonDataSource;
import com.polaris.pwf.config.spring.datasource.PolarisDealersDataSource;
import com.polaris.pwf.config.spring.datasource.PolarisDealersExtensionDataSource;
import com.polaris.pwf.config.spring.datasource.PolarisMinneapolisDataSource;
import com.polaris.pwf.util.JndiUtil;

@Configuration
@ComponentScan(basePackages = {"com.polaris.psi"})
@EnableTransactionManagement(proxyTargetClass = true)
@Import({PwfSpringConfig.class, PolarisMinneapolisDataSource.class, PolarisDealersExtensionDataSource.class, 
	PolarisDealersDataSource.class, PolarisDealersCommonDataSource.class})
public class AppSpringConfig {

	@Bean(name = "polarisDealersAppEntityPackage")
	public String polarisDealersAppEntityPackage() {
		return "com.polaris.psi.repository.entity";
	}

	@Bean(name = PolarisMinneapolisDataSource.DATASOURCE_BEAN_NAME)
	public DataSource polarisMinneapolisDataSource() {
		return JndiUtil.lookupDataSource("as400/POLDEV");
	}

	@Bean(name = PolarisDealersExtensionDataSource.DATASOURCE_BEAN_NAME)
	public DataSource polarisDealersExtensionDataSource() {
		return JndiUtil.lookupDataSource("sql/PolarisDealersExtension");
	}

	@Bean(name = PolarisDealersCommonDataSource.DATASOURCE_BEAN_NAME)
	public DataSource polarisDealersCommonDataSource() {
		return JndiUtil.lookupDataSource("sql/PolarisDealersCommon");
	}

	@Bean(name = PolarisDealersDataSource.DATASOURCE_BEAN_NAME)
	public DataSource polarisDealersDataSource() {
		return JndiUtil.lookupDataSource("sql/PolarisDealers");
	}
}
