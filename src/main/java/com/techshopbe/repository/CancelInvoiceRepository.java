package com.techshopbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techshopbe.entity.CancelInvoice;

@Repository
public interface CancelInvoiceRepository extends JpaRepository<CancelInvoice, Integer>  {

	CancelInvoice findById(String id);
}
