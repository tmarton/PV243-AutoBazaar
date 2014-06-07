package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.net.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: VehiclePhoto
 *
 * @author dubrouski
 */
@Entity
public class VehiclePhoto implements Serializable {
	
	private static final long serialVersionUID = 8L;
	   
	@Id
	@GeneratedValue
	private Long id;
	
	private String comment;
	
	@NotNull
	private URL url;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="advertisement_id")
	private Advertisement advertisement;
	

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
