package com.rudra.aks.xa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_XA")
public class CustomerBO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int	customerid;
	
	@Column
	private String customerName;
	
	@Column
	private String	emailid;
	
	@Column
	private String address;
	
	@Override
	public String toString() {
		return "CustomerBO [customerid=" + customerid + ", customerName=" + customerName + ", emailid=" + emailid
				+ ", address=" + address + "]";
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
