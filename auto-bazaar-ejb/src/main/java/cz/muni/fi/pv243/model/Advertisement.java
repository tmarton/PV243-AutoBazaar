package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import cz.muni.fi.pv243.enums.FuelType;
import cz.muni.fi.pv243.enums.VehicleBodyType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.joda.time.DateTime;
import org.joda.time.contrib.hibernate.PersistentDateTime;

/**
 * Entity implementation class for Entity: Advertisement
 * 
 * @author dubrouski
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Advertisement.getByAccount", query = "SELECT a FROM Advertisement a WHERE a.advertisingAccount.id = :id")
})
@TypeDefs({ @TypeDef(name = "jodaDateTime", typeClass = PersistentDateTime.class) })
public class Advertisement implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_advertising_account")
	private AdvertisingAccount advertisingAccount;
	
	@Column(name = "creation_date")
	private DateTime creationDate;
	
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
	
	@OneToMany(mappedBy="advertisement")
	private List<VehiclePhoto> vehiclePhotos;	
	
	public Advertisement() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public DateTime getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
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
	public AdvertisingAccount getAdvertisingAccount() {
		return advertisingAccount;
	}
	public void setAdvertisingAccount(AdvertisingAccount company) {
		this.advertisingAccount = company;
	}
	
	
}
