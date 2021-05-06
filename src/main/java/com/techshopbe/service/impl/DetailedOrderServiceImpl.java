package com.techshopbe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.DetailedOrderDTO;
import com.techshopbe.repository.DetailedOrderRepository;
import com.techshopbe.service.DetailedOrderService;

@Service
public class DetailedOrderServiceImpl implements DetailedOrderService{

	@Autowired
	DetailedOrderRepository detailedOrderRepository;
	@Override
	public DetailedOrderDTO getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
