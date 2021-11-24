package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techshopbe.entity.Invoice;
import com.techshopbe.entity.User;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	
	@Query("SELECT id FROM Invoice i WHERE i.userInvoiceIndex = ?1")
	int findInvoiceIDByUserInvoiceIndex(String userInvoiceIndex);
	
	List<Invoice> findByUserID(String userID);
	Invoice findByid(String id);
	List<Invoice> findAll();
}
