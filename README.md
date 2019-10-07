# exoPlanet-svc and exoPlanet-ui
## Getting Started
### Overview
exoPlanet-svc Service is a simple Spring Boot application uses Java 1.8 and Maven. On startup, the application loads the specified JSON file into memory.
exoPlanet-svc Service provides the below list of services to the user.
1.  GET /exoPlanetStatisticsGet The Statistics of ExoPlanet ie OrphanPlanets count,planet Orbiting the hotst star,timeline
2.  GET /hottestStarPlanetNameFind the name of the planet Orbiting the hottest
3.  GET /noOfOrphanPlanetsGet Count of OrphanPlanets
4.  GET /timeLineFind the timeline of the number of planets discovered in a year grouped by size.

exoPlanet-ui is a React application uses expoPlanet-svc service to build user inteface to check expoplanet statistics.

### Guide
To Run exoPlanet-svc Service:

From Commandline
1.  Clone the exoplanet git repo
    git clone https://github.com/rajeshwarim-hexaware/exoplanet.git
2. Build and start exoPlanet-svc

    cd exoplanet\exoPlanet-svc\exoplanet 
    
    mvn clean install
    
    java -jar target/exoplanet-0.0.1-SNAPSHOT.jar
    
 3. exoPlanet-svc sevice is ready to use at http://localhost:2104/api/swagger-ui.html#/
 
 From eclipse/IntelliJ
 1. The tests can be run by right clicking on the ExoplanetApplicationTests class and selecting "Run 'ExoplanetApplicationTests'"
 2. The application can be launched by right-clicking on the ExoPlanetApplication class and selecting "Run'ExoPlanetApplication'"
 3. exoPlanet-svc sevice is ready to use at http://localhost:2104/api/swagger-ui.html#/
 
 To Run exoPlanet-ui
 1. cd exoPlanet-ui\exoPlanet
 2. npm install
 3. npm start
 4. exoPlanet UI is ready to use at http://localhost:3000/
