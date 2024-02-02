package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinSalleSummaryDTO;
import com.devsuperior.dsmeta.dto.SaleMinSallerDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinSallerDTO> getReport(Pageable page, LocalDate dateMin, LocalDate dateMax, String name) {

		if (dateMax == null) {
			dateMax = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			Page<SaleMinSallerDTO> list = repository.search(page, dateMin, dateMax, name);
			return list;
		}
		if (dateMin == null) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			dateMin = today.minusYears(1L);
			Page<SaleMinSallerDTO> list = repository.search(page, dateMin, dateMax, name);
			return list;
		}
		Page<SaleMinSallerDTO> list = repository.search(page, dateMin, dateMax, name);
		return list;

	}

	public Page<SaleMinSalleSummaryDTO> getSumary(Pageable page, LocalDate dateMin, LocalDate dateMax) {

		Page<SaleMinSalleSummaryDTO> list = repository.searchSumary(page, dateMin, dateMax);

		return list;
	}
}
