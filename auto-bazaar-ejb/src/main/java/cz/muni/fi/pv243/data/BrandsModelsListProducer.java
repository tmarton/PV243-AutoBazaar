package cz.muni.fi.pv243.data;

import cz.muni.fi.pv243.dto.MemberDto;
import cz.muni.fi.pv243.dto.VehicleBrandDto;
import cz.muni.fi.pv243.dto.VehicleModelDto;
import cz.muni.fi.pv243.model.Member;
import cz.muni.fi.pv243.services.VehicleBrandService;
import cz.muni.fi.pv243.services.VehicleModelService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
public class BrandsModelsListProducer {
	@Inject
	private VehicleBrandService brandService;
	
	@Inject
	private VehicleModelService modelService;

	private List<VehicleBrandDto> brands;
	
	private List<VehicleModelDto> models;

	// @Named provides access the return value via the EL variable name
	// "members" in the UI (e.g.,
	// Facelets or JSP view)
	@Produces
	@Named
	public List<VehicleBrandDto> getBrands() {
		return brands;
	}
	
	@Produces
	@Named
	public List<VehicleModelDto> getModels() {
		return models;
	}

	public void onBrandsListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final VehicleBrandDto brand) {
		retrieveLists();
	}
	
	public void onModelsListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final VehicleModelDto model) {
		retrieveLists();
	}

	@PostConstruct
	public void retrieveLists() {
		brands = brandService.getAll();
		models = modelService.getAll();
	}
}
