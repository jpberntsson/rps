import {React, useState} from "react";
import {Button, Form, FormGroup, Label} from "reactstrap";

function SignControll(props) {

    const [sign, setSign] = useState('rock');

    const handleChange = event => {
        setSign(event.target.value);
    }

    function disabled() {
        return !props.game.currentRoundFinished && props.game.playerSign
    }

    return (<Form onSubmit={handleSubmit}>
        <div class="mb-3">
            <FormGroup>
                <Label class="form-label" for="name">
                    <strong>Pick your sign:</strong>
                </Label>
                <select class="form-select" value={sign} onChange={handleChange} disabled={disabled()}>
                    <option value="rock">Rock</option>
                    <option value="paper">Paper</option>
                    <option value="scissors">Scissors</option>
                </select>
            </FormGroup>
        </div>
        <div class="mb-3">
            <FormGroup>
                <Button class="btn btn-default" type="submit" disabled={disabled()}>Start!</Button>
            </FormGroup>
        </div>
    </Form>)
}
export default SignControll;