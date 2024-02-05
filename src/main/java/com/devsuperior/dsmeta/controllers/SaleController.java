package com.devsuperior.dsmeta.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinSalleSummaryDTO;
import com.devsuperior.dsmeta.dto.SaleMinSallerDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinSallerDTO>> getReport(Pageable pageable,
			@RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
			@RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
			@RequestParam(defaultValue = "") String name) {

		System.out.println("Passou aqui +DATE " + maxDate);
		System.out.println("Passou aqui -DATE " + minDate);
		System.out.println("Passou aqui NAME " + name);
		return ResponseEntity.ok(service.getReport(pageable, minDate, maxDate,
				name));
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleMinSalleSummaryDTO>> getSummary(Pageable pageable,
			@RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
			@RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate) {

		return ResponseEntity.ok(service.getSumary(pageable, minDate, maxDate));
	}

}
