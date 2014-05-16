package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: VehicleModel
 * 
 * @author dubrouski
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class VehicleModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String name;
	
	@ManyToOne
	private VehicleBrand brand;

	public VehicleModel() {
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
	public VehicleBrand getBrand() {
		return this.brand;
	}

	public void setBrand(VehicleBrand brand) {
		this.brand = brand;
	}
   
}
