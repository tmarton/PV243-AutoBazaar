package cz.muni.fi.pv243.controller;

import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import cz.muni.fi.pv243.dto.VehicleBrandDto;
import cz.muni.fi.pv243.services.VehicleBrandService;

@Stateful
@Model
public class BrandsManagement {
	@Inject
	private Logger log;

	@Inject
	private Event<VehicleBrandDto> brandEventSrc;

	@Inject
	private VehicleBrandService brandService;

	private VehicleBrandDto newBrand;
	
	public VehicleBrandDto getNewBrand() {
		return newBrand;
	}

	public void create() {
		log.info("Registering " + newBrand.getName());
		brandService.save(newBrand);
		brandEventSrc.fire(newBrand);
		initNewBrand();
	}

	@PostConstruct
	public void initNewBrand() {
		newBrand = new VehicleBrandDto();
	}
}
