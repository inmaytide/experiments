/**
 * 
 */
package com.inmaytide.webapp.model;

import java.io.Serializable;

/**
 * @author inmaytide
 *
 */
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2869670399500818479L;
	
	private Integer id;
	private String name;
	private String contact;
	private String telephone;
	private String email;
	private String remark;
	
	public Customer() {
	
	}

	public Customer(Integer id, String name, String contact, String telephone, String email, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.telephone = telephone;
		this.email = email;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
