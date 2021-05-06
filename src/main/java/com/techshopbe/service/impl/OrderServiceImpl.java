package com.techshopbe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.techshopbe.dto.OrderDTO;
import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.repository.ProductRepository;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.dto.DetailedOrderDTO;
import com.techshopbe.service.OrderService;

import net.bytebuddy.asm.Advice.Return;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void addOrder(String order) {
		// TODO Auto-generated method stub
		// System.out.println(order);
		Gson g = new Gson();
		OrderDTO detailedOrder = g.fromJson(order, OrderDTO.class);

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = userDetails.getUsername();
		detailedOrder.setEmail(email);

		getDetailedOrders(detailedOrder.getListProductOrder());

		if (detailedOrder.getShippingInfo() == null) {
			detailedOrder.setShippingInfo(getShippingInfo(email));
			;
		}

		detailedOrder.setTotalPrice(calculateTotalOrder(detailedOrder.getListProductOrder()));
		

	}

	public void getDetailedOrders(List<DetailedOrderDTO> detailedOrders) {

		for (DetailedOrderDTO product : detailedOrders) {
			int price = productRepository.findProductPriceByProductID(product.getProductID());

			int totalPrice = price * product.getQuantity();

			product.setProductPrice(price);
			product.setTotalPrice(totalPrice);

		}
	}

	public ShippingInfoDTO getShippingInfo(String email) {
		return userRepository.findShippingInfoByEmail(email);
	}

	public int calculateTotalOrder(List<DetailedOrderDTO> detailedOrders) {
		int totalOrder = 0;
		for (DetailedOrderDTO product : detailedOrders) {
			totalOrder += product.getTotalPrice();
		}
		return totalOrder;

	}

}
