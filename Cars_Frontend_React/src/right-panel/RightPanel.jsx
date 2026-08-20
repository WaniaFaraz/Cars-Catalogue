import TopOfRight from "./TopOfRight"
import BottomOfRight from "./BottomOfRight"
import AddCar from "../modals/AddCar"
import { useState } from "react"

function RightPanel({onCarsAdded}) {
   

    return (
        <div className="right-panel">
            <TopOfRight onCarsAdded={onCarsAdded}/>
            <BottomOfRight />
        </div>
    )
}

export default RightPanel