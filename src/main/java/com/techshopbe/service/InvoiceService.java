package com.techshopbe.service;

import java.util.List;

import com.techshopbe.dto.InvoiceDTO;
import com.techshopbe.dto.InvoiceForUserDTO;

public interface InvoiceService {
	public void add(String invoice);
	public List<InvoiceForUserDTO> getAllUserInvoices();
	public InvoiceDTO getByInvoiceID(String invoiceID);
	public void updateReviewStatus(String invoiceID, int productID);
	public int updateStatusInvoice(String invoiceID) throws Exception;
	public void cancelInvoice(String invoiceID, String reason) throws Exception;
	public List<InvoiceForUserDTO> getAllInvoicesByMonthAndYear(int month, int year);
}
