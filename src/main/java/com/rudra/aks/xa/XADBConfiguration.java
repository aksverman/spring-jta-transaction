package com.rudra.aks.xa;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
//@EnableJpaRepositories(basePackages = "com.rudra.aks.xa.repository")
@EnableTransactionManagement
public class XADBConfiguration{

	@Bean
	public PlatformTransactionManager txManager () throws Throwable {
		return new JtaTransactionManager(userTransaction(), atomikosTxManager());
		//return new DataSourceTransactionManager(jndiDataSource);
	}
	
	@Bean
	public UserTransaction userTransaction() throws SystemException {
		UserTransactionImp  userTran = new UserTransactionImp();
		userTran.setTransactionTimeout(10000);
		return userTran;
	}
	
	@Bean
	public	TransactionManager atomikosTxManager() {
		UserTransactionManager userTransactionMangager = new UserTransactionManager();
		userTransactionMangager.setForceShutdown(true);
		return userTransactionMangager;
		
	}
	
	
	@Bean
	@DependsOn("xaLocalDataSource")
	public	JdbcTemplate	xaLocalJdbc() {
		return new JdbcTemplate(xaLocalDataSource());
		
	}
	
	
	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource xaLocalDataSource() {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl("jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		//mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword("root");
		mysqlXaDataSource.setUser("root");

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName("localds");
		return xaDataSource;
	}
	
	@Bean
	@DependsOn("xaCustomDataSource")
	public	JdbcTemplate	xaCustomJdbc() {
		return new JdbcTemplate(xaCustomDataSource());
		
	}
	
	
	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource xaCustomDataSource() {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl("jdbc:mysql://10.98.8.100:3306/security_dev?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		//mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword("leo$123");
		mysqlXaDataSource.setUser("devuser");

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName("customds");
		return xaDataSource;
	}
	
	
}
