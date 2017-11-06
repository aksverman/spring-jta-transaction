package com.rudra.aks.xa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rudra.aks.xa.customer.dao.CustomerDAO;
import com.rudra.aks.xa.domain.CustomerBO;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO	customerDao;
	
	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public Integer save(CustomerBO customer) {
		return customerDao.save(customer);
	}

}
