package com.sshmanager.ssh.main.dto;

public class TotalPriceDTO {
	
	private String unit_price;
	private String supply_price;
	private String tax_price;
	private String total_price;
	
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
		return "TotalPriceDTO [unit_price=" + unit_price + ", supply_price=" + supply_price + ", tax_price=" + tax_price
				+ ", total_price=" + total_price + "]";
	}
}
