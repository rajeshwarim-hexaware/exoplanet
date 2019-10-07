package com.symantec.exoplanet.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Planet implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("PlanetIdentifier")
	private String planetIdentifier;

	@JsonProperty("HostStarTempK")
	private Double hostStarTempK;

	@JsonProperty("RadiusJpt")
	private Double radiusJpt;

	@JsonProperty("DiscoveryYear")
	private Integer discoveryYear;

	@JsonProperty("TypeFlag")
	private Double typeFlag;

	public String getPlanetIdentifier() {
		return planetIdentifier;
	}

	public void setPlanetIdentifier(String planetIdentifier) {
		this.planetIdentifier = planetIdentifier;
	}

	public Double getHostStarTempK() {
		return hostStarTempK;
	}

	public void setHostStarTempK(Double hostStarTempK) {
		if (hostStarTempK == null)
			this.hostStarTempK = 0.0;
		else
			this.hostStarTempK = hostStarTempK;
	}

	public Double getRadiusJpt() {
		return radiusJpt;
	}

	public void setRadiusJpt(Double radiusJpt) {
		this.radiusJpt = radiusJpt;
	}

	public Integer getDiscoveryYear() {
		return discoveryYear;
	}

	public void setDiscoveryYear(Integer discoveryYear) {
		if (discoveryYear == null)
			this.discoveryYear = 0;
		else
			this.discoveryYear = discoveryYear;
	}

	public Double getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(Double typeFlag) {
		this.typeFlag = typeFlag;
	}
}
