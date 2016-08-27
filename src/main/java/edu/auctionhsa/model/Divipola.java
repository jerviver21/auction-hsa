package edu.auctionhsa.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jerson Viveros
 */
@Entity
@org.hibernate.envers.Audited
public class Divipola implements Serializable {

    @Id
    private Integer id;

    @NotNull
    @Size(min = 1, max = 64)
    private String name;
    
    @JoinColumn(name = "id_divipola", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Divipola department;

    public Divipola() {
    }

    public Divipola(Integer id) {
        this.id = id;
    }

    public Divipola(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Divipola getIdDivipola() {
        return department;
    }

    public void setIdDivipola(Divipola idDivipola) {
        this.department = idDivipola;
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
        if (!(object instanceof Divipola)) {
            return false;
        }
        Divipola other = (Divipola) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Divipola[ id=" + id + " ]";
    }
    
}
