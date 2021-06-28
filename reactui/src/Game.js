import {React, useEffect, useState} from "react";
import {Button, Form, FormGroup, Label} from "reactstrap";
import {useParams} from "react-router-dom";
import useInterval from "./UseInterval";
import Overall from "./Overall"
import CurrentRound from "./CurrentRound"

function Game() {

    const [sign, setSign] = useState('rock');
    const {gameId} = useParams();
    const {playerId} = useParams();
    const [game, setGame] = useState({});

    const handleChange = event => {
        setSign(event.target.value);
    }

    const handleSubmit = event => {
        event.preventDefault();
        fetch('http://localhost:8080/game/' + gameId, {
            method: "PUT",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({sign: sign, playerId: playerId})
        })
            .then(response => response.json())
            .then(data => {
                setGame(data)
            });
    }

    function fetchGame(id) {
        fetch('http://localhost:8080/game/' + gameId + "?" + new URLSearchParams({
            playerId: playerId
        }), {
            method: "GET",
            headers: {'Content-Type': 'application/json'},

        })
            .then(response => response.json())
            .then(data => {
                setGame(data)
            })
    }

    useInterval(() => {
        fetchGame(gameId)
    }, 1000)

    function disabled() {
        return !game.currentRoundFinished && game.playerSign
    }

    return (
        <div>
            <div class="w-50 mt-5 p-3 border rounded container">
                <Overall game={game}/>
            </div>
            <div class="w-50 mt-5 p-3 border rounded container">
                <Form onSubmit={handleSubmit}>
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
                            <Button class="btn btn-default" color="primary" type="submit" disabled={disabled()}>Start!</Button>
                        </FormGroup>
                    </div>
                </Form>
            </div>
            <div className="w-50 mt-5 p-3 border rounded container">
                <CurrentRound game={game}/>
            </div>
        </div>

    )
}

export default Game;