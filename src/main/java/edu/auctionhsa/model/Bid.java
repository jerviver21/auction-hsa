package edu.auctionhsa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

/**
 * @author Jerson Viveros
 */

@Entity
@org.hibernate.envers.Audited
@org.hibernate.annotations.Immutable
public class Bid implements Serializable, Comparable<Bid>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Column(updatable=false)
    private Long amount;
    
    @Column(updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @JoinColumn(name = "id_item", referencedColumnName = "id")
    @ManyToOne(optional =false, fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private Item item;
    
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional =false, fetch = FetchType.LAZY, cascade= CascadeType.MERGE)
    private User user;
    

    public Bid() {
    }

    public Bid(Long amount, Item item) {
        this.item = item;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Bids[ id=" + id + " ]";
    }

	@Override
	public int compareTo(Bid o) {
		return this.getAmount().compareTo(o.getAmount());
	}
    
}
