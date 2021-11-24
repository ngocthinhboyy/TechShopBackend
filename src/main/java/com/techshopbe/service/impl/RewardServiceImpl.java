package com.techshopbe.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.entity.Reward;
import com.techshopbe.repository.RewardRepository;
import com.techshopbe.service.RewardService;

@Service
public class RewardServiceImpl implements RewardService {

	@Autowired
	RewardRepository rewardRepository;

	@Override
	public List<Reward> getAll() {
		return rewardRepository.findAllByIsDeletedOrderByLevelAsc(false);
	}

	public void checkIsExisted(Reward reward, Reward src) throws Exception {
		if (reward.getMinOrder() == src.getMinOrder() && reward.getMinSpending() == src.getMinSpending()) {
			throw new Exception("Reward is existed.");
		}
	}

	@Override
	public void addCheckedByBackend(Reward newReward) throws Exception {
		List<Reward> listRewards = rewardRepository.findAllByIsDeletedOrderByLevelAsc(false);

		Reward highestReward = listRewards.get(listRewards.size() - 1);
		Reward lowestReward = listRewards.get(0);
		
		if (newReward.getMinOrder() == 0 || newReward.getMinSpending() == 0) {
			throw new Exception("Reward is invalid.");
		}

		checkIsExisted(newReward, highestReward);
		checkIsExisted(newReward, lowestReward);

		if (newReward.getMinOrder() >= highestReward.getMinOrder()
				&& newReward.getMinSpending() >= highestReward.getMinSpending()) {
			newReward.setLevel(highestReward.getLevel() + 1);
		} else {
			for (int index = 0; index < listRewards.size() - 1; index++) {
				checkIsExisted(newReward, listRewards.get(index + 1));
				if (newReward.getMinOrder() >= listRewards.get(index).getMinOrder()
						&& newReward.getMinOrder() <= listRewards.get(index + 1).getMinOrder()
						&& newReward.getMinSpending() >= listRewards.get(index).getMinSpending()
						&& newReward.getMinSpending() <= listRewards.get(index + 1).getMinSpending()) {
					newReward.setLevel(listRewards.get(index + 1).getLevel());
					break;
				}
			}
		}

		if (newReward.getLevel() == 0) {
			throw new Exception("Reward is invalid.");
		} else {
			for (int index = newReward.getLevel() - 1; index < listRewards.size(); index++) {
				listRewards.get(index).setLevel(listRewards.get(index).getLevel() + 1);
				rewardRepository.save(listRewards.get(index));
			}
			String rewardID = UUID.randomUUID().toString();
			newReward.setId(rewardID);
			rewardRepository.save(newReward);
		}

	}

	@Override
	public void updateCheckedByBE(Reward reward) throws Exception {
		if (reward.getMinOrder() == 0 || reward.getMinSpending() == 0) {
			throw new Exception("Reward is invalid.");
		}
		
		Reward updateReward = rewardRepository.findById(reward.getId());

		if (updateReward.isDeleted()) {
			throw new Exception("Already deleted.");
		}
		Reward prevReward = rewardRepository.findByLevel(updateReward.getLevel() - 1);
		if (prevReward != null) {
			checkIsExisted(reward, prevReward);
			if (reward.getMinSpending() < prevReward.getMinSpending()
					|| reward.getMinOrder() < prevReward.getMinOrder()) {
				throw new Exception("Min spending or Min order is invalid.");
			}

		}
		Reward nextReward = rewardRepository.findByLevel(updateReward.getLevel() + 1);
		if (nextReward != null) {
			checkIsExisted(reward, nextReward);
			if (reward.getMinSpending() > nextReward.getMinSpending()
					|| reward.getMinOrder() > nextReward.getMinOrder()) {
				throw new Exception("Min spending or Min order is invalid..");
			}
		}

		updateReward.setName(reward.getName());
		// not allow update base reward
		if (prevReward != null) {
			updateReward.setMinSpending(reward.getMinSpending());
			updateReward.setMinOrder(reward.getMinOrder());
		}
		rewardRepository.save(updateReward);

	}

	public void update(Reward reward) throws Exception {
		Reward updateReward = rewardRepository.findById(reward.getId());
		if (updateReward.isDeleted()) {
			throw new Exception("Already deleted.");
		}

		// not allow update base reward
		if (reward.getLevel() != 0) {
			updateReward.setMinSpending(reward.getMinSpending());
		}
		updateReward.setName(reward.getName());
		rewardRepository.save(updateReward);
	}

	@Override
	public void delete(String rewardID) throws Exception {
		Reward deleteReward = rewardRepository.findById(rewardID);

		if (deleteReward.isDeleted()) {
			throw new Exception("Already deleted.");
		} else {
			if (deleteReward.getLevel() == 1) {
				throw new Exception("Cannot delete base reward.");
			} else {

				List<Reward> listRewards = rewardRepository.findByLevelGreaterThan(deleteReward.getLevel());
				for (int index = 0; index < listRewards.size(); index++) {
					listRewards.get(index).setLevel(listRewards.get(index).getLevel() - 1);
					rewardRepository.save(listRewards.get(index));
				}
				deleteReward.setDeleted(true);
				deleteReward.setLevel(-1);
				rewardRepository.save(deleteReward);
			}
		}

	}

	@Override
	public Reward getById(String id) {
		return rewardRepository.findById(id);
	}

}
