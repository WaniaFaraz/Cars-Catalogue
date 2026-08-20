//import components
//import './CarsArea.css'

import FilterDisplayLine from './filter-display/FilterDisplayLine.jsx'
import CarsCardArea from './cars-card-area/CarsCardArea.jsx'



function CarsArea({cars, filters, selectedCars, setSelectedCars}) {

    
    return (
        <div className="cars-area">
        <FilterDisplayLine />
        <CarsCardArea cars={cars} filters={filters} selectedCars={selectedCars} setSelectedCars={setSelectedCars} />
        </div>
    )
}

export default CarsArea