package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleMinSalleSummaryDTO {

	private String name;
	private Double amount;

	public SaleMinSalleSummaryDTO(String name, Double amount) {
		this.name = name;
		this.amount = amount;
	}

	public SaleMinSalleSummaryDTO(Sale entity) {

		this.name = entity.getSeller().getName();
		this.amount = entity.getAmount();
	}

	public String getName() {
		return name;
	}

	public Double getAmount() {
		return amount;
	}

}