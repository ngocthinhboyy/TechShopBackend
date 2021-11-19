package com.techshopbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techshopbe.entity.StatusInvoice;

@Repository
public interface StatusInvoiceRepository extends JpaRepository<StatusInvoice, Integer> {
	
	StatusInvoice findByStep(int step);
	StatusInvoice findByid(String id);
}
