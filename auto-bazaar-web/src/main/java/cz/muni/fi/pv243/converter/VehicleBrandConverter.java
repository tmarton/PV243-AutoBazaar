package cz.muni.fi.pv243.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import cz.muni.fi.pv243.dto.VehicleBrandDto;
import cz.muni.fi.pv243.services.VehicleBrandService;

@FacesConverter("vehicleBrandConverter")
public class VehicleBrandConverter implements Converter {
	
	@Inject
	private VehicleBrandService brandService;

	@Override
	public VehicleBrandDto getAsObject(FacesContext context, UIComponent component, String newValue) {
		if (newValue.isEmpty()) {
	        return null;
	    }
		
		return brandService.getByID(Long.parseLong(newValue));

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		VehicleBrandDto brand = (VehicleBrandDto) arg2;
		return brand.getId().toString();
	}

}
