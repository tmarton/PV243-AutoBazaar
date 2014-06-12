package cz.muni.fi.pv243.dto;

import java.io.Serializable;
import java.net.URL;
import java.util.Objects;

public class VehiclePhotoDto implements Serializable {
	
	private static final long serialVersionUID = 8L;
	   
	private Long id;
	private String comment;
	private URL url;
	private AdvertisementDto advertisement;
	
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

    public AdvertisementDto getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(AdvertisementDto advertisement) {
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
        final VehiclePhotoDto other = (VehiclePhotoDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
   
    
}
