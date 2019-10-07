import React from 'react';
import './App.css';
import ReactTable from "react-table";
import "react-table/react-table.css";

const { useState, useEffect } = React;
function App() {
  const [data, setData] = useState({
    loading: false,
    data: null,
  });

  useEffect(() => {

    setData({ loading: true });
    fetch('/api/exoPlanetStatistics')
      .then(resp => resp.json())
      .then(data => setData(data));
  }, []);

  const columns = [{
    Header: 'Year',
    accessor: 'year',
    headerStyle: { whiteSpace: 'unset' },
    style: { whiteSpace: 'unset' }
  }, {
    Header: 'Small',
    accessor: 'small',
    headerStyle: { whiteSpace: 'unset' },
    style: { whiteSpace: 'unset' }
  }, {
    Header: 'Medium',
    accessor: 'medium',
    headerStyle: { whiteSpace: 'unset' },
    style: { whiteSpace: 'unset' }
  }, {
    accessor: 'large',
    Header: 'Large',
    headerStyle: { whiteSpace: 'unset' },
    style: { whiteSpace: 'unset' }
  }]
  return (
    <div>
      < div style={{marginLeft: "500px",color: "darkslateblue"}}><h1>Exoplanet Statistics</h1></div>
      <div style={{marginLeft: "350px", color: "darkgray"}}><h5>Orphan planets Count:  <span style={{color: "black"}}> {data.noOfOrphanPlanets}</span> </h5>
        <h5>The planet orbiting the hottest star: <span style={{color: "black"}}>{data.hottestStarPlanetName}</span></h5>
        <h5 style={{color: "darkgray"}}>Planets discovered:</h5></div>
      <div>
        <ReactTable
          data={data.planetStatisticsByYear}
          columns={columns}
          defaultPageSize={10}
          pageSizeOptions={[10,3, 6]}
          className="-striped -highlight"
        />
      </div>
    </div>
  );
}

export default App;
