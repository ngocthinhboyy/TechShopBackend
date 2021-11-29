package com.techshopbe.service;

import java.util.List;

import com.techshopbe.entity.Shipper;

public interface ShipperService {
	
	public List<Shipper> getAll();
	public void add(Shipper shipperInfo);
	public void update(Shipper shipperInfo);
	public void delete(String id);
}
