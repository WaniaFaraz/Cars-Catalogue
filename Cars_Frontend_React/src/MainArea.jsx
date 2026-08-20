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
        price: [0,140000000],
        year: 2000,
        resaleValueYears:[0, 500000000],
        resaleValueDistance: [0, 500000000]
    })


    useEffect(() => {

        async function fetchCars() {
            const response = await api.get('/get-catalogue');
            const data = response.data;
            setCars(data);
        }
        fetchCars();
    }, [])

   

    return (
        <div className="main-area">
            <LeftFilterMenu cars={cars} filters={filters} setFilters={setFilters} />
            <CarsArea cars={cars} filters={filters}  />
            <RightPanel />
        </div>
    )
}

export default MainArea