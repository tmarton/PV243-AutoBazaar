package cz.muni.fi.pv243.dto;

import java.io.Serializable;
import java.util.Objects;

public class VehicleModelDto implements Serializable {
	
	private static final long serialVersionUID = 7L;
	
	private Long id;
	private String name;
	private VehicleBrandDto brand;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public VehicleBrandDto getBrand() {
		return this.brand;
	}

	public void setBrand(VehicleBrandDto brand) {
		this.brand = brand;
	}

    @Override
    public String toString() {
        return "VehicleModel{" + "id=" + id + ", name=" + name + ", brand=" + brand + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VehicleModelDto other = (VehicleModelDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
 
    
}
