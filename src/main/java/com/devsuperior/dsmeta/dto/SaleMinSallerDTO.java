package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleMinSallerDTO {

	private Long id;
	private LocalDate date;
	private Double amount;
	private String name;

	public SaleMinSallerDTO(Long id, Double amount, LocalDate date, String name) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.name = name;
	}

	public SaleMinSallerDTO(Sale entity) {
		this.id = entity.getId();
		this.amount = entity.getAmount();
		this.date = entity.getDate();
		this.name = entity.getSeller().getName();
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

}