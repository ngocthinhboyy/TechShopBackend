package com.techshopbe.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	
	@Query("SELECT new com.techshopbe.dto.ShippingInfoDTO(u.fullname, u.phone, u.address) FROM User u WHERE u.email = ?1")
	ShippingInfoDTO findShippingInfoByEmail(String email);
	
	@Query("SELECT totalInvoices FROM User u WHERE u.email = ?1")
	int findTotalInvoicesByEmail(String email);
	
	@Transactional
	@Modifying
    @Query("UPDATE User u SET u.totalInvoices = ?1 WHERE u.email = ?2")
    int updateTotalInvoicesByEmail(int totalInvoices, String email);
	
	
	User findById(String id);
	void deleteById(String id);

	List<User> findAllByRewardID(String rewardID);

	List<User> findAllByRewardIDAndIsDeleted(String id, boolean b);
	
	List<User> findAllByRoleID(String roleID);
	
	int countByJoiningDateBetween(String startDate, String endDate);
}
