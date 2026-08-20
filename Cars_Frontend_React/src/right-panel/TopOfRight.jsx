import RightPanelButton from "./RightPanelButton"

import AddCar from "../modals/AddCar"

import { useState } from "react"

function TopOfRight({onCarsAdded}) {
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
    return (
        <>
            <div className="top-of-right">
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
            <AddCar modalState={modalState} setModalState={setModalState} onClose={() => setModalState({ isOpen: false, type: "none" })} onCarsAdded={onCarsAdded} />
        </>
    )
}

export default TopOfRight