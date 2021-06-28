import {React} from "react";
function CurrentRound(props) {
    return (

        <div>
            <div className={(!props.game.currentRoundFinished && !props.game.playerSign && !props.game.opponentSign) ? "":"d-none"}>
                <h2>The game can begin!</h2>
            </div>
            <div className={(!props.game.currentRoundFinished && props.game.opponentSign) ? "":"d-none"}>
                <h2>Your opponent is awaiting your next move...</h2>
            </div>
            <div className={(!props.game.currentRoundFinished && props.game.playerSign && !props.game.opponentSign) ? "d-flex":"d-none"}>
                <h2>Awaiting your opponent's next move...</h2>
                <div className="spinner-grow ms-auto" role="status" aria-hidden="true"></div>
            </div>

            <div className={(props.game.currentRoundFinished && props.game.playerWon) ? "":"d-none"}>
                <h3>You won this round by playing {props.game.playerSign} against {props.game.opponentSign}</h3>
            </div>

            <div className={(props.game.currentRoundFinished && !props.game.playerWon && !props.game.isTie) ? "":"d-none"}>
                <h3>You lost this round by playing {props.game.playerSign} against {props.game.opponentSign}</h3>
            </div>

            <div className={(props.game.currentRoundFinished && props.game.isTie) ? "":"d-none"}>
                <h3>You tied this round by playing {props.game.playerSign} against {props.game.opponentSign}</h3>
            </div>
        </div>
    )

}
export default CurrentRound;
