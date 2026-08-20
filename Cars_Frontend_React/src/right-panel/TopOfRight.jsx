import RightPanelButton from "./RightPanelButton"
import AddCar from "../modals/AddCar"

import { useState } from "react"

import api from '../../axiosConfig'

function TopOfRight({onCarsAdded, selectedCars, setSelectedCars}) {
    //add car button state:  to make the other buttons appear
    const [buttonsState, setButtonsState] = useState(false);

    function addCarOnClick() {
        setButtonsState(!buttonsState);
    }

    //add car modal state 
    const [modalState, setModalState] = useState({
        isOpen: false, //whether or not the modal is open
        type: "none" //type of car in modal
    })

    function addCarTypeHandler(type) {
        setModalState({
            isOpen: true,
            type: type
        })
    }

    async function deleteCarOnClick() {
        const deleteCars = confirm("Are you sure you would like to delete all the selected cars?");
        if(deleteCars) {
            await api.post('delete-cars', {carNames: selectedCars})
            onCarsAdded();
            console.log("Car deleted successfully");
            
        }
        
    }
    return (
        <>
            <div className="top-of-right">
                {(selectedCars && selectedCars.length > 0) && <RightPanelButton innerText="Delete cars" className="right-panel-button delete-button" selectedCars={selectedCars} setSelectedCars={setSelectedCars} onClick={deleteCarOnClick} />}
                <RightPanelButton innerText="Edit Cars" className="right-panel-button" />
                <RightPanelButton innerText="Add Car" className="right-panel-button" onClick={addCarOnClick} />
                {buttonsState &&
                    (<>
                        <RightPanelButton innerText="Fuel Car" id="fuel-car" className="right-panel-button temp" onClick={() => addCarTypeHandler("Fuel")} />
                        <RightPanelButton innerText="PHEV" id="phev" className="right-panel-button temp" onClick={() => addCarTypeHandler("PHEV")} />
                        <RightPanelButton innerText="HEV" id="hev" className="right-panel-button temp" onClick={() => addCarTypeHandler("HEV")} />
                    </>)
                }

                <span className="underline under-add"></span>

            </div>
            <AddCar modalState={modalState} setModalState={setModalState} onClose={() => setModalState({ isOpen: false, type: "none"})} onCarsAdded={onCarsAdded} />
        </>
    )
}

export default TopOfRight