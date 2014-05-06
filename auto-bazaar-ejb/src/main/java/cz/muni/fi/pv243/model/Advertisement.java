package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import cz.muni.fi.pv243.enums.FuelType;
import cz.muni.fi.pv243.enums.VehicleBodyType;

/**
 * Entity implementation class for Entity: Advertisement
 *
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "description"))
public class Advertisement implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Account account;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	@NotNull
	@ManyToOne
	private VehicleBrand brand;
	
	@NotNull
	@ManyToOne
	private VehicleModel model;
	
	@NotNull
	@Column(name="production_date")
	private Date productionDate;
	
	@NotNull
	@Column(name="engine_displacement")
	private int engineDisplacement;
	
	@Column(name="fuel_type")
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
	
	@Column(name="body_type")
	@Enumerated(EnumType.STRING)
	private VehicleBodyType bodyType;
	
	@NotNull
	private String description;
	
	public Advertisement() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
   
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public FuelType getFuelType() {
		return fuelType;
	}
	
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	
	public VehicleBodyType getBodyType() {
		return bodyType;
	}
	
	public void setBodyType(VehicleBodyType bodyType) {
		this.bodyType = bodyType;
	}
	
	public VehicleBrand getBrand() {
		return brand;
	}
	
	public void setBrand(VehicleBrand brand) {
		this.brand = brand;
	}
	
	public VehicleModel getModel() {
		return model;
	}
	
	public void setModel(VehicleModel model) {
		this.model = model;
	}
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public int getEngineDisplacement() {
		return engineDisplacement;
	}
	public void setEngineDisplacement(int engineDisplacement) {
		this.engineDisplacement = engineDisplacement;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
