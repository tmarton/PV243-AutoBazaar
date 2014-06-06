package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: VehicleModel
 * 
 * @author dubrouski
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Vehicle.findByBrand",
                query="SELECT m FROM VehicleModel m LEFT JOIN m.brand b WHERE b.id = :id")
})
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class VehicleModel implements Serializable {
	
	private static final long serialVersionUID = 7L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
    @NotEmpty
	private String name;
	
	@ManyToOne
	private VehicleBrand brand;

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
	public VehicleBrand getBrand() {
		return this.brand;
	}

	public void setBrand(VehicleBrand brand) {
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
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.brand);
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
        final VehicleModel other = (VehicleModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        return true;
    }
 
    
}
