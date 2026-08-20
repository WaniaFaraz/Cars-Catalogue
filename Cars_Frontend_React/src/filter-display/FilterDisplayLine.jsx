import DisplayFilter from './DisplayFilter.jsx'

const filters = ["Price", "Mileage", "Resale"]

function FilterDisplayLine() {
    const filterList = filters.map((filter) => {
        return <DisplayFilter key={filter} className="filter" innerText={filter} />
    })
    return (
        <div className="filter-display"><p className="mini-title filter-display-title">Show</p>
        {filterList}
        </div>
    );
}

export default FilterDisplayLine