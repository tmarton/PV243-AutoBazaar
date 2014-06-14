package cz.muni.fi.pv243.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import cz.muni.fi.pv243.dao.AdvertisingAccountDao;
import cz.muni.fi.pv243.dto.AdvertisingAccountDto;
import cz.muni.fi.pv243.dto.CompanyInfoDto;
import cz.muni.fi.pv243.dto.VehicleModelDto;
import cz.muni.fi.pv243.services.AdvertisingAccountService;
import cz.muni.fi.pv243.services.CompanyInfoService;
import cz.muni.fi.pv243.services.VehicleModelService;

@Stateful
@Model
public class AccountManagement {
	@Inject
	private Logger log;

	@Inject
	private Event<AdvertisingAccountDto> accountEventSrc;
	@Inject
	private Event<CompanyInfoDto> companyInfoEventSrc;

	@Inject
	private AdvertisingAccountService accountService;
	
	@Inject
	private CompanyInfoService cInfoService;

	private AdvertisingAccountDto newAccount;
	
	private CompanyInfoDto newInfo;
	
	public AdvertisingAccountDto getNewAccount() {
		return newAccount;
	}
	
	public CompanyInfoDto getNewInfo() {
		return newInfo;
	}

	public void create() {
		log.info("Registering " + newInfo.getEmail());
		cInfoService.save(newInfo);
		accountService.save(newAccount);		
		accountEventSrc.fire(newAccount);
		companyInfoEventSrc.fire(newInfo);
		initNewAccountAndInfo();
	}

	@PostConstruct
	public void initNewAccountAndInfo() {
		newAccount = new AdvertisingAccountDto();
		newInfo = new CompanyInfoDto();
	}
}
