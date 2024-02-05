package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

		if (dateMax == null && dateMin == null && StringUtils.isEmpty(name)) {
			System.out.println("PASSOU AQUI");
			Page<SaleMinSallerDTO> list = repository.searchFindAllReport(page);
			return list;
		}

		if (dateMax == null) {
			dateMax = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		if (dateMin == null) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			dateMin = today.minusYears(1L);
		}
		System.out.println("PASSOU AQUI " + dateMax + " " + dateMin);
		Page<SaleMinSallerDTO> list = repository.searchReport(page, dateMin, dateMax, name);
		return list;
	}

	public Page<SaleMinSalleSummaryDTO> getSumary(Pageable page, LocalDate dateMin, LocalDate dateMax) {

		if (dateMax == null && dateMin == null) {
			Page<SaleMinSalleSummaryDTO> list = repository.searchFindAllSummary(page);
			return list;
		}

		if (dateMax == null) {
			dateMax = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else if (dateMin == null) {
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			dateMin = today.minusYears(1L);
		}

		Page<SaleMinSalleSummaryDTO> list = repository.searchSumary(page, dateMin, dateMax);

		return list;
	}
}
