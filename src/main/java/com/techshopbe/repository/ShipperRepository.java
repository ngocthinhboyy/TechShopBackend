package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techshopbe.entity.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

	Shipper findById(String id);
	List<Shipper> findAllByIsDeleted(boolean isDeleted);
}
