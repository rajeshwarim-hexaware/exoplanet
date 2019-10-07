package com.symantec.exoplanet.component;

import static com.symantec.exoplanet.constants.ExoPlanetConstants.MEDIUM_PLANET_SIZE;
import static com.symantec.exoplanet.constants.ExoPlanetConstants.NOT_EXIST;
import static com.symantec.exoplanet.constants.ExoPlanetConstants.ORPHAN_TYPE_FLAG;
import static com.symantec.exoplanet.constants.ExoPlanetConstants.SMALL_PLANET_SIZE;
import static com.symantec.exoplanet.constants.ExoPlanetConstants.ZERO;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.symantec.exoplanet.bean.Planet;
import com.symantec.exoplanet.bean.ResponseBean;
import com.symantec.exoplanet.bean.TimeLine;
import com.symantec.exoplanet.controller.ExoPlanetController;
import com.symantec.exoplanet.exception.ExoPlanetServiceException;

@Component
public class ApplicationStartupLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExoPlanetController.class);

	@Value("${exoplanet.json.data.url}")
	private String jsonDataUrl;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ResponseBean responseBean;

	@PostConstruct
	public void init() {
		try {
			LOGGER.info("ExoPlanet Service Reading Data from URL");
			URL url = new URL(jsonDataUrl);
			List<Planet> exoPlanetlist = objectMapper.readValue(url, new TypeReference<ArrayList<Planet>>() {
			});
			responseBean.setHottestStarPlanetName(getHottestStarPlanetName(exoPlanetlist));
			responseBean.setNoOfOrphanPlanets(getNoOfOrphanPlanets(exoPlanetlist));
			responseBean.setPlanetStatisticsByYear(getPlanetStatisticsByYear(exoPlanetlist));
			exoPlanetlist.clear();
			exoPlanetlist = null;
		} catch (Exception e) {
			LOGGER.warn("Something went wrong while reading Exoplanet data from url");
			throw new ExoPlanetServiceException("Error While reading Exoplanet data from url" + e);

		}
	}

	private List<TimeLine> getPlanetStatisticsByYear(List<Planet> exoPlanetlist) {
		LOGGER.info("ExoPlanet Service updating timeLineMap for small,medium and large Size planet");
		Map<Integer, TimeLine> timelineByYearMap = new HashMap<>();
		updateTimeLineMapForSmallSize(exoPlanetlist, timelineByYearMap);
		updateTimeLineMapForMediumSize(exoPlanetlist, timelineByYearMap);
		updateTimeLineMapForLargeSize(exoPlanetlist, timelineByYearMap);
		List<TimeLine> timeLineList = timelineByYearMap.values().stream().collect(Collectors.toList());
		LOGGER.info("TimeLine list size: " +timeLineList.size());
		return timeLineList;
	}

	private void updateTimeLineMapForSmallSize(List<Planet> exoPlanetlist, Map<Integer, TimeLine> timelineByYearMap) {
		try {
			LOGGER.info("ExoPlanet Service updating timeLineMap for SmallSize planet");
			Predicate<Planet> smallPredicate = smallPlanet -> smallPlanet.getDiscoveryYear() != ZERO
					&& smallPlanet.getRadiusJpt()!=null && smallPlanet.getRadiusJpt()< SMALL_PLANET_SIZE;

			Map<Integer, Long> smallPlanetCountByYearMap = getTimeLineMapByPredicate(exoPlanetlist, smallPredicate);

			smallPlanetCountByYearMap.forEach((key, value) -> {

				if (timelineByYearMap.get(key) == null)
					timelineByYearMap.put(key, new TimeLine(key, value, Long.valueOf(0), Long.valueOf(0)));
				else {
					TimeLine timeline = timelineByYearMap.get(key);
					timeline.setSmall(value);
					timelineByYearMap.put(key, timeline);
				}
			});
		} catch (Exception e) {
			LOGGER.warn("Something went wrong while updating timeLineMap for SmallSize planet");
			throw new ExoPlanetServiceException("Error while updating timeLineMap for SmallSize planet" + e);

		}
	}

	private void updateTimeLineMapForMediumSize(List<Planet> exoPlanetlist, Map<Integer, TimeLine> timelineByYearMap) {
		try {
			LOGGER.info("ExoPlanet Service updating timeLineMap for Medium planet");
			Predicate<Planet> mediumPredicate = mediumPlanet -> mediumPlanet.getDiscoveryYear() != ZERO
					&& mediumPlanet.getRadiusJpt()!=null
					&& mediumPlanet.getRadiusJpt() > SMALL_PLANET_SIZE
					&& mediumPlanet.getRadiusJpt() < MEDIUM_PLANET_SIZE;

			Map<Integer, Long> mediumPlanetCountByYearMap = getTimeLineMapByPredicate(exoPlanetlist, mediumPredicate);

			mediumPlanetCountByYearMap.forEach((key, value) -> {

				if (timelineByYearMap.get(key) == null)
					timelineByYearMap.put(key, new TimeLine(key, Long.valueOf(0), value, Long.valueOf(0)));
				else {
					TimeLine timeline = timelineByYearMap.get(key);
					timeline.setMedium(value);
					timelineByYearMap.put(key, timeline);
				}
			});
		} catch (Exception e) {
			LOGGER.warn("Something went wrong while updating timeLineMap for Medium planet");
			throw new ExoPlanetServiceException("Error while updating timeLineMap for Medium planet" + e);

		}
	}

	private void updateTimeLineMapForLargeSize(List<Planet> exoPlanetlist, Map<Integer, TimeLine> timelineByYearMap) {
		try {
			LOGGER.info("ExoPlanet Service updating timeLineMap for Large planet");

			Predicate<Planet> largePredicate = largePlanet -> largePlanet.getDiscoveryYear() != ZERO
					&& largePlanet.getRadiusJpt()!=null
					&& largePlanet.getRadiusJpt() > MEDIUM_PLANET_SIZE;
			Map<Integer, Long> largePlanetCountByYearMap = getTimeLineMapByPredicate(exoPlanetlist, largePredicate);
			largePlanetCountByYearMap.forEach((key, value) -> {

				if (timelineByYearMap.get(key) == null)
					timelineByYearMap.put(key, new TimeLine(key, Long.valueOf(0), Long.valueOf(0), value));
				else {
					TimeLine timeline = timelineByYearMap.get(key);
					timeline.setLarge(value);
					timelineByYearMap.put(key, timeline);
				}
			});
		} catch (Exception e) {
			LOGGER.warn("Something went wrong while updating timeLineMap for Large planet");
			throw new ExoPlanetServiceException("Error while updating timeLineMap for Large planet" + e);

		}
	}

	private Map<Integer, Long> getTimeLineMapByPredicate(List<Planet> exoPlanetlist, Predicate<Planet> predicate) {
		try {
			LOGGER.info("ExoPlanet Service finding the timeline of the number of planets discovered per year grouped by size.");
			Map<Integer, Long> planetCountByYearMap = exoPlanetlist.stream().filter(predicate)
					.collect(Collectors.groupingBy(Planet::getDiscoveryYear, Collectors.counting()));
			return planetCountByYearMap;
		} catch (Exception e) {
			LOGGER.warn("Something went wrong while finding the timeline of the number of planets discovered per year grouped by size.");
			throw new ExoPlanetServiceException("Error while finding the timeline of the number of planets discovered per year grouped by size."+ e);

		}
	}

	private String getHottestStarPlanetName(List<Planet> exoPlanetlist) {
		LOGGER.info("ExoPlanet Service finding planet Orbiting the hotst star");
		try {
			Optional<Planet> option = exoPlanetlist.stream().max(Comparator.comparing(Planet::getHostStarTempK));
			if (!option.isPresent())
				return NOT_EXIST;
			else
				return option.get().getPlanetIdentifier();
		} catch (Exception e) {
			LOGGER.warn("Something went wrong while finding planet Orbiting the hotst star");
			throw new ExoPlanetServiceException("Error finding planet Orbiting the hotst star" + e);

		}
	}

	private Integer getNoOfOrphanPlanets(List<Planet> exoPlanetlist) {
		try {
			LOGGER.info("ExoPlanet Service finding the number of orphan planets");
			List<Planet> orphanPlanetList = exoPlanetlist.stream()
					.filter(planet -> planet.getTypeFlag().equals(ORPHAN_TYPE_FLAG)).collect(Collectors.toList());
			if (orphanPlanetList != null && !orphanPlanetList.isEmpty())
				return orphanPlanetList.size();
			else
				return ZERO;
		} catch (Exception e) {
			LOGGER.warn("Something went wrong while finding the number of orphan planets");
			throw new ExoPlanetServiceException("Error finding the number of orphan planets" + e);

		}
	}

	public ResponseBean getResponseBean() {
		return responseBean;
	}

}
