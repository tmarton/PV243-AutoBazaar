package cz.muni.fi.pv243.controller;

import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import cz.muni.fi.pv243.dto.VehicleBrandDto;
import cz.muni.fi.pv243.dto.VehicleModelDto;
import cz.muni.fi.pv243.services.VehicleBrandService;
import cz.muni.fi.pv243.services.VehicleModelService;

@Stateful
@Model
public class ModelsManagement {
	@Inject
	private Logger log;

	@Inject
	private Event<VehicleModelDto> modelEventSrc;

	@Inject
	private VehicleModelService modelService;

	private VehicleModelDto newModel;
	
	public VehicleModelDto getNewModel() {
		return newModel;
	}

	public void create() {
		log.info("Registering " + newModel.getBrand().getName() + " " + newModel.getName());
		modelService.save(newModel);
		modelEventSrc.fire(newModel);
		initNewBrand();
	}

	@PostConstruct
	public void initNewBrand() {
		newModel = new VehicleModelDto();
	}
}
