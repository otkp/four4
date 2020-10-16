package org.epragati.dto;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author saikiran.kola
 *
 */
public class VehicleDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String type;
	private List<VehicleCov> vehiclecov;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<VehicleCov> getVehiclecov() {
		return vehiclecov;
	}

	public void setVehiclecov(List<VehicleCov> vehiclecov) {
		this.vehiclecov = vehiclecov;
	}

	@Override
	public String toString() {
		return "VehicleTypeDTO [type=" + type + ", vehiclecov=" + vehiclecov + "]";
	}

}
