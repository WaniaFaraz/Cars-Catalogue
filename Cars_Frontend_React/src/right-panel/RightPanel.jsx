import TopOfRight from "./TopOfRight"
import BottomOfRight from "./BottomOfRight"
import AddCar from "../modals/AddCar"
import { useState } from "react"

function RightPanel() {
    //add car modal controller
    const [modalState, setModalState] = useState({
        isOpen: false, //whether or not the modal is open
        type: "none" //type of car in modal
    });

    return (
        <div className="right-panel">
            <TopOfRight modalState={modalState} setModalState={setModalState} />
            {modalState.isOpen && <AddCar modalState={modalState} setModalState={setModalState}  />}
            <BottomOfRight />
        </div>
    )
}

export default RightPanel