package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleMinSalleSummaryDTO;
import com.devsuperior.dsmeta.dto.SaleMinSallerDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
        // Long id, Double amount, LocalDate date, String name
        @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleMinSallerDTO(obj.id, obj.amount, obj.date, obj.seller.name) "
                        + "FROM Sale obj "
                        + "WHERE obj.date BETWEEN :dateMin AND :dateMax "
                        + "AND UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name),'%')")

        Page<SaleMinSallerDTO> searchReport(Pageable page, LocalDate dateMin, LocalDate dateMax, String name);

        @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleMinSallerDTO(obj.id, obj.amount, obj.date, obj.seller.name) "
                        + "FROM Sale obj")

        Page<SaleMinSallerDTO> searchFindAllReport(Pageable page);

        @Query("SELECT NEW com.devsuperior.dsmeta.dto.SaleMinSalleSummaryDTO(obj.seller.name,SUM(obj.amount)) "
                        + "FROM Sale "
                        + "obj INNER JOIN obj.seller s GROUP BY obj.seller.name")

        Page<SaleMinSalleSummaryDTO> searchFindAllSummary(Pageable page);

        @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleMinSalleSummaryDTO(obj.seller.name, SUM(obj.amount)) "
                        + "FROM Sale obj "
                        + "WHERE obj.date BETWEEN :dateMin AND :dateMax "
                        + "GROUP BY obj.seller.name")

        Page<SaleMinSalleSummaryDTO> searchSumary(Pageable page, LocalDate dateMin, LocalDate dateMax);

}
