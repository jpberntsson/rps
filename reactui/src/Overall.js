import {React} from "react";

function Overall(props) {
    return (
        <table class="table">
            <thead>
            <tr>
                <th >Opponent:</th>
                <th >Your score:</th>
                <th >Opponent score:</th>
            </tr>
            </thead>
            <tr>
                <td>{props.game.opponentName} </td>
                <td>{props.game.playerScore} </td>
                <td>{props.game.opponentScore} </td>
            </tr>
        </table>
    )
}

export default Overall;