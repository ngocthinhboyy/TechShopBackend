package com.techshopbe.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.dto.UserDTO;
import com.techshopbe.entity.Invoice;
import com.techshopbe.entity.Reward;
import com.techshopbe.entity.Role;
import com.techshopbe.entity.User;
import com.techshopbe.repository.InvoiceRepository;
import com.techshopbe.repository.RewardRepository;
import com.techshopbe.repository.RoleRepository;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	final String CUSTOMER_ROLE = "9b3822c0-3c36-4cd3-9e67-b6982637df5a";
	final String ADMIN_ROLE = "bb68f158-da4a-4b90-8694-0bd3f15ce4f4";
	final int DEFAULT_REWARD_LEVEL = 1;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RewardRepository rewardRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public UserDTO getById(String id) {
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

			if (user.getRoleID() == null) {
				Reward defaultReward = rewardRepository.findByLevel(DEFAULT_REWARD_LEVEL);
				user.setRewardID(defaultReward.getId());
				user.setRoleID(CUSTOMER_ROLE);
			}

			user.setPswd(hashPassword);
			String userID = UUID.randomUUID().toString();
			user.setId(userID);
			LocalDateTime joiningDate = LocalDateTime.now();
			user.setJoiningDate(joiningDate.toLocalDate().toString());
			userRepository.save(user);
		} else {
			throw new Exception("Email already existed");
		}
	}

	@Override
	public void delete(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public ShippingInfoDTO getShippingInfoByEmail(String email) {
		User user = userRepository.findByEmail(email);
		ShippingInfoDTO shippingInfoDTO = new ShippingInfoDTO(user.getFullname(), user.getPhone(), user.getAddress());

		return shippingInfoDTO;
	}

	@Override
	public void updateUserReward(String invoiceID, int step) {
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

	@Override
	public UserDTO getByEmail(String email) {
		User user = userRepository.findByEmail(email);
		Role role = roleRepository.findById(user.getRoleID());
		UserDTO userDTO = new UserDTO();
		userDTO.setUserID(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setFullname(user.getFullname());
		userDTO.setAddress(user.getAddress());
		userDTO.setDob(user.getDOB());
		userDTO.setGender(user.getGender());
		userDTO.setPhone(user.getPhone());
		userDTO.setRole(role.getRoleName().replace("ROLE_", ""));
		return userDTO;
	}

}
