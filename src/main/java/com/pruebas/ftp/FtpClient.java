package com.pruebas.ftp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(FtpClient.class);
	private FTPClient client;
	private String server;
	private String user;
	private String password;
	
	private int port;
	
	public FtpClient(String server, String user, String password) {
		super();
		this.server = server;
		this.user = user;
		this.password = password;
		this.port = 21;
	}

	public FtpClient(String server, int port, String user, String password) {
		super();
		this.server = server;
		this.user = user;
		this.password = password;
		this.port = port;
	}
	
	public boolean connect() throws Exception {
		
		client = new FTPClient();
		try {
			client.connect(server, this.port);
			client.login(user, password);
			int reply = client.getReplyCode();

		    if(!FTPReply.isPositiveCompletion(reply)) {
		        client.disconnect();
		        throw new java.io.IOException("Couldn't connect to the server");
		    }
		    client.setBufferSize(1024 * 1024);
		    LOGGER.info("Buffer Size:" + client.getBufferSize());
		    
		    return isConnected();
		    
		} catch (IOException e) {
			client.disconnect();
			LOGGER.error("Couldn't connect to the server", e);
			throw e;
		}  
	    
	}
	List<String> getFileNamesFromPath(String pathname, final String regex) throws Exception {
		try {
			List<String> fileNames = new ArrayList<String>();
			client.setAutodetectUTF8(true);
			client.enterLocalPassiveMode();
		 
			FTPFile[] files = client.listFiles(pathname, new FTPFileFilter() {
				@Override
				public boolean accept(FTPFile file) {
					return Pattern.matches(regex, file.getName());
				}
			} );
			
			for(FTPFile file : files) {
				fileNames.add(file.getName());
			}
				
			return fileNames;
		} catch (Exception e) {
			client.disconnect();
			LOGGER.error("Couldn't getFileNamesFromPath:" + pathname, e);
			throw e;
		}
	}
	
	List<String> getFileNamesFromPath(String pathname) throws Exception {
		try {
			List<String> fileNames = new ArrayList<String>();
			
			cwd(pathname);
			
			client.setAutodetectUTF8(true);
			client.enterLocalPassiveMode();
			FTPFile[] files = client.listFiles();
			for(FTPFile file : files) {
				fileNames.add(file.getName());
			}
				
			return fileNames;
		} catch (Exception e) {
			client.disconnect();
			LOGGER.error("Couldn't getFileNamesFromPath:" + pathname, e);
			throw e;
		}
		
	}
	
	public boolean isConnected() {
		if (client != null) {
			return client.isConnected();
		} else {
			return false;
		}
	}
	
	public void disconnect() {
		if (client != null) {
			if (client.isConnected()) {
				try {
					client.disconnect();
					LOGGER.info("Client disconnected.");
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	private void cwd(String pathname) throws Exception {
		int reply = client.cwd(pathname);
		if(!FTPReply.isPositiveCompletion(reply)) {
			throw new Exception("Couldn't execute cwd command on folder " + pathname);
		} else {
			LOGGER.info("Working directory: " + client.printWorkingDirectory());
		}
	}
}
