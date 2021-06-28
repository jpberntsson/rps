import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import RegisterPlayer from "./RegisterPlayer"
import Match from "./Match"
import Game from "./Game"

class App extends React.Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={RegisterPlayer}/>
            <Route path='/match/:matchId/:playerId' exact={true} component={Match}/>
            <Route path='/game/:gameId/:playerId' exact={true} component={Game}/>
          </Switch>
        </Router>
    )
  }
}
export default App;
