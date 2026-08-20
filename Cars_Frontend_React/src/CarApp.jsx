import { useState } from 'react'
import CarCard from './cars-card-area/CarCard.jsx'
import MainArea from './MainArea.jsx'
import AddCar from './modals/AddCar.jsx'

function CarApp() {
  

  return (
    <>
    <div className="top-bar"></div>
    <MainArea />
    <AddCar />
    </>
  )
}

export default CarApp
