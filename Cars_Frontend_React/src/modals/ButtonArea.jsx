import CheckboxButton from "./CheckboxButton"
import plusIcon from "../assets/icons/icons8-plus-24.png"

const listOfFeatures = [
        "Sun roof",
        "Lane Keeping Assist",
        "Anti-lock Braking System",
        "360 Camera",
        "Autonomous Emergency Braking",
        "Adaptive Cruise Control"
    ]

function ButtonArea({title, subtitle}) {
    //title: title of the button area Ex: Features
    //sub-title: sub-title of the button area Ex: Select all that apply
    const displayedFeatureButtons = listOfFeatures.map((v) => {
        return <CheckboxButton value={v} name="features" key={v} />
    })
    return (
        <div className="modal-vertical-block">
            <p className="input-title">{title}</p>
            <p className="input-sub-title">{subtitle}</p>
            <div className="features-button-container">
                {displayedFeatureButtons}
                <label className="add-feature-button"><button name="add-feature" /><img src={plusIcon} /></label>
            </div>
        </div>
    )
}

export default ButtonArea