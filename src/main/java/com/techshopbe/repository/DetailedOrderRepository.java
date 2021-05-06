package com.techshopbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techshopbe.dto.DetailedOrderDTO;
import com.techshopbe.entity.DetailedInvoice;

@Repository
public interface DetailedOrderRepository extends JpaRepository<DetailedInvoice, Integer>{

	//DetailedOrderDTO findByProductId(int id);

}
