package edu.auctionhsa.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Jerson Viveros
 */
@Entity
@org.hibernate.envers.Audited
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Version
	private long version;
    
    @NotNull
    @Size(min = 1, max = 64)
    @Column(unique=true)
    private String name;
    
    @Size(max = 1024)
    private String description;
    
    @NotNull
    private boolean isPublished = false;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Future(message="The action end date must be in the future")
    private Date auctionEnd;
    
    @NotNull
    @Range(min=0)
    private Long initialPrice = 0L;
    
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User seller;
    
    @ElementCollection
	@CollectionTable(name="images", joinColumns=@JoinColumn(name="id_item"))
    @Embedded
    private Set<Image> images = new HashSet<>();
    
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private Set<Bid> bids = new TreeSet<>();
    
    @org.hibernate.annotations.Formula(
        "coalesce((select max(b.AMOUNT) from BID b where b.id_item = ID), 0)"
    )
    @org.hibernate.envers.NotAudited
	private Long maxBidAmount;
    
    //Auditory 
   
    @Size(max = 64)
    @Column(name = "aud_usr_modified")
    @JsonIgnore
    private String audUsrModified = "DEFAULT";

    public Item() {
    }

    public Item(Long id) {
        this.id = id;
    }

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public Long getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Long initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getAudUsrModified() {
        return audUsrModified;
    }

    public void setAudUsrModified(String audUsrModified) {
        this.audUsrModified = audUsrModified;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> imagesCollection) {
        this.images = imagesCollection;
    }

    public Set<Bid> getBidsCollection() {
        return bids;
    }

    public void setBidsCollection(Set<Bid> bidsCollection) {
        this.bids = bidsCollection;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User idUser) {
        this.seller = idUser;
    }
    
    public Long getMaxBidAmount() {
		return maxBidAmount;
	}
    
    public boolean isValidBidAmount(Long newBidAmount) {
        return newBidAmount != null && newBidAmount.compareTo(getInitialPrice()) == 1
            && (maxBidAmount == null || newBidAmount.compareTo(maxBidAmount) == 1);
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Items[ id=" + id + " ]";
    }

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	

    
}
