package com.jdc.app.entity;

import java.util.List;

public class SaleDTO {

	private Sale sale;
	private List<SaleDetail> details;

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public List<SaleDetail> getDetails() {
		return details;
	}

	public void setDetails(List<SaleDetail> details) {
		this.details = details;
	}

}
