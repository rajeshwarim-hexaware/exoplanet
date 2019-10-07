package com.symantec.exoplanet.test;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.symantec.exoplanet.bean.ResponseBean;
import com.symantec.exoplanet.bean.TimeLine;
import com.symantec.exoplanet.service.ExoPlanetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExoplanetApplicationTests {

	@Autowired
	ExoPlanetService exoPlanetService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void testNoOfOrphanPlanets() {
		Integer noOfOrphanPlanets = exoPlanetService.getNoOfOrphanPlanets();
		assertEquals(Integer.valueOf(2), noOfOrphanPlanets);
	}

	@Test
	public void testHottestStarPlanetName() {
		String hottestStarPlanetName = exoPlanetService.getHottestStarPlanetName();
		assertEquals("V391 Peg b", hottestStarPlanetName);
	}

	@Test
	public void testTimeline() {
		List<TimeLine> timelineList = exoPlanetService.getTimeLine();
		assertEquals(19, timelineList.size());
		TimeLine timeLineFor2005 = timelineList.stream().filter(timeline -> timeline.getYear().equals(2005)).findAny()
				.orElse(null);
		assertEquals(Long.valueOf(1), timeLineFor2005.getSmall());
		assertEquals(Long.valueOf(3), timeLineFor2005.getMedium());
		assertEquals(Long.valueOf(0), timeLineFor2005.getLarge());
	}

	@Test
	public void testExoPlanetStatistics() {
		ClassLoader classloader = getClass().getClassLoader();
		try (InputStream getTimeLineList = new FileInputStream(
				classloader.getResource("timeLineList.json").getFile())) {
			List<TimeLine> timeLineList = objectMapper.readValue(getTimeLineList,
					new TypeReference<ArrayList<TimeLine>>() {
					});
			ResponseBean response = exoPlanetService.getExoPlanetStatistics();
			assertEquals(Integer.valueOf(2), response.getNoOfOrphanPlanets());
			assertEquals("V391 Peg b", response.getHottestStarPlanetName());
			assertEquals(19, response.getPlanetStatisticsByYear().size());

			List<TimeLine> nonEqualList = new ArrayList<>();
			timeLineList.forEach(timeline1 -> {
				response.getPlanetStatisticsByYear().forEach(timeline2 -> {
					if (timeline1.getYear().equals(timeline2.getYear())) {
						if (!timeline1.getSmall().equals(timeline2.getSmall())
								|| !timeline1.getMedium().equals(timeline2.getMedium())
								|| !timeline1.getLarge().equals(timeline2.getLarge()))
							nonEqualList.add(timeline1);
					}
				});

			});
			assertEquals(0, nonEqualList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
