import ButtonArea from "./ButtonArea"
import TextInput from "./TextInput"
import Dropdown from "./Dropdown"

import './AddCar_styles.css'

import api from '../../axiosConfig'

import { useEffect, useRef } from "react"

function AddCar({ modalState, setModalState, onClose, onCarsAdded }) {

    const listOfDriveOptions = ["AWD", "4WD", "RWD", "FWD"]

    const dialogRef = useRef(null);

    useEffect(() => {
        const dialog = dialogRef.current;
        if (!dialog) return;
        if (modalState.isOpen) {
            dialog.showModal();
        }
        else {
            dialog.close();
        }
    }, [modalState])

    useEffect(() => {
        const dialog = dialogRef.current;
        if (!dialog) return;

        function cancelHandler(e) {
            e.preventDefault();
            onClose();

        }
        dialog.addEventListener("cancel", cancelHandler);
        return () => dialog.removeEventListener("cancel", cancelHandler);
    }, [onClose])

    async function handleFormSubmit(e) {
        e.preventDefault();
        const form = e.target;
        const formData = new FormData(form);
        const searchParams = new URLSearchParams(formData);

        try {
            const response = await api.post("/addCar", searchParams, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });
            const isSuccess = response.data;
            if (isSuccess) {
                console.log("Car added successfully!");
                onCarsAdded();
                console.log("On cars added done")
                onClose();
                resetForm(form);
            }
        } catch (error) {
            console.error("Axios POST request failed:", error);
        }
    }

    function resetForm(form) {
        form.reset();
    }

    return (
        <dialog className="add-car-modal" ref={dialogRef}>
            <div className="modal-title">Add {modalState.type} Car</div>
            <form id="add-car-form" onSubmit={handleFormSubmit}>
                <div className="modal-body">
                    <div className="modal-body-left">
                        <div className="hide"><input type="text" name="type" value={modalState.type} readOnly /></div>
                        <div className="modal-row">
                            <TextInput title="Brand" placeholder="Ex: Toyota" name="company" />
                            <TextInput title="Model" name="model" placeholder="Ex: Land Cruiser Prado" />

                        </div>
                        <div className="modal-row">
                            <TextInput title="Price" placeholder="Ex: 20000000" name="price" />
                            <TextInput title="Year" placeholder="Ex: 2023" name="year" />
                        </div>

                        <div className="modal-row">
                            <TextInput title="Number of seats" name="numSeats" placeholder="Ex: 5" />
                            <Dropdown title="Drive" name="drive" options={listOfDriveOptions} />
                        </div>
                        {modalState.type === "Fuel" && (
                            <div className="modal-row">
                                <TextInput title="Mileage (km)" name="fuelMileage" placeholder="Ex: 6000" />
                                <TextInput title="Length" name="length" placeholder="Ex: 5 (-1 if unknown)" />
                            </div>
                        )}
                        {modalState.type === "PHEV" && (
                            <>
                                <div className="modal-row">
                                    <TextInput title="Electricity Mileage (km)" name="electricityMileage" placeholder="Ex: 6000" />
                                    <TextInput title="Fuel Mileage (km)" name="fuelMileage" placeholder="Ex: 6000" />

                                </div>
                                <div className="modal-row">
                                    <TextInput title="Charging Time (hrs)" name="chargingTime" placeholder="Ex: 2.5" />
                                    <TextInput title="Charger type" name="charger" placeholder="charger lvl. Ex: 3" />
                                </div>
                                <div className="modal-row">
                                    <TextInput title="Length" name="length" placeholder="Ex: 5 (-1 if unknown)" />
                                </div>
                            </>
                        )}
                        {modalState.type === "HEV" && (
                            <>
                                <div className="modal-row">
                                    <TextInput title="Electricity Mileage (km)" name="electricityMileage" placeholder="Ex: 6000" />
                                    <TextInput title="Fuel Mileage (km)" name="fuelMileage" placeholder="Ex: 6000" />
                                </div>
                                <div className="modal-row">
                                    <TextInput title="Length" name="length" placeholder="Ex: 5 (-1 if unknown)" />
                                </div>
                            </>
                        )}


                        <div className="modal-row">
                            <TextInput title="Resale Value after 4 years (PKR)" name="resaleValueYears" placeholder="Ex: 2000000" />
                        </div>

                        <div className="modal-row">
                            <TextInput title="Resale Value after 60,000 km (PKR)" name="resaleValueDistance" placeholder="Ex: 2000000" />
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
                <div className="modal-lower-button-area">
                    <button onClick={onClose}>Close</button>
                    <button type="submit" htmlFor="add-car-form" className="add-car-submit-button">Add Car</button>
                </div>
            </form>
        </dialog>
    )
}

export default AddCar