import { Slider } from "../independent-components/slider";
import api from '../../axiosConfig.js';
import { useState, useEffect, useMemo } from "react";
import { formatPkr } from "./menuHelpers.js";

function LeftFilterMenuCategory({ innerText, type, options, id, cars, name, filters, setFilters }) {

    //stuff for ranges/sliders
    const [ranges, setRanges] = useState({
        //for the sliders in the price and resale value filtering
        price: [0, 20000000],
        resaleValueYears: [0, 20000000],
        resaleValueDistance: [0, 20000000]
    });

    const [displayedRanges, setDisplayedRanges] = useState({
        price: [0,20000000],
        resaleValueYears: [0, 20000000],
        resaleValueDistance: [0, 20000000]
    })

    const calculateBounds = useMemo(() => {
        if (!cars || cars.length == 0) {
            return {
                price: [0, 20000000],
                resaleValueYears: [0, 20000000],
                resaleValueDistance: [0, 20000000]
            }
        }
        const pricesList = cars.map((car) => car.price);
        const resaleValueYearsList = cars.map((car) => car.resaleValueYears);
        const resaleValueDistanceList = cars.map((car) => car.resaleValueDistance);

        const priceMin = Math.min(...pricesList);
        const priceMax = Math.max(...pricesList);
        const resaleValueYearsMin = Math.min(...resaleValueYearsList);
        const resaleValueYearsMax = Math.max(...resaleValueYearsList);
        const resaleValueDistanceMin = Math.min(...resaleValueDistanceList)
        const resaleValueDistanceMax = Math.max(...resaleValueDistanceList)
        return {
            price: [priceMin, priceMax],
            resaleValueYears: [resaleValueYearsMin, resaleValueYearsMax],
            resaleValueDistance: [resaleValueDistanceMin, resaleValueDistanceMax]
        }
    }, [cars])

    //to filter cars based on any range filter
    function valueCommitHandler(name, values) {
        setFilters({
            ...filters,
            [name]: [values[0], values[1]]
        })
        
    }
    //to display the current range selected
    function valueChangeHandler(name, values) {
        setDisplayedRanges({
            ...displayedRanges,
            [name]: [values[0], values[1]]
        })
    }


    //stuff for checkbox
    const optionsList = options && (options.map((option) => {
        //option = company/seats, etc.
        return (
            <li className="category-sub-option" key={option} >
                <label htmlFor={option}>
                    <input type="checkbox" className="checkbox-color-change" id={id} value={option} onChange={checkboxHandler} name={name} />{option}</label>
            </li>)
    }))

    async function checkboxHandler(e) {
        const name = e.target.name;
        const checked = e.target.checked;
        const array = filters[name];
        console.log("array: ", array);
        console.log(name);

        if (checked) {
            const newArray = [...array, e.target.value];
            setFilters({
                ...filters,
                [name]: newArray
            });
        } else {
            const newArray = array.filter((value) => e.target.value !== value);
            setFilters({
                ...filters,
                [name]: newArray
            })
        }

    }


    //for range

    return (
        <>
            {/* CHECKBOX */}
            {type === "checkbox" && (
                <li className="left-filter-menu-category"><button className="button-color-change" >{innerText}</button>
                    <ul className="category-sub-group">
                        {optionsList}
                    </ul>
                </li>
            )}

            {/* RANGE */}
            {type === "range" && (
                <li className="left-filter-menu-category"><button className="button-color-change">{innerText}</button>
                    <Slider
                        defaultValue={[0, 100000]}
                        min={calculateBounds[name][0]? calculateBounds[name][0]-10000 : 0}
                        max={calculateBounds[name][1] + 10000}
                        step={((calculateBounds[name][1] + 10000) - calculateBounds[name][0]) / 100}
                        name={name}
                        onValueCommit={(values) => valueCommitHandler(name, values)}
                        onValueChange={(values) => valueChangeHandler(name, values)}
                    />
                    <p>{formatPkr(displayedRanges[name][0])} - {formatPkr(displayedRanges[name][1])}</p>
                </li>
            )}

            {/* DROPDOWN */}
            {type === "dropdown" && (
                <li className="left-filter-menu-category"><button className="button-color-change">{innerText}</button>
                </li>
            )}

        </>
    )
}

//extra functions

export default LeftFilterMenuCategory