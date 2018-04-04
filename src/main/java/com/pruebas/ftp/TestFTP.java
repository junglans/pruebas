package com.pruebas.ftp;

import java.util.List;

public class TestFTP {

	public static void main(String [] args) throws Exception {
		System.out.println(String.format("%1$-10s", "SPAIN"));
		String val = "TP12".replaceAll("[^\\d.]", "");
		
		System.out.println(String.format("%1$010d", Integer.parseInt(val)));
	/*
		FtpClient client = new FtpClient("95.60.152.105","areas","Zertifika");
		client.connect();
		if (client.isConnected()) {
			List<String> fileNames = client.getFileNamesFromPath("/FacturasFI/Processed/","^Factura_[A-Z]\\d{8}_\\d{10}_\\d{13}\\.xml$");
			System.out.println(fileNames);
		}
		client.disconnect();
		*/
	}
}
