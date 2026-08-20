
import LeftFilterMenuCategory from "./LeftFilterMenuCategory";
import { useState } from 'react';
import { useEffect } from "react";
import filterIcon from '../assets/icons/icons8-filter-96.png';
import api from '../../axiosConfig.js';

const driveOptions = ["AWD", "FWD", "RWD", "4WD"]
const features = [
    "Sun roof",
    "Stereo",
    "Radio",
    "Heated Seats",
    "Cruise Control"
]
function LeftFilterMenu({ cars, filters, setFilters }) {
    const [companies, setCompanies] = useState([]);

    useEffect(() => {
        async function fetchCompanies() {
            const response = await api.get('/get-all/company');
            const rawCompanies = response.data;
            const companyOptions = [...new Set(rawCompanies)];
            setCompanies(companyOptions);
        }
        fetchCompanies()

    }, [cars])

    return (
        <div className="left-filter-menu">
            <div className="left-filter-menu-title mini-title">Filters<img src={filterIcon} alt="filter icon" /></div>
            <span className="underline"></span>
            <ul className="menu-options">
                <LeftFilterMenuCategory name="company" innerText="Company" type="checkbox" options={companies} id="company-filter" cars={cars} filters={filters} setFilters={setFilters}/>

                <LeftFilterMenuCategory innerText="Price" type="range" id="price-filter" cars={cars} name="price" filters={filters} setFilters={setFilters} />
                <LeftFilterMenuCategory innerText="Year" type="dropdown" id="year-filter" cars={cars} />
                <LeftFilterMenuCategory name="numSeats" innerText="Seats" type="checkbox" options={[5, 7]} id="seat-filter" cars={cars} filters={filters} setFilters={setFilters} />
                <LeftFilterMenuCategory name="drive" innerText="Drive" type="checkbox" options={driveOptions} id="drive-filter" cars={cars} filters={filters} setFilters={setFilters} />
                <LeftFilterMenuCategory innerText="Resale Value after 4 yrs" type="range" id="resale-years-filter" cars={cars} name="resaleValueYears" filters={filters} setFilters={setFilters} />
                <LeftFilterMenuCategory innerText="Resale Value after 60,000 km" type="range" id="resale-distance-filter" cars={cars} name="resaleValueDistance" filters={filters} setFilters={setFilters} />
                <LeftFilterMenuCategory innerText="Features" type="checkbox" options={features} id="features-filter" cars={cars} />

            </ul>
        </div>
    )

}

export default LeftFilterMenu