import '../home_styles.css'


function CarCard({car, setSelectedCars, selectedCars}) {

    function selectCarHandler(e) {
        const selectedCar = `${car.company} ${car.model} ${car.year}`;
        if(e.target.checked) {
            console.log(selectedCar);
            setSelectedCars([...selectedCars, selectedCar])
        }
        else if(!e.target.checked) {
            const newSelectedCarsArray = selectedCars.filter((c)=> c !== selectedCar);
            setSelectedCars(newSelectedCarsArray);
        }
        console.log("Selected cars: ", selectedCars)
    }
    return (
        <>
            <div className="car-card">
                <div className="car-card-image"><img src={  car.image }/></div>
                <div className="car-card-info">
                    <p className="car-name">{car.company} {car.model}</p>
                    <p className="year">{car.year}</p>
                    <p className="initial-price">PKR {car.price.toLocaleString()}</p>
                    <p className="drive-and-seats">{car.drive + ""} - {car.numSeats} seater</p>
                    <p className="fuel-mileage">Fuel mileage: {car.fuelMileage}km</p>
                    <p className="four-year-resale">4yr resale: PKR {car.resaleValueYears.toLocaleString()}</p>
                    <p className="distance-resale">60,000km resale: PKR {car.resaleValueDistance.toLocaleString()}</p>
                    <input type="checkbox" className="select-car-checkbox" onChange={selectCarHandler}></input>
                </div>
            </div>
        </>
            )
}

export default CarCard