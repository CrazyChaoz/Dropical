# Was noch getan werden muss
+ RequestHandler für Join-, List, CreateGameRequest.   
  Nachteil: Wenn das Spiel läuft, dürfen CreateGame 
  und Join nicht passieren. List wäre egal.
+ clearLines geht nicht.
+ WolliAI verbessern.
  
Nicht schaffbar bis Freitag:
+ Ordentliches Interface auf Client-Seite für
  Spiele-Verwaltung wie Join oder Create oder sunstwas.
  
+ Die GameStates sind ein Durcheinander. Komplettes 
  umbauen notwendig. Es gehört alles Logik in die 
  Game Loop oder alles in currentGameState.update().
  
+ Die aktuelle Infrastruktur der Transmitter und 
  der 1000000 verschiedenen Proxies vereinheitlichen.
  Das heißt fast alles neu schreiben und designen.  
  Einfach nur Transmitter und TransmitterWrapper. 
   
+ GameDataContainer: Mehrere Requests versenden wäre
  erweiterbarer als einen Request, der aus Listen besteht.