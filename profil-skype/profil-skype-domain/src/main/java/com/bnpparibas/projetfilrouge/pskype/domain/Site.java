package com.bnpparibas.projetfilrouge.pskype.domain;

/**
 * Liste des sites physiques avec leur adresse
 * Cette classe permet de situer physiquement un collaborateur
 * @author Judicaël
 * @version V0.1
 *
 */
public class Site {
	private String siteCode;
	private String siteName;
	private String siteAddress;
	private String sitePostalCode;
	private String siteCity;
	
	public Site() {
		
	}
	public Site(String code,String name,String address,String postalCode, String city) {
		this.siteCode=code;
		this.siteName=name;
		this.siteAddress=address;
		this.sitePostalCode=postalCode;
		this.siteCity=city;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public String getSitePostalCode() {
		return sitePostalCode;
	}

	public String getSiteCity() {
		return siteCity;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	public void setSitePostalCode(String sitePostalCode) {
		this.sitePostalCode = sitePostalCode;
	}

	public void setSiteCity(String siteCity) {
		this.siteCity = siteCity;
	}
	
}
