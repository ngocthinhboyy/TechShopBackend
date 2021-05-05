package com.techshopbe.service.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.entity.User;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}
	@Override
	public User getById(int id) {
		return userRepository.findById(id).get();
	}
	@Override
	public void add(User user) throws Exception {
		User entityHasSameEmail = userRepository.findByEmail(user.getEmail());
		if(entityHasSameEmail == null) {
			String hashPassword = BCrypt.hashpw(user.getPswd(), BCrypt.gensalt());
			user.setPswd(hashPassword);
			userRepository.save(user);
		}
		else {
			throw new Exception("Email already existed");
		}
	}
	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	
}
