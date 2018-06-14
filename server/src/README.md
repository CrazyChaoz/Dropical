# Architecture
The Server is built with a hierarchy.

### 1: Server and GameManager
The Server is a singleton that provides globally
available functionality like Logger and ThreadPool.  
The GameManager accepts Client connections and
handles the joining to the multiple running games.

### 2: Game and Requests
The class Game has the game logic with GameStates, may
have many players and viewers connected and it
sends out the necessary GameDataContainers.

### 3: Tetris
You want to play Tetris, right? Every player has
their own Arena and Tetrominos.


# Was noch getan werden muss
+ RequestHandler für Join-, List, CreateGameRequest.   
  Nachteil: Wenn das Spiel läuft, dürfen CreateGame 
  und Join nicht passieren. List wäre egal.
+ clearLines geht nicht.
+ WolliAI verbessern.
  
# Nicht schaffbar bis Freitag:
+ Ordentliches Interface auf Client-Seite für
  Spiele-Verwaltung wie Join oder Create oder sonstwas.
  
+ Die GameStates sind ein Durcheinander. Komplettes 
  umbauen notwendig. Es gehört alles Logik in die 
  Game Loop oder alles in currentGameState.update().
  
+ Die aktuelle Infrastruktur der Transmitter und 
  der 1000000 verschiedenen Proxies vereinheitlichen.
  Das heißt fast alles neu schreiben und designen.  
  Einfach nur Transmitter und TransmitterWrapper.   
  Das heißt, die Requests sollen ordentlich gekapselt sein.
   
+ GameDataContainer: Mehrere Requests versenden wäre
  erweiterbarer als einen Request, der aus Listen besteht.