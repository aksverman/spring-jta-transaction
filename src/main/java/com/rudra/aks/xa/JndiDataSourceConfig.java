package com.rudra.aks.xa;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
public class JndiDataSourceConfig {/*

	@Value("${jdbc.driverClassName}")
	private	String 	driverClassName;
	
	@Value("${jdbc.connectionUrl}")
	private String url;
	
	@Value("${jdbc.jndi-dataSourceName}")
	private String jndiDataSourceName;
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
	        return new TomcatEmbeddedServletContainerFactory() {

	            @Override
	            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(
	                    Tomcat tomcat) {
	                tomcat.enableNaming();
	                tomcat.setPort(8065);
	                return super.getTomcatEmbeddedServletContainer(tomcat);
	            }

	            @Override
	            protected void postProcessContext(Context context) {
	                ContextResource resource = new ContextResource();
	                resource.setName(jndiDataSourceName);
	                resource.setType(DataSource.class.getName());
	                resource.setProperty("driverClassName", driverClassName);
	                resource.setProperty("url", url);
	                resource.setProperty("username", "devuser");
	                resource.setProperty("password", "leo$123");
	                resource.setProperty("maxActive", "10");
	                resource.setProperty("maxIdle", "5");
	                resource.setProperty("minIdle", "2");
	                resource.setProperty("maxWait", "10000");
	                context.getNamingResources().addResource(resource);
	            }
	        };
	}

	@Bean(destroyMethod = "")
    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:comp/env/" + jndiDataSourceName);
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }
	
	@Bean
	public DataSource customDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://10.98.8.100:3306/security_dev?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		dataSource.setUsername( "devuser" );
		dataSource.setPassword( "leo$123" );
		return dataSource;
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		dataSource.setUsername( "root" );
		dataSource.setPassword( "root" );
		return dataSource;
	}
	
	
*/}
