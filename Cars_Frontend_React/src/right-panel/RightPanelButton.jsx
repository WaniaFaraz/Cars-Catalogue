
function RightPanelButton({innerText, id, className, onClick}) {

    return(
        <button id={id}  className={className} onClick={onClick}>{innerText}</button>
    )
}

export default RightPanelButton