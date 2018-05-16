package com.dropical.client.server;

import com.badlogic.gdx.utils.TimeUtils;
import com.dropical.client.gameStates.StateOfGame;
import com.dropical.client.serverEssentials.GameState;
import com.dropical.client.serverEssentials.PlayerAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TetrisPlayer {
	//Arena & Tetromino
	private TetrisArena tetrisArena;
	private List<Tetromino> tetrominoList;
	private Tetromino actTet;
	private Tetromino nextTet;
	private Random random;	//für zufälligen neuen Tetromino

	private StateOfGame stateOfGame;	//StatePattern
	private GameState gameState;	//GameState-Enum

	//GameSpeed-Timing
	private long gameSpeedStartTime;
	private long gameSpeedInterval = 500000000;	//0.5 Sekunde in Nanosekunden

	//Steuerung-Timing
	private boolean keyPressed = false;
	private long keyPressedStartTime;
	private long keyPressedInterval = 200000000;	//20ms
	private long moveStartTime;
	private long moveInterval = 50000000;	//5ms

	//Score
	private int points;
	private int destroiedLines;	//durch aktuellen Zug zerstörte Linien

	public TetrisPlayer() {
		//GameState StatePattern
		stateOfGame = new StateOfGame();

		//Random für Tetrominoerzeugung
		this.random = new Random();

		//Tetrominos erzeugen und in ArrayList speichern (werden immer wieder für die Arena verwendet)
		tetrominoList = new ArrayList<Tetromino>();
		tetrominoList.add(new Tetromino(Tetromino.arrayTetrominoI, 1, 2, 4, 4));
		tetrominoList.add(new Tetromino(Tetromino.arrayTetrominoT, 2, 4, 4, 4));
		tetrominoList.add(new Tetromino(Tetromino.arrayTetrominoL, 3, 4, 4, 4));
		tetrominoList.add(new Tetromino(Tetromino.arrayTetrominoJ, 4, 4, 4, 4));
		tetrominoList.add(new Tetromino(Tetromino.arrayTetrominoZ, 5, 2, 4, 4));
		tetrominoList.add(new Tetromino(Tetromino.arrayTetrominoS, 6, 2, 4, 4));
		tetrominoList.add(new Tetromino(Tetromino.arrayTetrominoO, 7, 1, 4, 4));

		//Arena erzeugen
		this.tetrisArena = new TetrisArena(10, 20);

		//aktuellen und nächsten Tetromino festlegen
		//am Spielbeginn muss zuerst der nächste Tetromino erstellt werden, um überhaupt in newTetromino() einen aktuellen erstellen zu können
		this.nextTet = new Tetromino(tetrominoList.get(this.random.nextInt(7)));
		newTetronimo();

		//für Tetromino-MoveDown-Timing
		this.gameSpeedStartTime = TimeUtils.nanoTime();
	}

	//----------------------------------------------------------

	public void calcGameStep(PlayerAction pressedKey) {
		switch(pressedKey) {
			case NOKEY:
				keyPressedStartTime = TimeUtils.nanoTime();
				keyPressed = false;
				break;
			case RIGHT:
				keyPressedRight();
				break;
			case LEFT:
				keyPressedLeft();
				break;
			case UP:
				rotate();
				break;
			case DOWN:
				keyPressedDown();
				break;
			case DROP:
				//solange, bis Tetromino nicht mehr nach unten bewegt werden kann
				if(gameState == GameState.GAME_RUNNING) {
					while(!moveDown());
				}
				break;
			case START:
				stateOfGame.start();
				break;
			case PAUSE:
				stateOfGame.pauseGame();
				if(gameState == GameState.GAME_PAUSED) {
					stateOfGame.resumeGame();
				}
				break;
			case QUIT:
				break;
		}

		//Tetromino-MoveDown-Timing
		if(TimeUtils.timeSinceNanos(gameSpeedStartTime) > gameSpeedInterval) {
			moveDown();
			gameSpeedStartTime = TimeUtils.nanoTime();
		}
	}

	public boolean addLines(int lines) {
		boolean gameover = false;	//Rückgabewert, ob man durch die Linien Gameover ist

		//wenn oben genug Platz, Tetromino nach oben bewegen
		if(actTet.getPosY()-lines >= 0) {
			actTet.setPosY(actTet.getPosY()-lines);
		}

		//Linien hinzufügen
		gameover = tetrisArena.addLines(lines);

		return gameover;
	}
	private void checkScoreUpdate() {
		destroiedLines = 0;

		//Punkte-System
		if((destroiedLines = tetrisArena.seekAndDestroyLines()) > 0) {
			switch(destroiedLines) {
				case 1:
					points += 40;
					break;
				case 2:
					points += 100;
					break;
				case 3:
					points += 300;
					break;
				case 4:
					points += 1200;
					break;
			}
		}
	}
	private void newTetronimo(){
		//aktuellen Tetromino durch nextTet festlegen
		actTet = tetrominoList.get(nextTet.getId()-1);
		actTet.reset(5, 0);

		//zufälligen nächsten Tetromino erstellen
		nextTet = new Tetromino(tetrominoList.get(random.nextInt(7)));
		nextTet.reset(5, 0);
	}

	//Steuerung
	private void keyPressedRight() {
		//einzelnes moven
		if(!keyPressed) {
			moveOnX(1);
			keyPressedStartTime = TimeUtils.nanoTime();
			keyPressed = true;
		}

		//nach 20ms wird erst auf längeres Drücken reagiert
		if(TimeUtils.timeSinceNanos(keyPressedStartTime) > keyPressedInterval) {
			//wenn länger gedrückt, alle 5ms nach rechts moven
			if(TimeUtils.timeSinceNanos(moveStartTime) > moveInterval) {
				moveOnX(1);
				moveStartTime = TimeUtils.nanoTime();
			}
		}
	}
	private void keyPressedLeft() {
		//einzelnes moven
		if(!keyPressed) {
			moveOnX(-1);
			keyPressedStartTime = TimeUtils.nanoTime();
			keyPressed = true;
		}

		//nach 20ms wird erst auf längeres Drücken reagiert
		if(TimeUtils.timeSinceNanos(keyPressedStartTime) > keyPressedInterval) {
			//wenn länger gedrückt, alle 5ms nach rechts moven
			if(TimeUtils.timeSinceNanos(moveStartTime) > moveInterval) {
				moveOnX(-1);
				moveStartTime = TimeUtils.nanoTime();
			}
		}
	}
	private void keyPressedDown() {
		//einzelnes moven
		if(!keyPressed) {
			moveDown();
			keyPressedStartTime = TimeUtils.nanoTime();
			keyPressed = true;
		}

		//nach 20ms wird erst auf längeres Drücken reagiert
		if(TimeUtils.timeSinceNanos(keyPressedStartTime) > keyPressedInterval) {
			//wenn länger gedrückt, alle 5ms nach rechts moven
			if(TimeUtils.timeSinceNanos(moveStartTime) > moveInterval) {
				moveDown();
				moveStartTime = TimeUtils.nanoTime();
			}
		}
	}

	private boolean moveDown() {
		boolean moveable = false;	//Rückgabewert, ob Tetromino ganz unten angekommen ist

		if(gameState == GameState.GAME_RUNNING) {
			//wenn der Tetromino nicht eine Position weiter unten platziert werden konnte -> neuer Block
			if(!tetrisArena.checkTetromino(actTet, actTet.getPosX(), actTet.getPosY()+1)) {
				moveable = true;

				//Tetromino in ArenaArray hinzufügen
				tetrisArena.setTetromino(actTet, actTet.getPosX(), actTet.getPosY());
				checkScoreUpdate();

				//neue Tetrominos festlegen
				newTetronimo();
			}
			else {
				actTet.setPosY(actTet.getPosY()+1);
			}

			//Gameover überprüfen
			if(tetrisArena.checkGameover()) {
				stateOfGame.loose();
			}

		}

		return moveable;
	}
	private boolean moveOnX(int schritte) {
		if(gameState == GameState.GAME_RUNNING) {
			if(tetrisArena.checkTetromino(actTet, actTet.getPosX()+schritte, actTet.getPosY())) {
				actTet.setPosX(actTet.getPosX()+schritte);
				return true;	//Tetromino konnte auf X-Koordinate verschoben werden
			}
		}

		return false;	//Tetromino konnte auf X-Koordinate nicht verschoben werden
	}
	private void rotate() {
		if(gameState == GameState.GAME_RUNNING) {
			actTet.rotateRight();
			if(!tetrisArena.checkTetromino(actTet, actTet.getPosX(), actTet.getPosY())) {

				//Tetromino kann nicht gedreht werden -> deshalb rotierten Stein nach rechts oder links bewegen und prüfen, ob er dort gesetzt werden kann
				if(moveOnX(1)) { }
				else if(moveOnX(-1)) { }
				//Ausnahmefall Tetromino I: wenn Tetromino I am ganz rechten Rand liegt, muss man ihn 2x nach links verschieben, um rotieren zu können
				else if(moveOnX(-2)) { }
				else {
					actTet.rotateLeft();
				}

			}
		}
	}

	//----------------------------------------------------------

	//Getter
	public TetrisArena getTetrisArena() {
		return tetrisArena;
	}
	public Tetromino getActTet() {
		return actTet;
	}
	public Tetromino getNextTet() {
		return nextTet;
	}
	public StateOfGame getStateOfGame() {
		return stateOfGame;
	}
	public int getPoints() {
		return points;
	}
	public int getDestroiedLines() {
		return destroiedLines;
	}

	//Setter
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	public void setGameSpeedInterval(long gameSpeedInterval) {
		this.gameSpeedInterval = gameSpeedInterval;
	}
	public void setDestroiedLines(int destroiedLines) {
		this.destroiedLines = destroiedLines;
	}
}