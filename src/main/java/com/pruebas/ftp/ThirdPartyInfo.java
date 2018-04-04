package com.pruebas.ftp;

import java.io.Serializable;
import java.util.Map;

public class ThirdPartyInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4392445660715289894L;
	private String id;
	private String name;
	private String path;
	private String errorPath;
	private String processPath;
	private Map<String, String> destination;
	
	public ThirdPartyInfo() {}
	public ThirdPartyInfo(String id, String name, String path, String processPath, String errorPath) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.processPath = processPath;
		this.errorPath = errorPath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getErrorPath() {
		return errorPath;
	}
	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}
	public String getProcessPath() {
		return processPath;
	}
	public void setProcessPath(String processPath) {
		this.processPath = processPath;
	}
	
	public Map<String, String> getDestination() {
		return destination;
	}
	public void setDestination(Map<String, String> destination) {
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "ThirdPartyInfo [id=" + id + ", name=" + name + ", path=" + path + ", errorPath=" + errorPath
				+ ", processPath=" + processPath + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorPath == null) ? 0 : errorPath.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((processPath == null) ? 0 : processPath.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThirdPartyInfo other = (ThirdPartyInfo) obj;
		if (errorPath == null) {
			if (other.errorPath != null)
				return false;
		} else if (!errorPath.equals(other.errorPath))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (processPath == null) {
			if (other.processPath != null)
				return false;
		} else if (!processPath.equals(other.processPath))
			return false;
		return true;
	}
	
}
