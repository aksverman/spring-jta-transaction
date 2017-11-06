package com.rudra.aks.xa.customer.dao;

import java.util.List;

import com.rudra.aks.xa.domain.CustomerBO;


public interface CustomerDAO {
	int save(CustomerBO customer);
	List<CustomerBO>	search(String columnName, String searchText);
	void delete(CustomerBO customer);
}
