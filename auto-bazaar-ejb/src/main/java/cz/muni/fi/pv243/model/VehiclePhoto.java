package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.net.URL;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: VehiclePhoto
 *
 * @author dubrouski
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "VehiclePhoto.getByAdvertisement", query = "SELECT p FROM VehiclePhoto p WHERE p.advertisement.id = :id")
})
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

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
    
    @Override
    public String toString() {
        return "VehiclePhoto{" + "id=" + id + ", comment=" + comment + ", url=" + url + ", advertisement=" + advertisement + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.comment);
        hash = 89 * hash + Objects.hashCode(this.url);
        hash = 89 * hash + Objects.hashCode(this.advertisement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VehiclePhoto other = (VehiclePhoto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.advertisement, other.advertisement)) {
            return false;
        }
        return true;
    }
   
    
}
