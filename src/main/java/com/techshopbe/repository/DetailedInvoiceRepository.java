package com.techshopbe.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techshopbe.dto.DetailedInvoiceDTO;
import com.techshopbe.entity.DetailedInvoice;

@Repository
public interface DetailedInvoiceRepository extends JpaRepository<DetailedInvoice, Integer>{

	@Query("SELECT new com.techshopbe.dto.DetailedInvoiceDTO(p.id, d.price, d.quantity, d.totalPrice, p.name, d.isReviewed, p.images, c.slug, d.price, d.reviewID) FROM Product p, DetailedInvoice d, Category c WHERE p.id = d.productID AND d.invoiceID = ?1 AND p.categoryID = c.id")
	List<DetailedInvoiceDTO> findAllByInvoiceID(String invoiceID);
	
	@Transactional
	@Modifying
    @Query("UPDATE DetailedInvoice d SET d.isReviewed = true WHERE d.id = ?1 AND d.productID = ?2")
    int updateRatingInfoByProductID(String invoiceID, String productID);
	
	DetailedInvoice findByInvoiceIDAndProductID(String invoiceID, String productID);
	

}
