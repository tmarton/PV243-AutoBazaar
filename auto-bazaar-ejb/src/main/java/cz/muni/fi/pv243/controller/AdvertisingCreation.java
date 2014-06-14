package cz.muni.fi.pv243.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import cz.muni.fi.pv243.dto.AdvertisementDto;
import cz.muni.fi.pv243.dto.VehicleBrandDto;
import cz.muni.fi.pv243.services.AdvertisementService;
import cz.muni.fi.pv243.services.VehicleBrandService;

@Stateful
@Model
public class AdvertisingCreation {
	@Inject
	private Logger log;

	@Inject
	private Event<AdvertisementDto> advertEventSrc;

	@Inject
	private AdvertisementService adService;
	
	@Inject
	private VehicleBrandService brandService;

	private AdvertisementDto newAdvert;

	public AdvertisementDto getNewAdvert() {
		return newAdvert;
	}

	public void create() {
		log.info("Creating " + newAdvert.getBrand() + " " + newAdvert.getModel() + ", "
				+ newAdvert.getProductionDate());
		adService.save(newAdvert);
		advertEventSrc.fire(newAdvert);
		initNewAd();
	}

	@PostConstruct
	public void initNewAd() {
		newAdvert = new AdvertisementDto();
	}

}
