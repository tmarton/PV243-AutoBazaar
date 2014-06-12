package cz.muni.fi.pv243.dto;

import java.io.Serializable;
import java.util.List;


import cz.muni.fi.pv243.enums.FuelType;
import cz.muni.fi.pv243.enums.VehicleBodyType;
import java.util.ArrayList;
import java.util.Objects;
import org.joda.time.DateTime;


public class AdvertisementDto implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	private Long id;
	private AdvertisingAccountDto advertisingAccount;
	private DateTime creationDate;
	private VehicleBrandDto brand;
	private VehicleModelDto model;
	private DateTime productionDate;
	private int engineDisplacement;
	private FuelType fuelType;
	private VehicleBodyType bodyType;
	private String description;
	private List<VehiclePhotoDto> vehiclePhotos = new ArrayList<>();	
	
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
	
	public VehicleBrandDto getBrand() {
		return brand;
	}
	
	public void setBrand(VehicleBrandDto brand) {
		this.brand = brand;
	}
	
	public VehicleModelDto getModel() {
		return model;
	}
	
	public void setModel(VehicleModelDto model) {
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
	public AdvertisingAccountDto getAdvertisingAccount() {
		return advertisingAccount;
	}
	public void setAdvertisingAccount(AdvertisingAccountDto company) {
		this.advertisingAccount = company;
	}

    public List<VehiclePhotoDto> getVehiclePhotos() {
        return vehiclePhotos;
    }

    public void setVehiclePhotos(List<VehiclePhotoDto> vehiclePhotos) {
        this.vehiclePhotos = vehiclePhotos;
    }
	
    public void addVehiclePhoto(VehiclePhotoDto vehiclePhoto) {
        vehiclePhotos.add(vehiclePhoto);
    }	

    @Override
    public String toString() {
        return "AdvertisementDto{" + "id=" + id + ", creationDate=" + creationDate + ", brand=" + brand + ", model=" + model + ", productionDate=" + productionDate + ", engineDisplacement=" + engineDisplacement + ", fuelType=" + fuelType + ", bodyType=" + bodyType + ", description=" + description + ", vehiclePhotoCount=" + vehiclePhotos.size() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final AdvertisementDto other = (AdvertisementDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
