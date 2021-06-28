import {React, useState, useEffect} from "react";
import { useParams, useHistory } from "react-router-dom";
import useInterval from "./UseInterval";


function Match() {

    const history = useHistory();
    const [match, setMatch] = useState({});
    const { matchId } = useParams();
    const { playerId } = useParams();

    function fetchMatch(id) {
        fetch('http://localhost:8080/match/' + id, {
            method:"GET",
            headers: { 'Content-Type': 'application/json' },
        })
            .then(response => response.json())
            .then(data => {
                if(data.gameId != undefined) {
                    setMatch(data)
                }
            })
    }

    useInterval(() => {fetchMatch(matchId)}, 1000)

    useEffect(() => {
        if(match.gameId != undefined) {
            history.push("/game/" + match.gameId + "/" + playerId)
        }

    })

    return (
        <div className="container d-flex align-items-center w-50 mt-5 p-3 border rounded">
            <h2>Finding match...</h2>
            <div className="spinner-grow ms-auto" role="status" aria-hidden="true"></div>
        </div>
    )

}
export default Match;