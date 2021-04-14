package com.techshopbe.service;

import java.util.List;

import com.techshopbe.entity.User;

public interface UserService {
	List<User> getAll();
	User getById(int id);
	void add(User user) throws Exception;
	void delete(int id);
}
