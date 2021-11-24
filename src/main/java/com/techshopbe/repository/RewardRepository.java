package com.techshopbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techshopbe.entity.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Integer>  {
	List<Reward> findByLevelGreaterThanOrderByLevelDesc(int level);
	Reward findById(String id);
	List<Reward> findByLevelGreaterThanEqual(int level);
	List<Reward> findByLevelGreaterThan(int level);
	List<Reward> findAllByIsDeletedOrderByLevelAsc(boolean isDeleted);
	Reward findByLevel(int deletedLevel);
}