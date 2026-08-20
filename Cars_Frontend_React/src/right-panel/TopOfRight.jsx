import RightPanelButton from "./RightPanelButton"
import AddCar from "../modals/AddCar"

function addFuelHandler() {
    setModalState(()=>{
        return {
            isOpen: true,
            type: "Fuel"
        }
    })
}

function TopOfRight({modalState, setModalState}) {

    return (
        <>
        <div className="top-of-right">
            <RightPanelButton innerText="Edit Cars" className="right-panel-button" />
            <RightPanelButton innerText="Add Car" className="right-panel-button" />
            <RightPanelButton innerText="Fuel Car" id="fuel-car" className="right-panel-button temp" onclick={addFuelHandler} />
            <RightPanelButton innerText="PHEV" id="phev" className="right-panel-button temp" />
            <RightPanelButton innerText="HEV" id="hev" className="right-panel-button temp" />
            <span className="underline under-add"></span>

        </div>
        <AddCar modalState={modalState} setModalState={setModalState} />
        </>
    )
}

export default TopOfRight