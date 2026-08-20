
function TextInput({title, placeholder, name}) {
    //text: title above the text input
    //placeholder: placeholder of the text input
    //name: variable name for the backend, also used for the id
    return (
        <>
            <div className="modal-vertical-block">
                <p className="input-title">{title}</p>
                <input type="text" id={name} name={name} placeholder={placeholder} />
            </div>
        </>
    )
}

export default TextInput