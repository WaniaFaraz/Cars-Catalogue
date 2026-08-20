
function DisplayFilter({innerText, className}) {

    return (
        <label><input type="checkbox" className={className} />{innerText}</label>
    )
}

export default DisplayFilter