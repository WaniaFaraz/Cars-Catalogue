import LeftFilterMenu from './left-filter-menu/LeftFilterMenu.jsx'
import CarsArea from './CarsArea.jsx'
import RightPanel from './right-panel/RightPanel.jsx'
import { useState } from 'react';
import { useEffect } from 'react';
import api from '../axiosConfig.js'

function MainArea() {

    const [cars, setCars] = useState([]); //unchanged
    const [filters, setFilters] = useState({
        company: [],
        drive: [],
        numSeats: [],
        price: [],
        year: 0,
        resaleValueYears: [],
        resaleValueDistance: []
    })


    useEffect(() => {
        fetchCars();
    }, [])

    useEffect(() => {
        console.log("Debugging cars array", cars);
    }, [cars])

    async function fetchCars() {
        const response = await api.get('/get-catalogue');
        const data = response.data;
        setCars([...data]);
        setFilters({
            company: [],
            drive: [],
            numSeats: [],
            price: [],
            year: 0,
            resaleValueYears: [],
            resaleValueDistance: []
        })
        return data;
    }

    const [selectedCars, setSelectedCars] = useState([]);


    return (
        <div className="main-area">
            <LeftFilterMenu cars={cars} filters={filters} setFilters={setFilters} />
            <CarsArea cars={cars} filters={filters} selectedCars={selectedCars} setSelectedCars={setSelectedCars} />
            <RightPanel onCarsAdded={fetchCars} selectedCars={selectedCars} setSelectedCars={setSelectedCars} />
        </div>
    )
}

export default MainArea