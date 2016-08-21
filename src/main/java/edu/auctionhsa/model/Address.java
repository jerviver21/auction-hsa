package edu.auctionhsa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	
	@Size(max = 64)
	@Column(name = "address_street")
	private String addressStreet;
	@Size(max = 10)
	@Column(name = "address_number")
	private String addressNumber;
	@Size(max = 64)
	@Column(name = "address_neihborhood")
	private String addressNeihborhood;
	@Size(max = 64)
	@Column(name = "address_residential_unit")
	private String addressResidentialUnit;
	
	public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressNeihborhood() {
        return addressNeihborhood;
    }

    public void setAddressNeihborhood(String addressNeihborhood) {
        this.addressNeihborhood = addressNeihborhood;
    }

    public String getAddressResidentialUnit() {
        return addressResidentialUnit;
    }

    public void setAddressResidentialUnit(String addressResidentialUnit) {
        this.addressResidentialUnit = addressResidentialUnit;
    }

}
