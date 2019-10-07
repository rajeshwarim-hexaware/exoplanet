package com.symantec.exoplanet.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symantec.exoplanet.bean.ResponseBean;
import com.symantec.exoplanet.bean.TimeLine;
import com.symantec.exoplanet.component.ApplicationStartupLoader;
import com.symantec.exoplanet.exception.ExoPlanetServiceException;
import com.symantec.exoplanet.service.ExoPlanetService;

@Service
public class ExoPlanetServiceImpl implements ExoPlanetService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExoPlanetServiceImpl.class);

	@Autowired
	private ApplicationStartupLoader startupLoader;

	@Override
	public Integer getNoOfOrphanPlanets() {
		Integer noOfOrphanPlanets = null;
		try {
			noOfOrphanPlanets = startupLoader.getResponseBean().getNoOfOrphanPlanets();
		} catch (Exception e) {
			LOGGER.warn("Something went wrong when getting OrphanPlanet count");
			throw new ExoPlanetServiceException(ExoPlanetServiceException.EXOPLANET_NO_DATAFOUND_EXCEPTION + e);
		}
		return noOfOrphanPlanets;
	}

	@Override
	public String getHottestStarPlanetName() {

		String hottestStarPlanetName = null;
		try {
			hottestStarPlanetName = startupLoader.getResponseBean().getHottestStarPlanetName();
		} catch (Exception e) {
			LOGGER.warn("Something went wrong when getting Hottest Star Planet");
			throw new ExoPlanetServiceException(ExoPlanetServiceException.EXOPLANET_NO_DATAFOUND_EXCEPTION + e);
		}
		return hottestStarPlanetName;
	}

	@Override
	public List<TimeLine> getTimeLine() {
		List<TimeLine> timeLine = null;
		try {
			timeLine = startupLoader.getResponseBean().getPlanetStatisticsByYear();
		} catch (Exception e) {
			LOGGER.warn("Something went wrong when getting TimeLine");
			throw new ExoPlanetServiceException(ExoPlanetServiceException.EXOPLANET_NO_DATAFOUND_EXCEPTION + e);
		}
		return timeLine;
	}

	@Override
	public ResponseBean getExoPlanetStatistics() {
		ResponseBean responseBean = null;
		try {
			responseBean = startupLoader.getResponseBean();
		} catch (Exception e) {
			LOGGER.warn("Something went wrong when getting ExoPlanetStatistics");
			throw new ExoPlanetServiceException(ExoPlanetServiceException.EXOPLANET_NO_DATAFOUND_EXCEPTION + e);
		}
		return responseBean;
	}

}
