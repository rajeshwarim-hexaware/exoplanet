package com.symantec.exoplanet.service;

import java.util.List;

import com.symantec.exoplanet.bean.ResponseBean;
import com.symantec.exoplanet.bean.TimeLine;

public interface ExoPlanetService {

	Integer getNoOfOrphanPlanets();

	String getHottestStarPlanetName();

	List<TimeLine> getTimeLine();

	ResponseBean getExoPlanetStatistics();

}
