package com.pruebas.util;

import java.util.List;
import java.util.Map;

public class TicketUtils {
	
	private static String H_HDR = "H_HDR";
	private static String H_DTL = "H_DTL";
	private static String H_DSC = "H_DSC";
	private static String H_TM = "H_TM";
	private static String H_TAX = "H_TAX";
	private static String H_VOC = "H_VOC";
	private static String H_AR = "H_AR";
	
	private static String HDR = "HDR";
	private static String DTL = "DTL";
	private static String DSC = "DSC";
	private static String TM = "TM";
	private static String TAX = "TAX";
	private static String VOC = "VOC";
	private static String AR = "AR";
	/**
	 * Campos comunes que relacionan todos los elementos de un ticket son:
	 * Property + CheckWsName + CheckWsNumber + RvcNum + BusinessDate + GuestCheckId + CheckNum
	 * 
	 */
	public static void loadTicket(Map<String, List<List<String>>> tickets, Map<String, Map<String, Object>> headers, List<String> row) throws Exception {
		String rowTypeId = row.get(0);
		if (HDR.equals(rowTypeId)) {
			processHeaderRows(tickets, row);
		} else if (DTL.equals(rowTypeId) || DSC.equals(rowTypeId) || TM.equals(rowTypeId)) {
			processBodyRows(tickets, row);
		} else if (TAX.equals(rowTypeId)) {
			processTaxRows(tickets, row);
		} else if (VOC.equals(rowTypeId)) {
			processVocRows(tickets, row);
		} else if (AR.equals(rowTypeId)) {
			processArRows(tickets, row);
		} else if (	H_HDR.equals(rowTypeId) || H_DTL.equals(rowTypeId) || H_DSC.equals(rowTypeId) || H_TM.equals(rowTypeId) ||  
					H_TAX.equals(rowTypeId) || H_VOC.equals(rowTypeId) ||  H_AR.equals(rowTypeId)) {
			validateHeader(rowTypeId, headers, row);
		} else {
			throw new java.lang.Exception("Non processable line '" + row + "' found on file");
		}
	}
	
	private static void processHeaderRows(Map<String, List<List<String>>> tickets, List<String> row) {
		String key  = row.get(1)+row.get(2)+row.get(3)+row.get(4)+row.get(7)+row.get(12)+row.get(13);
		List<List<String>> ticket = new java.util.ArrayList<List<String>>();
		tickets.put(key,ticket);
		ticket.add(row);	
	}
	private static void processBodyRows(Map<String, List<List<String>>> tickets, List<String> row) throws Exception {
		String key = row.get(1)+row.get(2)+row.get(3)+row.get(4)+row.get(6)+row.get(9)+row.get(10);
		add(tickets, key, row);
	}
	private static void processTaxRows(Map<String, List<List<String>>> tickets, List<String> row) throws Exception {
		String key = row.get(1)+row.get(2)+row.get(3)+row.get(4)+row.get(5)+row.get(6)+row.get(7);
		add(tickets, key, row);
	}
	private static void processVocRows(Map<String, List<List<String>>> tickets, List<String> row) throws Exception {
		String key = row.get(1)+row.get(2)+row.get(3)+row.get(6)+row.get(7)+row.get(9)+row.get(10);
		add(tickets, key, row);
	}
	private static void processArRows(Map<String, List<List<String>>> tickets, List<String> row) throws Exception {
		String key = row.get(1)+row.get(11)+row.get(12)+row.get(3)+row.get(2)+row.get(4)+row.get(5);
		add(tickets, key, row);
	}
	
	private static void validateHeader(String headerId, Map<String, Map<String, Object>> headers, List<String> hdrRow) throws Exception {
		@SuppressWarnings("unchecked")
		String header = String.join("|", (List<String>)headers.get(headerId).get("value"));
		String row = String.join("|", hdrRow);
		headers.get(headerId).put("found", Boolean.TRUE);
		if (!header.equals(row)) {
			throw new java.lang.Exception("The header '" + row + "' is wrong or incomplete");
		}
	}
	private static void add(Map<String, List<List<String>>> tickets, String key, List<String>row) throws Exception {
		List<List<String>> ticket = tickets.get(key);
		if (ticket != null) {
			ticket.add(row);
		} else {
			throw new java.lang.Exception("Non processable line '" + row + "' found on file");
		}
	}
	
}
