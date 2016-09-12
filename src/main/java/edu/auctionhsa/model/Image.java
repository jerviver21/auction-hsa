package edu.auctionhsa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jerson Viveros
 */
@Embeddable
public class Image implements Serializable {

    
    @NotNull
    @Size(min = 1, max = 1024)
    private String path;

    @Size(max = 64)
    private String audUsrModified = "DEFAULT";
    
    private Boolean selected = false;
    

    public Image() {
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAudUsrModified() {
        return audUsrModified;
    }

    public void setAudUsrModified(String audUsrModified) {
        this.audUsrModified = audUsrModified;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (path != null ? path.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.path == null && other.path != null) || (this.path != null && !this.path.equals(other.path))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Images[ id=" + path + " ]";
    }


	public Boolean getSelected() {
		return selected;
	}


	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
    
}
