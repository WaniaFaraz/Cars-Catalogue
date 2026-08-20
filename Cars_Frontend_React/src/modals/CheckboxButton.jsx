

function CheckboxButton({name, value}) {
    //name: name attribute in checkbox - to group checkbox buttons
    //value: value of checkbox - also the inner text
    return (
        <label className="feature-button">
            <input type="checkbox" value={value} name={name} />{name}</label>
    )
}

export default CheckboxButton