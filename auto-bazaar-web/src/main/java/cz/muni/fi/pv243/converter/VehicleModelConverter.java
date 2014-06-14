package cz.muni.fi.pv243.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import cz.muni.fi.pv243.dto.VehicleBrandDto;
import cz.muni.fi.pv243.dto.VehicleModelDto;
import cz.muni.fi.pv243.services.VehicleBrandService;
import cz.muni.fi.pv243.services.VehicleModelService;

@FacesConverter("vehicleModelConverter")
public class VehicleModelConverter implements Converter {

	@Inject
	private VehicleModelService modelService;

	@Override
	public VehicleModelDto getAsObject(FacesContext context,
			UIComponent component, String newValue) {
		if (newValue.isEmpty()) {
			return null;
		}
		return modelService.getByID(Long.parseLong(newValue));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		VehicleModelDto model = (VehicleModelDto) arg2;
		return model.getId().toString();
	}

}
