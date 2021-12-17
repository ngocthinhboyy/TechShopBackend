package com.techshopbe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.CustomerDTO;
import com.techshopbe.entity.Role;
import com.techshopbe.entity.User;
import com.techshopbe.repository.RewardRepository;
import com.techshopbe.repository.RoleRepository;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RewardRepository rewardRepository;
	@Override
	public List<CustomerDTO> getAllCustomer() {
		Role role = roleRepository.findByRoleName("ROLE_CUSTOMER");
		List<User> users = userRepository.findAllByRoleID(role.getId());
		List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
		for(User user : users) {
			CustomerDTO customer = new CustomerDTO();
			customer.setID(user.getId());
			customer.setEmail(user.getEmail());
			customer.setAddress(user.getAddress());
			customer.setDob(user.getDOB());
			customer.setFullname(user.getFullname());
			customer.setGender(user.getGender());
			customer.setPhone(user.getPhone());
			customer.setReward(rewardRepository.findById(user.getRewardID()).getName());
			customers.add(customer);
		}
		
		return customers;
	}

}
