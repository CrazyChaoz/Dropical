package com.dropical.client.server;

import com.badlogic.gdx.utils.TimeUtils;
import com.dropical.client.serverEssentials.GameState;
import com.dropical.client.serverEssentials.PollRequest;
import com.dropical.client.serverEssentials.TetrisServer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class TetrisServerImpl implements TetrisServer {
	private TetrisPlayer[] players;
	private int level;
	private long timeTillNextLevel;
	private long timerStartTime;
	private long timerInterval = 1000000000;	//1 Sekunde in Nanosekunden

	public TetrisServerImpl(int anzahlSpieler) {
		players = new TetrisPlayer[anzahlSpieler];
		level = 1;
		timeTillNextLevel = 15;
		timerStartTime = TimeUtils.nanoTime();

		for(int i = 0; i < anzahlSpieler; i++) {
			players[i] = new TetrisPlayer();
		}
	}

	@Override
	public PollRequest pollGameState(PollRequest poll) throws Exception {
		//Zeit-Level System (erst starten, wenn Spiel läuft)
		if(poll.getGameState() == GameState.GAME_RUNNING) {
			if(level < 10) {	//max. Level ist 10
				if(TimeUtils.timeSinceNanos(timerStartTime) > timerInterval) {
					//60s Countdown runterzählen
					timeTillNextLevel--;
					//wenn Countdown vorbei, Level erhöhen
					if(timeTillNextLevel == 0) {
						level++;
						timeTillNextLevel = 15;
					}
					timerStartTime = TimeUtils.nanoTime();
				}
			}
			else {
				timeTillNextLevel = 0;
			}
		}

		//aktueller Spieler
		TetrisPlayer player = players[poll.getPlayerNo()];

		//wenn GameState beim Spieler davor verändert wurde, muss es dieser Spieler ebenfalls erfahren
		player.setGameState(poll.getGameState());

		//----------------------------------------------------------

		//Spielschritt berechnen
		player.calcGameStep(poll.getPlayerAction());

		//nach Spielschritt-Berechnung muss der GameSpeed berechnet werden
		BigDecimal bd = new BigDecimal(1000000000*((11 - level)*0.05), new MathContext(0)); //um keine Ergebnisse wie 8E10 zu bekommen
		BigInteger bi = bd.toBigInteger();  //keine Kommastellen
		player.setGameSpeedInterval(Long.parseLong(bi.toString()));

		//wenn der Spieler Linien zerstört hat -> anderem Spieler Linien hinzufügen
		int lines = player.getDestroiedLines();
		if(lines > 0 && players.length == 2) {	//nur bei Multiplayer
			TetrisPlayer otherPlayer = players[(poll.getPlayerNo()+1)%2];
			if(otherPlayer.addLines(lines)) {	//wenn man durch hinzugefügte Linie Gameover wird, GameState ändern
				otherPlayer.getStateOfGame().loose();
			}
			player.setDestroiedLines(0);	//durch aktuellen Zug Anzahl zerstörte Linien wieder auf 0 setzen
		}

		//----------------------------------------------------------

		//PollRequest im aktuellen GameState zusammenbauen und an Client zurückschicken
		player.getStateOfGame().setPlayers(players);	//TetrisPlayer Array dem GameState mitgeben
		poll = player.getStateOfGame().buildPollRequest(poll);
		poll.setLevel(level);
		poll.setTime(timeTillNextLevel);

		return poll;
	}
}
