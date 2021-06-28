# rps

This is a rock, paper scissors game with a domain layer written in java with a spring boot rest-api and a react single-page application on top. It supports an 'infinite' number of players.

You should be able start the project by cloning it and then running 

    mvn spring-boot:run 

ignore all the errors the js-dependencies throw. 

You should be able to start it without having Node installed but it wouldn't surprise me if you run into problems. It works on my machine :wink:

### Playing the game

Once it starts you can go to http://localhost:8080. You can open up a second browser tab with the same url to get a game going.

Once two players have joined a game either player can start by making a move. Once a round has finished either player can start the next round by making a move. There is no round limit for a game. It can go on forever.

### There are a few things I've scoped out:

* Security, authentication and authorization
* Tests for the controllers and react ui.
* Error handling. The controllers expect the client to pass in valid values and will not fail with clear error messages if entities are not found. The ui will not handle io-problems gracefully either.
* WebSockets instead of polling would've been a better solution for the ui but I didn't have the time to dig into that this time.

### I focused on:

* Trying to create well designed business logic in the domain layer (domain objects and services) with a good coupling and cohesion between the different components inspired by clean architecture. I wanted to create a domain layer free of implementation details of any outer layer(s), in this case the rest-api.
* A high level of test coverage of the business logic. I stuck to TDD as best as I could.
