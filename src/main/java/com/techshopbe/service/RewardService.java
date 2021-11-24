package com.techshopbe.service;

import java.util.List;

import com.techshopbe.entity.Reward;

public interface RewardService {
	List<Reward> getAll();

	void addCheckedByBackend(Reward reward) throws Exception;

	void updateCheckedByBE(Reward reward) throws Exception;

	void delete(String rewardID) throws Exception;
	
	Reward getById(String id);
}
