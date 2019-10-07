package com.symantec.exoplanet.controller;

import static com.symantec.exoplanet.constants.ExoPlanetConstants.REST_API_HOTTEST_STAR_PLANET_NAME;
import static com.symantec.exoplanet.constants.ExoPlanetConstants.REST_API_ORPHAPLANET_COUNT;
import static com.symantec.exoplanet.constants.ExoPlanetConstants.REST_API_TIMELINE;
import static com.symantec.exoplanet.constants.ExoPlanetConstants.REST_API_EXOPLANET_STATISTICS;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symantec.exoplanet.bean.ResponseBean;
import com.symantec.exoplanet.bean.TimeLine;
import com.symantec.exoplanet.service.ExoPlanetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "exoPlanetUrl", description = "ExoPlanet Controller URLs")
public class ExoPlanetController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExoPlanetController.class);

	@Autowired
	private ExoPlanetService exoPlanetService;

	/**
	 * Get the number of orphan planets
	 * 
	 * @return response contains ResponseEntity<Integer>
	 */
	@ApiOperation(value = "Get Count of OrphanPlanets ", notes = "This methods will get the number of orphan planets (no star)", response = Integer.class, tags = {
			"ExoPlanetData" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok", response = Integer.class) })
	@GetMapping(value = REST_API_ORPHAPLANET_COUNT)
	public ResponseEntity<Integer> getNoOfOrphanPlanets() {
		LOGGER.info("ExoPlanet Service getting the number of orphan planets...");
		ResponseEntity<Integer> response = null;
		Integer noOfOrphanPlanets = exoPlanetService.getNoOfOrphanPlanets();
		if (noOfOrphanPlanets != null) {
			response = new ResponseEntity<Integer>(noOfOrphanPlanets, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;

	}

	/**
	 * Find the name of the planet Orbiting the hottest star
	 * 
	 * @return response contains ResponseEntity<String>
	 */
	@ApiOperation(value = "Find the name of the planet Orbiting the hottest star", notes = "This methods will get the  name (planet identifier) of the planet orbiting the hottest star", response = ResponseBean.class, tags = {
			"ExoPlanetData" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok", response = String.class) })
	@GetMapping(value = REST_API_HOTTEST_STAR_PLANET_NAME)
	public ResponseEntity<String> getHottestStarPlanetName() {
		LOGGER.info("ExoPlanet Service getting the name of the planet Orbiting the hotst star...");
		ResponseEntity<String> response = null;
		String hottestStarPlanetName = exoPlanetService.getHottestStarPlanetName();
		if (hottestStarPlanetName != null) {
			response = new ResponseEntity<String>(hottestStarPlanetName, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;

	}

	/**
	 * Find the Time line of the number of planets discovered per year grouped by size
	 * 
	 * @return response contains ResponseEntity<TimeLine>
	 */
	@ApiOperation(value = "Find the timeline of the number of planets discovered per year grouped by size.", notes = "This methods will get A timeline of the number of planets discovered per year grouped by size.", response = ResponseBean.class, tags = {
			"ExoPlanetData" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok", response = TimeLine.class) })
	@GetMapping(value = REST_API_TIMELINE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TimeLine>> getTimeLine() {
		LOGGER.info(
				"ExoPlanet Service getting A timeline of the number of planets discovered per year grouped by size...");
		ResponseEntity<List<TimeLine>> response = null;
		List<TimeLine> planetStatisticsByYear = exoPlanetService.getTimeLine();
		if (planetStatisticsByYear != null && !planetStatisticsByYear.isEmpty()) {
			response = new ResponseEntity<List<TimeLine>>(planetStatisticsByYear, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;

	}

	/**
	 * Get The Statistics of ExoPlanet
	 * 
	 * @return response contains ResponseEntity<ResponseBean>
	 */
	@ApiOperation(value = "Get The Statistics of ExoPlanet ie OrphanPlanets count,planet Orbiting the hottest star,timeline", notes = "This methods will get the Statistics of ExoPlanet ie OrphanPlanets count,planet Orbiting the hottest star,timeline", response = ResponseBean.class, tags = {
			"ExoPlanetData" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok", response = ResponseBean.class) })
	@GetMapping(value = REST_API_EXOPLANET_STATISTICS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBean> getExoPlanetStatistics() {
		LOGGER.info("ExoPlanet Service getting the Statistics of ExoPlanet...");
		ResponseEntity<ResponseBean> response = null;
		ResponseBean responseBean = exoPlanetService.getExoPlanetStatistics();
		if (responseBean != null) {
			response = new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;

	}

}
