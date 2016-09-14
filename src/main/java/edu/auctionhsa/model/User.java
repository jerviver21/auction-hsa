package edu.auctionhsa.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Jerson Viveros
 */
@Entity
@org.hibernate.envers.Audited
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 64)
    private String name;
    
    @NotNull
    @Size(min = 1, max = 64)
    @Column(unique=true)
    private String numId;
    
    @NotNull
    @Size(max = 64)
    @Column(unique=true)
    private String usr;
    
    @NotNull
    @Size(max = 1024)
    private String pwd;
    
    @JoinColumn(name = "id_divipola", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Divipola city;
    
    @Embedded
    private Address address;
    
    
    private Boolean isUsrPremium;
    
    //Auditory Fields
    @Size(max = 64)
    private String audUsrModified = "DEFAULT";

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumId() {
        return numId;
    }

    public void setNumId(String numId) {
        this.numId = numId;
    }

    

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Boolean getIsUsrPremium() {
        return isUsrPremium;
    }

    public void setIsUsrPremium(Boolean isUsrPremium) {
        this.isUsrPremium = isUsrPremium;
    }

    public String getAudUsrModified() {
        return audUsrModified;
    }

    public void setAudUsrModified(String audUsrModified) {
        this.audUsrModified = audUsrModified;
    }


    public Divipola getCity() {
        return city;
    }

    public void setCity(Divipola idDivipola) {
        this.city = idDivipola;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Users[ id=" + id + " ]";
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
    
}
