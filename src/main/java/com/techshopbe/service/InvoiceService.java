package com.techshopbe.service;

import java.util.List;

import com.techshopbe.dto.InvoiceDTO;
import com.techshopbe.dto.InvoiceForUserDTO;

public interface InvoiceService {
	public void add(String invoice);
	public List<InvoiceForUserDTO> getAllUserInvoices();
	public InvoiceDTO getByInvoiceID(int invoiceID);
	public void updateReviewStatus(int invoiceID, int productID);
	public void updateStatusInvoice(int invoiceID) throws Exception;
	public void cancelInvoice(int invoiceID, String reason) throws Exception;

}
