package com.techshopbe.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.techshopbe.dto.DetailedInvoiceDTO;
import com.techshopbe.dto.InvoiceDTO;
import com.techshopbe.dto.ShippingInfoDTO;
import com.techshopbe.dto.InvoiceForUserDTO;
import com.techshopbe.entity.CancelInvoice;
import com.techshopbe.entity.DetailedInvoice;
import com.techshopbe.entity.Invoice;
import com.techshopbe.entity.StatusInvoice;
import com.techshopbe.entity.ShippingInfo;
import com.techshopbe.repository.CancelInvoiceRepository;
import com.techshopbe.repository.DetailedInvoiceRepository;
import com.techshopbe.repository.InvoiceRepository;
import com.techshopbe.repository.ProductRepository;
import com.techshopbe.repository.ShippingInfoRepository;
import com.techshopbe.repository.StatusInvoiceRepository;
import com.techshopbe.repository.UserRepository;
import com.techshopbe.security.CustomUserDetails;
import com.techshopbe.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	ShippingInfoRepository shippingInfoRepository;
	@Autowired
	DetailedInvoiceRepository detailedInvoiceRepository;
	@Autowired
	StatusInvoiceRepository statusInvoiceRepository;
	@Autowired
	CancelInvoiceRepository cancelInvoiceRepository;

	@Override
	public void add(String invoice) {
		Gson g = new Gson();
		InvoiceDTO invoiceDTO = g.fromJson(invoice, InvoiceDTO.class);
		boolean otherShippingAddress = true;

		// set email
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		String email = userDetails.getUsername();
		invoiceDTO.setEmail(email);

		// set detailed invoice (calculate price and total price)
		invoiceDTO.setDetailedInvoices(getDetailedInvoices(invoiceDTO.getDetailedInvoices()));

		// set shipping info
		if (invoiceDTO.getShippingInfo() == null) {
			invoiceDTO.setShippingInfo(getShippingInfo(email));
			otherShippingAddress = false;
		}

		// set total price of invoice
		invoiceDTO.setTotal(calculateTotalInvoiceCost(invoiceDTO.getDetailedInvoices()));

		/*
		 * Insert new invoice
		 */
		// INVOICE userID, totalCost, invoiceDate, shippingDate, note,
		// otherShippingAddress, statusInvoice
		// shipping Date: now + 10 ngày
		int userID = userDetails.getUserID();
		LocalDateTime processDate = LocalDateTime.now();

		// totalInvoices: lưu tổng số hoá đơn của người dùng
		// userInvoiceIndex: là key phân biệt các invoice (không lấy newest id)
		// (userInvoiceIndex = email + totalInvoices)
		int totalInvoices = userRepository.findTotalInvoicesByEmail(email);
		String userInvoiceIndex = email + String.valueOf(totalInvoices);
		StatusInvoice statusInvoice = statusInvoiceRepository.findByStep(1);

		Invoice invoiceEntity = new Invoice();
		int totalItems = 0;
		invoiceEntity.setUserID(userID);
		invoiceEntity.setTotalCost(invoiceDTO.getTotal());
		invoiceEntity.setNote(invoiceDTO.getNote());
		invoiceEntity.setOtherShippingAddress(otherShippingAddress);
		invoiceEntity.setUserInvoiceIndex(userInvoiceIndex);
		invoiceEntity.setStatusID(statusInvoice.getId());
		invoiceEntity.setProcessDate(processDate.toString());
		invoiceEntity.setStep(1);
		invoiceEntity.setTotalItems(totalItems);
		invoiceEntity = invoiceRepository.save(invoiceEntity);
		// after insert invoice, increase totalInvoices of user
		userRepository.updateTotalInvoicesByEmail(totalInvoices + 1, email);

		/*
		 * Insert new shipping
		 */
		// get current invoice ID through userInvoiceIndex
		int invoiceID = invoiceRepository.findInvoiceIDByUserInvoiceIndex(userInvoiceIndex);

		// SHIPPINGINFO invoiceID, fullname, phone, address
		ShippingInfo shippingInfo = new ShippingInfo();
		shippingInfo.setInvoiceID(invoiceID);
		shippingInfo.setAddress(invoiceDTO.getShippingInfo().getAddress());
		shippingInfo.setFullname(invoiceDTO.getShippingInfo().getFullname());
		shippingInfo.setPhone(invoiceDTO.getShippingInfo().getPhone());
		shippingInfoRepository.save(shippingInfo);

		/*
		 * Insert new DETAILED INVOICE
		 */

		// DETAILEDINVOICE (invoiceID, productID, quantity, price)
		for (DetailedInvoiceDTO detailedInvoiceDTO : invoiceDTO.getDetailedInvoices()) {
			DetailedInvoice detailedInvoice = new DetailedInvoice();
			detailedInvoice.setInvoiceID(invoiceID);
			detailedInvoice.setPrice(detailedInvoiceDTO.getOldPrice());
			detailedInvoice.setProductID(detailedInvoiceDTO.getId());
			detailedInvoice.setQuantity(detailedInvoiceDTO.getQuantity());
			detailedInvoice.setTotalPrice(detailedInvoiceDTO.getTotalPrice());
			totalItems += detailedInvoiceDTO.getQuantity();
			detailedInvoiceRepository.save(detailedInvoice);
		}
		invoiceEntity.setTotalItems(totalItems);
		invoiceRepository.save(invoiceEntity);

	}

	public List<DetailedInvoiceDTO> getDetailedInvoices(List<DetailedInvoiceDTO> detailedInvoices) {

		for (DetailedInvoiceDTO detailedInvoice : detailedInvoices) {
			int price = productRepository.findProductPriceByProductID(detailedInvoice.getId());

			int totalPrice = price * detailedInvoice.getQuantity();

			detailedInvoice.setOldPrice(price);
			detailedInvoice.setTotalPrice(totalPrice);
		}
		return detailedInvoices;
	}

	public ShippingInfoDTO getShippingInfo(String email) {
		return userRepository.findShippingInfoByEmail(email);
	}

	public int calculateTotalInvoiceCost(List<DetailedInvoiceDTO> detailedInvoices) {
		int totalInvoiceCost = 0;
		for (DetailedInvoiceDTO detailedInvoice : detailedInvoices) {
			totalInvoiceCost += detailedInvoice.getTotalPrice();
		}
		return totalInvoiceCost;

	}

	@Override
	public List<InvoiceForUserDTO> getAllUserInvoices() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		List<Invoice> invoices = new ArrayList<Invoice>();
		List<InvoiceForUserDTO> invoicesForUser = new ArrayList<InvoiceForUserDTO>();
		invoices = invoiceRepository.findByUserID(userDetails.getUserID());
		for (Invoice invoice: invoices) {
			List<DetailedInvoiceDTO> detailedInvoices = detailedInvoiceRepository.findAllByInvoiceID(invoice.getId());
			InvoiceForUserDTO invoiceForUser = new InvoiceForUserDTO();
			invoiceForUser.setId(invoice.getId());
			invoiceForUser.setTotal(invoice.getTotalCost());
			invoiceForUser.setTotalItems(invoice.getTotalItems());
			if(detailedInvoices.get(0) != null) {
				invoiceForUser.setFirstProduct(detailedInvoices.get(0).getId(), detailedInvoices.get(0).getCategorySlug(), detailedInvoices.get(0).getName(), detailedInvoices.get(0).getImages(), detailedInvoices.get(0).getQuantity(), detailedInvoices.get(0).getOldPrice(), detailedInvoices.get(0).getSalePrice(), detailedInvoices.get(0).getTotalPrice());
			}
			
			if(invoice.isCancelled()) {
				CancelInvoice cancelInvoice = cancelInvoiceRepository.findById(invoice.getCancelID());
				invoiceForUser.setStatus("Cancelled");
				invoiceForUser.setStatusDetail("Cancelled");
				invoiceForUser.setReason(cancelInvoice.getReason());
			} else {
				StatusInvoice statusInvoice = statusInvoiceRepository.findByid(invoice.getStatusID());
				invoiceForUser.setStatus(statusInvoice.getStatus());
				invoiceForUser.setStatusDetail(statusInvoice.getDetail());
				invoiceForUser.setStatusNote(statusInvoice.getNote());
			}
			invoicesForUser.add(invoiceForUser);
		}
		return invoicesForUser;
	}

	@Override
	public InvoiceDTO getByInvoiceID(int invoiceID) {
		List<DetailedInvoiceDTO> detailedInvoices = detailedInvoiceRepository.findAllByInvoiceID(invoiceID);
		ShippingInfoDTO shippingInfo = shippingInfoRepository.findByInvoiceID(invoiceID);
		Invoice invoice = invoiceRepository.findByid(invoiceID);

		StatusInvoice statusInvoice = statusInvoiceRepository.findByid(invoice.getStatusID());

		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setDetailedInvoices(detailedInvoices);
		invoiceDTO.setNote(invoice.getNote());
		invoiceDTO.setShippingInfo(shippingInfo);
		invoiceDTO.setStatus(invoice.getStatus());
		invoiceDTO.setTotal(invoice.getTotalCost());
		invoiceDTO.setTotalItems(invoice.getTotalItems());
		if(invoice.isCancelled()) {
			CancelInvoice cancelInvoice = cancelInvoiceRepository.findById(invoice.getCancelID());
			statusInvoice = statusInvoiceRepository.findByStep(1);
			invoiceDTO.setCancelled(true);
			invoiceDTO.setCancelledDate(cancelInvoice.getCancelledDate());
			invoiceDTO.setReason(cancelInvoice.getReason());
			invoiceDTO.setStatus("Cancelled");
			String[] processDate = {invoice.getProcessDate().split(", ")[0]};
			invoiceDTO.setProcessDate(processDate);
		}
		else {
			invoiceDTO.setStatus(statusInvoice.getStatus());
			invoiceDTO.setStatusDetail(statusInvoice.getDetail());
			invoiceDTO.setStatusNote(statusInvoice.getNote());
			invoiceDTO.setProcessDate(invoice.getProcessDate().split(", "));
		}

		return invoiceDTO;
	}

	@Override
	public void updateReviewStatus(int invoiceID, int productID) {
		detailedInvoiceRepository.updateRatingInfoByProductID(invoiceID, productID);

	}

	@Override
	public void updateStatusInvoice(int invoiceID) throws Exception {
		Invoice invoice = invoiceRepository.findByid(invoiceID);

		if (invoice.getStep() < 6 && !invoice.isCancelled()) {
			StatusInvoice statusInvoice = statusInvoiceRepository.findByStep(invoice.getStep() + 1);
			LocalDateTime processDate = LocalDateTime.now();
			
			invoice.setStep(invoice.getStep() + 1);
			invoice.setStatusID(statusInvoice.getId());
			invoice.setProcessDate(invoice.getProcessDate() + ", " + processDate.toString());
			invoiceRepository.save(invoice);
		} else {
			throw new Exception("Already Done Process Or Cancelled");
		}
	}

	@Override
	public void cancelInvoice(int invoiceID, String reason) throws Exception {
		Invoice invoice = invoiceRepository.findByid(invoiceID);
		
		if (!invoice.isCancelled() && invoice.getStep() < 5) {
			String cancelID = UUID.randomUUID().toString();
			LocalDateTime cancelDate = LocalDateTime.now();
			CancelInvoice cancelInvoice = new CancelInvoice();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
			
			cancelInvoice.setId(cancelID);
			cancelInvoice.setCancelledDate(cancelDate.toString());
			if(!reason.isEmpty())
				cancelInvoice.setReason(reason);
			else 
				cancelInvoice.setReason("");
			if(userDetails.getAuthorities().toString().contains("CUSTOMER"))
				cancelInvoice.setWhoCancel("CUSTOMER");
			else
				cancelInvoice.setWhoCancel("ADMIN");
			
			invoice.setCancelID(cancelID);
			invoice.setCancelled(true);
			
			cancelInvoiceRepository.save(cancelInvoice);
			invoiceRepository.save(invoice);
		} else if (invoice.isCancelled()) {
			throw new Exception("Already Cancelled");
		} else if (invoice.getStep() >= 5) {
			throw new Exception("Cancel Failed");
		}
		
	}

	@Override
	public List<InvoiceForUserDTO> getAllInvoicesByMonthAndYear(int month, int year) {
		
		List<Invoice> invoices = new ArrayList<Invoice>();
		List<InvoiceForUserDTO> allInvoicesByMonthAndYear = new ArrayList<InvoiceForUserDTO>();
		invoices = invoiceRepository.findAll();
		for (Invoice invoice: invoices) {
			InvoiceForUserDTO invoiceForUser = new InvoiceForUserDTO();
			invoiceForUser.setId(invoice.getId());
			invoiceForUser.setTotal(invoice.getTotalCost());
			invoiceForUser.setTotalItems(invoice.getTotalItems());
			int monthLastConfirm = -1;
			int yearLastConfirm = -1;
			if(invoice.isCancelled()) {
				CancelInvoice cancelInvoice = cancelInvoiceRepository.findById(invoice.getCancelID());
				invoiceForUser.setStatus("Cancelled");
				invoiceForUser.setStatusDetail("Cancelled");
				invoiceForUser.setReason(cancelInvoice.getReason());
				monthLastConfirm = Integer.parseInt(cancelInvoice.getCancelledDate().split("-")[1]);
				yearLastConfirm = Integer.parseInt(cancelInvoice.getCancelledDate().split("-")[0]);
				invoiceForUser.setLastConfirm(cancelInvoice.getCancelledDate());
			} else {
				StatusInvoice statusInvoice = statusInvoiceRepository.findByid(invoice.getStatusID());
				invoiceForUser.setStatus(statusInvoice.getStatus());
				invoiceForUser.setStatusDetail(statusInvoice.getDetail());
				invoiceForUser.setStatusNote(statusInvoice.getNote());
				String[] arrProcessDate =  invoice.getProcessDate().split(", ");
				monthLastConfirm = Integer.parseInt(arrProcessDate[arrProcessDate.length - 1].split("-")[1]);
				yearLastConfirm = Integer.parseInt(arrProcessDate[arrProcessDate.length - 1].split("-")[0]);
				invoiceForUser.setLastConfirm(arrProcessDate[arrProcessDate.length - 1]);
			}
			if(month == monthLastConfirm && year == yearLastConfirm) {
				allInvoicesByMonthAndYear.add(invoiceForUser);
			}
		}

		return allInvoicesByMonthAndYear;
	}

}
