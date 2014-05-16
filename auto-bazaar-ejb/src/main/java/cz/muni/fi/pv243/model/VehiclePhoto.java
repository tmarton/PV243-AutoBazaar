package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.net.URL;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: VehiclePhoto
 *
 */
@Entity

public class VehiclePhoto implements Serializable {

	   
	@Id
	private Long id;
	private String comment;
	private URL url;
	private static final long serialVersionUID = 1L;

	public VehiclePhoto() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}    
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}   
	public URL getUrl() {
		return this.url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
   
}
