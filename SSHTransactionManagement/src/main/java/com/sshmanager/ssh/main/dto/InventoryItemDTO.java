package com.sshmanager.ssh.main.dto;

public class InventoryItemDTO {
	private String item_idx;
	private String company_idx;
	private String company_name;
	private String item_code;
	private String content;
	private String unit_price;
	private String initial_quantity;
	private String current_quantity;
	
	public String getItem_idx() {
		return item_idx;
	}
	public void setItem_idx(String item_idx) {
		this.item_idx = item_idx;
	}
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
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public String getInitial_quantity() {
		return initial_quantity;
	}
	public void setInitial_quantity(String initial_quantity) {
		this.initial_quantity = initial_quantity;
	}
	public String getCurrent_quantity() {
		return current_quantity;
	}
	public void setCurrent_quantity(String current_quantity) {
		this.current_quantity = current_quantity;
	}
	@Override
	public String toString() {
		return "InventoryItemDTO [item_idx=" + item_idx + ", company_idx=" + company_idx + ", company_name="
				+ company_name + ", item_code=" + item_code + ", content=" + content + ", unit_price=" + unit_price
				+ ", initial_quantity=" + initial_quantity + ", current_quantity=" + current_quantity + "]";
	}
}
