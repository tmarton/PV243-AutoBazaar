package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import cz.muni.fi.pv243.enums.FuelType;
import cz.muni.fi.pv243.enums.VehicleBodyType;

import java.util.ArrayList;
import java.util.Objects;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * Entity implementation class for Entity: Advertisement
 * 
 * @author dubrouski
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Advertisement.getByAccount", query = "SELECT a FROM Advertisement a WHERE a.advertisingAccount.id = :id")
})
public class Advertisement implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_advertising_account")
	private AdvertisingAccount advertisingAccount;
	
    @NotNull
	@Column(name = "creation_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime creationDate;
	
	@NotNull
	@ManyToOne
	private VehicleBrand brand;
	
	@NotNull
	@ManyToOne
	private VehicleModel model;
	
	@NotNull
	@Column(name="production_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime productionDate;
	
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
	
	@OneToMany(mappedBy="advertisement", fetch=FetchType.EAGER)
	private List<VehiclePhoto> vehiclePhotos = new ArrayList<>();	
	
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
	public DateTime getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(DateTime productionDate) {
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

    public List<VehiclePhoto> getVehiclePhotos() {
        return vehiclePhotos;
    }

    public void setVehiclePhotos(List<VehiclePhoto> vehiclePhotos) {
        this.vehiclePhotos = vehiclePhotos;
    }
	
    public void addVehiclePhoto(VehiclePhoto vehiclePhoto) {
        vehiclePhotos.add(vehiclePhoto);
    }	

    @Override
    public String toString() {
        return "Advertisement{" + "id=" + id + ", creationDate=" + creationDate + ", brand=" + brand + ", model=" + model + ", productionDate=" + productionDate + ", engineDisplacement=" + engineDisplacement + ", fuelType=" + fuelType + ", bodyType=" + bodyType + ", description=" + description + ", vehiclePhotoCount=" + vehiclePhotos.size() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.advertisingAccount);
        hash = 89 * hash + Objects.hashCode(this.creationDate);
        hash = 89 * hash + Objects.hashCode(this.brand);
        hash = 89 * hash + Objects.hashCode(this.model);
        hash = 89 * hash + Objects.hashCode(this.productionDate);
        hash = 89 * hash + this.engineDisplacement;
        hash = 89 * hash + Objects.hashCode(this.fuelType);
        hash = 89 * hash + Objects.hashCode(this.bodyType);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.vehiclePhotos);
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
        final Advertisement other = (Advertisement) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.advertisingAccount, other.advertisingAccount)) {
            return false;
        }
        if (!Objects.equals(this.creationDate, other.creationDate)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.productionDate, other.productionDate)) {
            return false;
        }
        if (this.engineDisplacement != other.engineDisplacement) {
            return false;
        }
        if (this.fuelType != other.fuelType) {
            return false;
        }
        if (this.bodyType != other.bodyType) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.vehiclePhotos, other.vehiclePhotos)) {
            return false;
        }
        return true;
    }
    
    
}
