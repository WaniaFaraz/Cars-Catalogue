import CarCard from './CarCard.jsx'
import { useMemo, useState } from 'react'

/*
const cars = [{
  "company": "Toyota",
  "model": "Land Cruiser Prado",
  "year": 2006,
  "price": 14000000,
  "resaleValueYears": 1400000,
  "resaleValueDistance": 140000,
  "numSeats": 7,
  "drive": "AWD",
  "length": -1.0,
  "features": ["features"],
  "mileage": 140,
  "electricityMileage": "Not applicable",
  "fuelMileage": 140,
  "name": "Toyota Land Cruiser Prado 2006",
  "type": "Fuel"
}, {
  "company": "Nissan",
  "model": "Pathfinder",
  "year": 2015,
  "price": 140000000,
  "resaleValueYears": 1400000,
  "resaleValueDistance": 140000,
  "numSeats": 7,
  "drive": "AWD",
  "length": -1.0,
  "features": ["sun roof", "stereo"],
  "mileage": 140,
  "electricityMileage": "Not applicable",
  "fuelMileage": 140,
  "name": "Nissan Pathfinder 2015",
  "type": "Fuel"
}]
*/

function CarsCardArea({ cars, filters, selectedCars, setSelectedCars }) {

  const displayedCars = cars.filter((car) => {
    //company
    if ((filters.company).length > 0 && !filters.company.includes(car.company)) {
      return false;
    }
    //price
    if (filters.price.length > 0 && (car.price < filters.price[0] || car.price > filters.price[1])) {
      return false;
    }
    //seats
    if (filters.numSeats.length > 0 && !filters.numSeats.includes(car.numSeats.toString())) {
      return false;
    }
    //year
    if (filters.year > car.year) {
      return false;
    }
    //drive

    if (filters.drive.length > 0 && !(filters.drive.some(item => car.drive.includes(item)))) {
      return false;
    }
    //resaleValue after 4 years
    if (filters.resaleValueYears.length > 0 && (car.resaleValueYears < filters.resaleValueYears[0] || car.resaleValueYears > filters.resaleValueYears[1])) {
      return false;
    }
    //resaleValue after 60,000 km
    if (filters.resaleValueDistance.length > 0 &&( car.resaleValueDistance < filters.resaleValueDistance[0] || car.resaleValueDistance > filters.resaleValueDistance[1])) {
      return false;
    }
    //features
    //TODO: add logic

    return true;
  })

 

  const carsList = useMemo(
    () => {
      return displayedCars.map((car) => {
        return <CarCard key={car.name} car={car} setSelectedCars={setSelectedCars} selectedCars={selectedCars} />
      })
    }, [displayedCars]
  )




  return (
    <div className="cars-card-area" >
      {carsList}
    </div>
  )

}

export default CarsCardArea
