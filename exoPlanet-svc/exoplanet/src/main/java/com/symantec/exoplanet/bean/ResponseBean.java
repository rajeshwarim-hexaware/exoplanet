package com.symantec.exoplanet.bean;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ResponseBean {
	private Integer noOfOrphanPlanets;
	private String hottestStarPlanetName;
	private List<TimeLine> planetStatisticsByYear;

	public Integer getNoOfOrphanPlanets() {
		return noOfOrphanPlanets;
	}

	public void setNoOfOrphanPlanets(Integer noOfOrphanPlanets) {
		this.noOfOrphanPlanets = noOfOrphanPlanets;
	}

	public String getHottestStarPlanetName() {
		return hottestStarPlanetName;
	}

	public void setHottestStarPlanetName(String hottestStarPlanetName) {
		this.hottestStarPlanetName = hottestStarPlanetName;
	}

	public List<TimeLine> getPlanetStatisticsByYear() {
		return planetStatisticsByYear;
	}

	public void setPlanetStatisticsByYear(List<TimeLine> planetStatisticsByYear) {
		this.planetStatisticsByYear = planetStatisticsByYear;
	}
}
