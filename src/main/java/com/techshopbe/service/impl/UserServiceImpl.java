package com.techshopbe.service.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.dto.UserDTO;
import com.techshopbe.entity.Invoice;
import com.techshopbe.entity.Reward;
import com.techshopbe.entity.User;
import com.techshopbe.repository.InvoiceRepository;
import com.techshopbe.repository.RewardRepository;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	final int CUSTOMER_ROLE = 1;
	final int DEFAULT_REWARD_LEVEL = 1;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RewardRepository rewardRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public UserDTO getById(int id) {
		User user = userRepository.findById(id);
		UserDTO userDTO = new UserDTO(user.getId(), user.getFullname(), user.getPhone(), user.getAddress(),
				user.getEmail(), user.getGender(), user.getDOB());

		return userDTO;
	}

	@Override
	public void add(User user) throws Exception {
		User entityHasSameEmail = userRepository.findByEmail(user.getEmail());
		if (entityHasSameEmail == null) {
			String hashPassword = BCrypt.hashpw(user.getPswd(), BCrypt.gensalt());

			if (user.getRoleID() == CUSTOMER_ROLE) {
				Reward defaultReward = rewardRepository.findByLevel(DEFAULT_REWARD_LEVEL);
				user.setRewardID(defaultReward.getId());
			}

			user.setPswd(hashPassword);
			userRepository.save(user);
		} else {
			throw new Exception("Email already existed");
		}
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public ShippingInfoDTO getShippingInfoByEmail(String email) {
		User user = userRepository.findByEmail(email);
		ShippingInfoDTO shippingInfoDTO = new ShippingInfoDTO(user.getFullname(), user.getPhone(), user.getAddress());

		return shippingInfoDTO;
	}

	@Override
	public void updateUserReward(int invoiceID, int step) {
		if (step != 5) {
			return;
		}
		Invoice invoice = invoiceRepository.findByid(invoiceID);

		User user = userRepository.findById(invoice.getUserID());
		int newAccumulativeOrder = user.getAccumulativeOrder() + 1;
		int newAccumulativePending = user.getAccumulativeSpending() + invoice.getTotalCost();

		int userLevel = rewardRepository.findById(user.getRewardID()).getLevel();
		List<Reward> rewards = rewardRepository.findByLevelGreaterThanOrderByLevelDesc(userLevel);
		for (Reward reward : rewards) {
			if (reward.getMinOrder() <= newAccumulativeOrder && reward.getMinSpending() <= newAccumulativePending) {
				user.setRewardID(reward.getId());
				break;
			}
		}
		user.setAccumulativeOrder(newAccumulativeOrder);
		user.setAccumulativeSpending(newAccumulativePending);
		userRepository.save(user);

	}

	@Override
	public void updateUserReward(String rewardID) {
		Reward reward = rewardRepository.findById(rewardID);
		
		// nothing change when update base reward
		if (reward.getLevel() == 1) {
			return;
		}
		
		Reward lowerReward = rewardRepository.findByLevel(reward.getLevel() - 1);
		List<User> users = userRepository.findAllByRewardIDAndIsDeleted(reward.getId(), false);
		List<User> usersLowerReward = userRepository.findAllByRewardIDAndIsDeleted(lowerReward.getId(), false);

		for (User user : users) {
			if (user.getAccumulativeOrder() < reward.getMinOrder()
					|| user.getAccumulativeSpending() < reward.getMinSpending()) {
				user.setRewardID(lowerReward.getId());
				userRepository.save(user);
			}
		}

		for (User user : usersLowerReward) {
			if (user.getAccumulativeOrder() >= reward.getMinOrder()
					&& user.getAccumulativeSpending() >= reward.getMinSpending()) {
				user.setRewardID(reward.getId());
				userRepository.save(user);
			}
		}
	}

	@Override
	public void downgradeUserReward(String rewardID, int deletedLevel) {
		List<User> users = userRepository.findAllByRewardIDAndIsDeleted(rewardID, false);

		// cannot update or delete base reward -> level always >= 1
		Reward newReward = rewardRepository.findByLevel(deletedLevel - 1);

		for (User user : users) {
			user.setRewardID(newReward.getId());
			userRepository.save(user);
		}
	}

}
