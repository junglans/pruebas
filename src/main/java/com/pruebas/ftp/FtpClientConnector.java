package com.pruebas.ftp;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class FtpClientConnector implements Callable {

	private String host;
	private int port;
	private String user;
	private String password;
	
	private String path;
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		FtpClient ftpClient = new FtpClient(host, port, user, password);
		try {
			
			if (ftpClient.connect()) {
				return ftpClient.getFileNamesFromPath(path);
			} else {
				throw new java.lang.Exception("Unable to connect to ftp://"+user+":*****@"+host+":"+port+"/"+path);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			ftpClient.disconnect();
		}
		
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
