import TopOfRight from "./TopOfRight"
import BottomOfRight from "./BottomOfRight"
import AddCar from "../modals/AddCar"
import { useState } from "react"

function RightPanel({onCarsAdded, selectedCars, setSelectedCars}) {
   

    return (
        <div className="right-panel">
            <TopOfRight onCarsAdded={onCarsAdded} selectedCars={selectedCars} setSelectedCars={setSelectedCars}/>
            <BottomOfRight />
        </div>
    )
}

export default RightPanel