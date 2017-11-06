package com.rudra.aks.xa.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.cj.api.jdbc.Statement;
import com.rudra.aks.xa.domain.CustomerBO;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	@Qualifier("xaLocalJdbc")
	JdbcTemplate	xaLocalJdbc;
	
	@Autowired
	@Qualifier("xaCustomJdbc")
	JdbcTemplate	xaCustomJdbc;
	
		
	private static final Logger logger = Logger.getLogger(CustomerDAOImpl.class);
	private static final String INSERT_QUERY = "INSERT INTO CUST_XA(CUSTOMERNAME, EMAILID, ADDRESS) VALUES(?,?,?)";
	
	@PostConstruct
	public void init() {}
	
	/**
	 * 
	 */
	public int save(final CustomerBO customer) {
		
		//saving two both the db 
		int customerid1 = 0;
		/*try {*/
			KeyHolder keyHolder1 = new GeneratedKeyHolder();
			xaLocalJdbc.update(new PreparedStatementCreator(){

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, customer.getCustomerName());
					pstmt.setString(2, customer.getEmailid());
					pstmt.setString(3, customer.getAddress());
					
					return pstmt;
				}
			}, keyHolder1);
			customerid1 = keyHolder1.getKey().intValue();
			
			//saving to different db using customjdbc
			KeyHolder keyHolder2 = new GeneratedKeyHolder();
			xaCustomJdbc.update(new PreparedStatementCreator(){

				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, customer.getCustomerName());
					pstmt.setString(2, customer.getEmailid());
					pstmt.setString(3, customer.getAddress());
					
					return pstmt;
				}
			}, keyHolder2);
			int customerid2 = keyHolder2.getKey().intValue();
			logger.info("Generated id : " + customerid1 + " and " + customerid2);
			if(customerid1>0)
				throw new RuntimeException("custome");
		/*} catch (InvalidDataAccessApiUsageException e) {
			logger.error("Exception : " + e.getMessage());
		} catch (DataAccessException e) {
			logger.error("Exceptionv : " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return customerid1;
	}
	
	
	public void delete(CustomerBO customer) {
		
	}
	
	
	public List<CustomerBO> search(String columnName, String searchText) {
		return null;}


}


