package com.sshmanager.ssh.main.dto;

public class ItemDTO {
	
	private String item_idx;
	private String transaction_idx;
	private String content;
	private String amount;
	private String unit;
	private String unit_price;
	private String supply_price;
	private String tax_price;
	private String total_price;
	
	public String getItem_idx() {
		return item_idx;
	}
	public void setItem_idx(String item_idx) {
		this.item_idx = item_idx;
	}
	public String getTransaction_idx() {
		return transaction_idx;
	}
	public void setTransaction_idx(String transaction_idx) {
		this.transaction_idx = transaction_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public String getSupply_price() {
		return supply_price;
	}
	public void setSupply_price(String supply_price) {
		this.supply_price = supply_price;
	}
	public String getTax_price() {
		return tax_price;
	}
	public void setTax_price(String tax_price) {
		this.tax_price = tax_price;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	
	@Override
	public String toString() {
		return "ItemDTO [item_idx=" + item_idx + ", transaction_idx=" + transaction_idx + ", content=" + content
				+ ", amount=" + amount + ", unit=" + unit + ", unit_price=" + unit_price + ", supply_price="
				+ supply_price + ", tax_price=" + tax_price + ", total_price=" + total_price + "]";
	}
	
}
