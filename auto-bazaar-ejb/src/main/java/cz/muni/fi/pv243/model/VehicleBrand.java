package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: VehicleBrand
 *	
 * @author dubrouski
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class VehicleBrand implements Serializable {

	private static final long serialVersionUID = 6L;
	   
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
    @NotEmpty
	private String name;	

	public VehicleBrand() {
		super();
	}   
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
        hash = 11 * hash + Objects.hashCode(this.name);
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
        final VehicleBrand other = (VehicleBrand) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
