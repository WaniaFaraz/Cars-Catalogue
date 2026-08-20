import ButtonArea from "./ButtonArea"
import TextInput from "./TextInput"
import Dropdown from "./Dropdown"

function AddCar({modalState, setModalState}) {

    const listOfDriveOptions = ["AWD", "4WD", "RWD" ,"FWD"]

    return (
        <dialog className="add-car-modal">
            <div className="modal-title">Add {modalState.type} Car</div>
            <form id="add-car-form" action="/api/addCar">
                <div className="modal-body">
                    <div className="modal-body-left">
                        <div className="hide"><input type="text" name="type" value={modalState.type} readOnly /></div>
                        <div className="modal-row">
                            <TextInput title="Brand" placeholder="Ex: Toyota" name="company" />
                        </div>
                        <div className="modal-row">
                            <TextInput title="Model" name="model" placeholder="Ex: Land Cruiser Prado" />
                        </div>

                        <div className="modal-row">
                            <TextInput title="Number of seats" name="numSeats" placeholder="Ex: 5" />
                            <Dropdown title="Drive" name="drive" options={listOfDriveOptions} />
                        </div>

                        <div className="modal-row">
                            <TextInput title="Mileage (km)" name="mileage" placeholder="Ex: 6000" />
                            <TextInput title="Length" name="length" placeholder="Ex: 5 (-1 if unknown)" />
                        </div>

                        <div className="modal-row">
                            <TextInput title="Resale Value after 4 years (PKR)" name="resaleValueYears" placeholder="Ex: 2000000"/>
                        </div>

                         <div className="modal-row">
                            <TextInput title="Resale Value after 60,000 km (PKR)" name="resaleValueDistance" placeholder="Ex: 2000000"/>
                        </div>                      
                    </div>
                    <div className="modal-body-right">
                        <div className="modal-row grow">
                            <ButtonArea title="Features" subtitle="Select all that apply" />
                        </div>
                        <div className="modal-row no-grow">
                            <TextInput title="Image URL" name="image" placeholder="Enter image URL" />
                        </div>
                    </div>
                </div>
                <button type="submit" for="add-car-form" className="add-car-submit-button">Add Car</button>
            </form>
        </dialog>
    )
}

export default AddCar