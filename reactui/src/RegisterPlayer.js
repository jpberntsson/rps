import {useState} from 'react'
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import {useHistory} from "react-router-dom";

function RegisterPlayer() {


    const history = useHistory();
    const [name, setName] = useState('');

    const handleChange = event => {
        setName(event.target.value);
    }


    const handleSubmit = event => {
        event.preventDefault();
        fetch('http://localhost:8080/player', {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({alias: name})
        })
            .then(response => response.json())
            .then(playerData => {
                fetch('http://localhost:8080/match', {
                    method: "POST",
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({playerId: playerData.id})
                })
                    .then(response => response.json())
                    .then(matchData => {
                        history.push("/match/" + matchData.id + "/" + playerData.id)
                    })
            });
    }

    return (
        <div class="container w-50 mt-5 bd-example p-3 border rounded">
            <Form onSubmit={handleSubmit} s>
                <div class="mb-3">
                    <FormGroup>
                        <Label class="form-label" for="name"><h2>Player name</h2></Label>
                        <Input class="form-control" type="text" name="name" id="name" onChange={handleChange}
                        />
                    </FormGroup>
                </div>
                <div class="mb-3">
                    <FormGroup>
                        <Button class="btn btn-default" color="primary" type="submit">Find opponent</Button>
                    </FormGroup>
                </div>
            </Form>
        </div>
    );
}

export default RegisterPlayer;