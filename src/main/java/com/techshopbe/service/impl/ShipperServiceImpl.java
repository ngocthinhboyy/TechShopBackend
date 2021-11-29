package com.techshopbe.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.entity.Shipper;
import com.techshopbe.repository.ShipperRepository;
import com.techshopbe.service.ShipperService;

@Service
public class ShipperServiceImpl implements ShipperService {

	@Autowired
	ShipperRepository shipperRepository;
	@Override
	public List<Shipper> getAll() {
		return shipperRepository.findAllByIsDeleted(false);
	}

	@Override
	public void add(Shipper shipper) {
		shipper.setId(UUID.randomUUID().toString());
		shipperRepository.save(shipper);
	}

	@Override
	public void update(Shipper shipper) {
		shipperRepository.save(shipper);
		
	}

	@Override
	public void delete(String id) {
		Shipper shipper = shipperRepository.findById(id);
		shipper.setIsDeleted(true);
		shipperRepository.save(shipper);
		
	}

}
