package com.techshopbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	
	@Query("SELECT new com.techshopbe.dto.ShippingInfoDTO(u.fullname, u.phone, u.address) FROM User u WHERE u.email = ?1")
	ShippingInfoDTO findShippingInfoByEmail(String email);
	
}
