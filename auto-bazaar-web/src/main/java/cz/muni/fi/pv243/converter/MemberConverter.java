package cz.muni.fi.pv243.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import cz.muni.fi.pv243.dto.MemberDto;
import cz.muni.fi.pv243.dto.VehicleBrandDto;
import cz.muni.fi.pv243.services.MemberService;
import cz.muni.fi.pv243.services.VehicleBrandService;

@FacesConverter("memberConverter")
public class MemberConverter implements Converter {
	
	@Inject
	private MemberService memberService;

	@Override
	public MemberDto getAsObject(FacesContext context, UIComponent component, String newValue) {
		if (newValue.isEmpty()) {
	        return null;
	    }
		
		return memberService.getByID(Long.parseLong(newValue));

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		MemberDto member = (MemberDto) arg2;
		return member.getId().toString();
	}

}
