package com.tap.servlets;

public class Vendorclass {
	String vendorName;
	int bankAccountNo;
	String bankName;
	String addressLine1;
	String addressLine2;
	String city;
	String country;
	int zipCode;

	
	public Vendorclass() {
		super();
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public int getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(int bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public Vendorclass(String vendorName, int bankAccountNo, String bankName) {
		
		this.vendorName = vendorName;
		this.bankAccountNo = bankAccountNo;
		this.bankName = bankName;
	}

	public Vendorclass(String vendorName, int bankAccountNo, String bankName, String addressLine1, String addressLine2,
			String city, String country, int zipCode) {
	
		this.vendorName = vendorName;
		this.bankAccountNo = bankAccountNo;
		this.bankName = bankName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}

	public Vendorclass(String vendorName, String bankName, String addressLine1, String addressLine2, String city,
			String country, int zipCode) {
		super();
		this.vendorName = vendorName;
		this.bankName = bankName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}

	
	
	

}
