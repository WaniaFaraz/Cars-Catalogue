

function Dropdown({title, name, options}) {
    //title: title of the dropdown (will probably have an uppercase letter)
    //name: name of the variable in the backend ex: drive
    //options: array of options to put in the values of the option, and as innerText
    const displayedOptions = options.map((v) => {
        return <option value={v} key={v}>{v}</option>
    })
    return (
        <div className="modal-vertical-block">
            <p className="input-title">{title}</p>
            <select name={name} multiple size="1">
                {displayedOptions}
            </select>
        </div>
    )
}

export default Dropdown