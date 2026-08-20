//import components
//import './CarsArea.css'

import FilterDisplayLine from './filter-display/FilterDisplayLine.jsx'
import CarsCardArea from './cars-card-area/CarsCardArea.jsx'



function CarsArea({cars, filters}) {

    
    return (
        <div className="cars-area">
        <FilterDisplayLine />
        <CarsCardArea cars={cars} filters={filters} />
        </div>
    )
}

export default CarsArea