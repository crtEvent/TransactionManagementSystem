package com.sshmanager.ssh.main.dto;

public class CompanyDTO {
	
	private String company_idx;
	private String company_name;
	private String company_reg_num;
	private String representative;
	private String address_1;
	private String address_2;
	private String tel;
	private String fax;
	private String mobile;
	private String ect;
	private String reg_date;
	private String path;
	
	public String getCompany_idx() {
		return company_idx;
	}
	public void setCompany_idx(String company_idx) {
		this.company_idx = company_idx;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_reg_num() {
		return company_reg_num;
	}
	public void setCompany_reg_num(String company_reg_num) {
		this.company_reg_num = company_reg_num;
	}
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getAddress_1() {
		return address_1;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public String getAddress_2() {
		return address_2;
	}
	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEct() {
		return ect;
	}
	public void setEct(String ect) {
		this.ect = ect;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return "CompanyDTO [company_idx=" + company_idx + ", company_name=" + company_name + ", company_reg_num="
				+ company_reg_num + ", representative=" + representative + ", address_1=" + address_1 + ", address_2="
				+ address_2 + ", tel=" + tel + ", fax=" + fax + ", mobile=" + mobile + ", ect=" + ect + ", reg_date="
				+ reg_date + ", path=" + path + "]";
	}
	
}
