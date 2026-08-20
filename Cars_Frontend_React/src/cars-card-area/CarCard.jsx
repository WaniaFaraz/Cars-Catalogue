import '../home_styles.css'


function CarCard({car}) {

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
                    <input type="checkbox" className="select-car-checkbox"></input>
                </div>
            </div>
        </>
            )
}

export default CarCard