package com.techshopbe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.CustomerDTO;
import com.techshopbe.dto.DetailedCustomerDTO;
import com.techshopbe.dto.InvoiceForUserDTO;
import com.techshopbe.entity.CancelInvoice;
import com.techshopbe.entity.Invoice;
import com.techshopbe.entity.Role;
import com.techshopbe.entity.StatusInvoice;
import com.techshopbe.entity.User;
import com.techshopbe.repository.CancelInvoiceRepository;
import com.techshopbe.repository.InvoiceRepository;
import com.techshopbe.repository.RewardRepository;
import com.techshopbe.repository.RoleRepository;
import com.techshopbe.repository.StatusInvoiceRepository;
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
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private StatusInvoiceRepository statusInvoiceRepository;
	@Autowired
	CancelInvoiceRepository cancelInvoiceRepository;
	@Override
	public List<CustomerDTO> getAllCustomer() {
		Role role = roleRepository.findByRoleName("ROLE_CUSTOMER");
		List<User> users = userRepository.findAllByRoleID(role.getId());
		List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
		for(User user : users) {
			CustomerDTO customer = new CustomerDTO();
			customer.setID(user.getId());
			customer.setEmail(user.getEmail());
			customer.setFullname(user.getFullname());
			customer.setPhone(user.getPhone());
			customer.setReward(rewardRepository.findById(user.getRewardID()).getName());
			customers.add(customer);
		}
		
		return customers;
	}
	@Override
	public DetailedCustomerDTO getDetailedCustomer(String id) {
		User user = userRepository.findById(id);
		DetailedCustomerDTO detailedCustomer = new DetailedCustomerDTO();
		detailedCustomer.setEmail(user.getEmail());
		detailedCustomer.setFullname(user.getFullname());
		detailedCustomer.setPhone(user.getPhone());
		detailedCustomer.setDob(user.getDOB());
		detailedCustomer.setAccumulativeOrder(user.getAccumulativeOrder());
		detailedCustomer.setAccumulativeSpending(user.getAccumulativeSpending());
		detailedCustomer.setAddress(user.getAddress());
		detailedCustomer.setGender(user.getGender());
		detailedCustomer.setReward(rewardRepository.findById(user.getRewardID()).getName());
		
		
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = invoiceRepository.findByUserID(id);
		for (Invoice invoice: invoices) {
			InvoiceForUserDTO invoiceForCustomer = new InvoiceForUserDTO();
			invoiceForCustomer.setId(invoice.getId());
			invoiceForCustomer.setTotal(invoice.getTotalCost());
			invoiceForCustomer.setTotalItems(invoice.getTotalItems());
			
			if(invoice.isCancelled()) {
				CancelInvoice cancelInvoice = cancelInvoiceRepository.findById(invoice.getCancelID());
				invoiceForCustomer.setStatusDetail("Cancelled");
				invoiceForCustomer.setStatus("Cancelled");
				invoiceForCustomer.setReason(cancelInvoice.getReason());
				invoiceForCustomer.setWhoCancel(cancelInvoice.getWhoCancel());
				invoiceForCustomer.setLastConfirm(cancelInvoice.getCancelledDate());
			} else {
				StatusInvoice statusInvoice = statusInvoiceRepository.findByid(invoice.getStatusID());
				invoiceForCustomer.setStatusDetail(statusInvoice.getDetail());
				String[] processDate = invoice.getProcessDate().split(", ");
				invoiceForCustomer.setLastConfirm(processDate[processDate.length - 1]);
				invoiceForCustomer.setStatus(statusInvoice.getStatus());
			}
			
			detailedCustomer.orders.add(invoiceForCustomer);
		}

		return detailedCustomer;
	}

}
