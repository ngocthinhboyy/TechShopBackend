package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techshopbe.dto.DetailedInvoiceDTO;
import com.techshopbe.entity.DetailedInvoice;

@Repository
public interface DetailedInvoiceRepository extends JpaRepository<DetailedInvoice, Integer>{

	@Query("SELECT new com.techshopbe.dto.DetailedInvoiceDTO(p.productID, d.price, d.quantity, d.totalPrice, p.productName) FROM Product p, DetailedInvoice d WHERE p.productID = d.productID AND d.invoiceID = ?1")
	List<DetailedInvoiceDTO> findAllByInvoiceID(int invoiceID);
	

}
