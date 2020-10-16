package org.epragati.dto;

import java.io.Serializable;
/**
 * 
 * @author saikiran.kola
 *
 */
public class VehicleCov implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String cov;
	private String id;
	public String getCov() {
		return cov;
	}
	public void setCov(String cov) {
		this.cov = cov;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
