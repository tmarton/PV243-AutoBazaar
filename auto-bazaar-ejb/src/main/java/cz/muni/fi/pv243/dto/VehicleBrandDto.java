package cz.muni.fi.pv243.dto;

import java.io.Serializable;
import java.util.Objects;

public class VehicleBrandDto implements Serializable {

	private static final long serialVersionUID = 6L;
	   
	private Long id;
	private String name;	

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

    @Override
    public String toString() {
        return "VehicleBrand{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final VehicleBrandDto other = (VehicleBrandDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
