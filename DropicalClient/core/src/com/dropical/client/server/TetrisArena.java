package com.dropical.client.server;

import java.util.Random;

public class TetrisArena {
	private int width;  //sichtbare Spielfeldbreite + 4 (2 links, 2 rechts)
	private int height; //sichtbare Spielfeldhöhe + 4 (ganz oben)
	private int[][] arenaArray; //[y][x]

	//Konstruktoren
	public TetrisArena() {
		//Standard TetrisArena erstellen
		this.width = 24;
		this.height = 14;
		arenaArray = new int[24][14];
		reset();    //Array initalisieren
	}
	public TetrisArena(int w, int h) {
		this.width = w+4;   //+4 für die Rotierung, ect.
		this.height = h+4;  //+4 um sich Algorithmen beim Tetromino einfliegen zu sparen
		this.arenaArray = new int[height][width];
		reset();    //Array initalisieren
	}

	public boolean addLines(int lines) {
		//Fügt zufällig generierte Zeilen ganz unten mit min. einem Loch ein.
		//lines -> Anzahl Zeilen, die hinzugefügt werden
		//Rückgabewert -> Gameover, true oder false

		boolean gameOver = false;   //Rückgabewert, ob gameover oder nicht

		//***** Überprüfen, ob die obersten lines+1 Zeilen keinen Stein beinhalten. *****
		//lines+1, weil nach dem hinzufügen der Zeilen, mindestens eine Zeile frei sein muss. Ansonsten -> Game over!
		//4, weil nur das sichtbare Feld ab der 5. Zeile durchlaufen wird
		for(int y = 4; y < 4+lines+1; y++) {
			for(int x = 0; x < width-4; x++) {
				//wenn kein Tetromino gefunden
				if(arenaArray[y][x+2] == 0) {
					gameOver = false;
				}
				//wenn Tetromino gefunden -> Game over!
				else {
					gameOver = true;
					//Schleife abbrechen, damit GameOver nicht wieder auf false gesetzt wird.
					y = height;
					break;
				}
			}
		}

		//***** Tetrominos nach oben verschieben *****
		//height-lines, weil die letzten lines(1-4) Zeilen mit zufälligen Steinen befüllt werden
		for(int y = 0; y < height-lines; y++) {
			for(int x = 0; x < width-4; x++) {
				//y+lines, weil 1-4 Zeilen nach oben verschoben werden
				//x+2, weil sichtbare Arena bei Array-Index 2 beginnt
				arenaArray[y][x+2] = arenaArray[y+lines][x+2];
			}
		}

		//***** Zeilen mit Steinen befüllen (mindestens 1 Loch pro Zeile) *****
		Random random = new Random();

		for(int y = 0; y < lines; y++) {
			//Position des fixen Lochs bestimmen (Index 0 bis width-4)
			int loch = random.nextInt(width-5);

			//Rest mit zufälligen Steinen oder dem zufällig bestimmten Loch befüllen
			for(int x = 0; x < width-4; x++) {
				if(x != loch) {
					//x+2, weil sichtbare Arena bei Array-Index 2 beginnt
					//height-1, wegen Array-Index
					arenaArray[height-1-y][x+2] = random.nextInt(7);
				}
				else {
					arenaArray[height-1-y][x+2] = 0;
				}
			}
		}

		return gameOver;
	}
	public int seekAndDestroyLines() {
		int detectedLines = 0;  //Rückgabewert, Anzahl gefundener bzw. entfernter Zeilen
		boolean entfernen = false;

		//y=4 (bei 5. Zeile beginnen)
		//alle Zeilen durchgehen
		for(int y = 4; y < height; y++) {
			//Linien suchen
			entfernen = false;
			for(int x = 0; x < width-4; x++) {
				if(arenaArray[y][x+2] != 0) {
					entfernen = true;
				}
				else {
					entfernen = false;
					break;
				}
			}

			//wenn Linie gefunden
			if(entfernen) {
				detectedLines++;
				destroyLine(y);
			}
		}

		return detectedLines;
	}
	private void destroyLine(int y) {
		//***** Linie entfernen *****
		for(int x = 0; x < width-4; x++) {
			arenaArray[y][x+2] = 0;
		}

		//***** Tetrominos nach unten verschieben *****
		//yOld bekommt den y-Wert der entfernen Zeile und geht dann das Spielfeld von unten nach oben durch.
		//4, weil oben 4 Zeilen, für die Einfliegung eines Tetrominos, frei sind.
		for(int yOld = y; yOld > 4; yOld--) {
			//width-4, weil man die sichtbare Breite braucht
			for(int x = 0; x < width-4; x++) {
				//x+2, weil die ersten 2 Indexe für die Bordercollision mit 1 initialisiert sind
				arenaArray[yOld][x+2] = arenaArray[yOld-1][x+2];
			}
		}
	}
	public void reset() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				//links und rechts von der sichtbaren Arena, wird der Array mit 1 gefüllt (wegen Bordercollision)
				//Rest mit 0 füllen
				if(x < 2 || x > width-3) {
					arenaArray[y][x] = 1;
				}
				else {
					arenaArray[y][x] = 0;
				}
			}
		}
	}

	public boolean setTetromino(Tetromino t, int x_new, int y_new) {
		//*** setzt den übergebenen Tetromino bei den angegeben Koordinaten ***
		boolean placed = checkTetromino(t, x_new, y_new);

		int[][] tArray = t.toArray();
		int tHeight = t.getHeight();
		int tWidth = t.getWidth();

		if(placed) {
			for(int y = 0; y < tHeight; y++) {
				for(int x = 0; x < tWidth; x++) {
					//nur setzen, wenn ein Block im Tetromino-Array drinnen steht
					if(tArray[y][x] != 0) {
						arenaArray[y_new+y][x_new+x] = tArray[y][x];
					}
				}
			}
		}

		return placed;
	}
	public boolean checkTetromino(Tetromino t, int x_new, int y_new) {
		//*** überprüft, ob der übergebene Tetromino bei dem angegeben Koordinaten gesetzt werden kann ***
		boolean placeable = false;
		int[][] tArray = t.toArray();
		int tHeight = t.getHeight();
		int tWidth = t.getWidth();
		int xTMP;
		int yTMP;

		//zählt Tetromino-Array durch
		for(int y = 0; y < tHeight; y++) {
			for(int x = 0; x < tWidth; x++) {
				//geht nur hinein, wenn im aktuellen Tetromino-Array Index ein Block drinnen liegt.
				//Wenn nicht, ist der Tetromino vorerst platzierbar.
				if(tArray[y][x] > 0) {
					yTMP = y_new+y;
					xTMP = x_new+x;
					//Überprüfung, wegen IndexOutOfBounds-Exception
					if(xTMP >= 0 && yTMP >= 0 && xTMP < 14 && yTMP < 24) {
						//Ausgangspunkt im Arena-Array (x_new, y_new) + Position im Tetromino-Array (for-Schleifen-Durchlaufvariable x & y)
						//Wenn an diesem Punkt kein Block vorhanden ist (0), ist der Tetromino vorerst platzierbar.
						//ansonsten nicht platzierbar und es wird false returned
						if(arenaArray[yTMP][xTMP] == 0) {
							placeable = true;
						}
						else {
							return false;
						}
					}
					else {
						return false;
					}
				}
				else {
					placeable = true;
				}
			}
		}

		return placeable;
	}
	public boolean checkGameover() {
		boolean gameover = false;

		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < 10; x++) {
				if(arenaArray[y][x+2] != 0) {
					gameover = true;
					break;
				}
			}
		}

		return gameover;
	}

	public int[][] toArray() {
		//*** Schneidet den sichtbaren Array heraus und gibt ihn zurück. ***
		int[][] arenaToArray = new int[height-4][width-4];

		for(int y = 0; y < height-4; y++) {
			for(int x = 0; x < width-4; x++) {
				arenaToArray[y][x] = arenaArray[y+4][x+2];
			}
		}

		return arenaToArray;
	}

	@Override
	public String toString() {
		StringBuilder arenaString = new StringBuilder();

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				arenaString.append(arenaArray[y][x]);
			}
			arenaString.append("\n");
		}
		//.toString ist die toString-Methode des StringBuilders
		return arenaString.toString();
	}
}
