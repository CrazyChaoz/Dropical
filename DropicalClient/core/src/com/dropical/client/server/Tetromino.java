package com.dropical.client.server;

public class Tetromino {
	//Tetrominos int[rotation][y][x]
	public static final int[][][] arrayTetrominoI = {   //rotationSteps = 2 | id = 1
			//rotation = 0
			{
					{0,0,0,0},  //y=0
					{1,1,1,1},  //y=1
					{0,0,0,0},  //y=2
					{0,0,0,0}   //y=3
			},
			//rotation = 1
			{
					{0,1,0,0},
					{0,1,0,0},
					{0,1,0,0},
					{0,1,0,0}
			}
	};
	public static final int[][][] arrayTetrominoT = {   //rotationSteps = 4 | id = 2
			//rotation = 0
			{
					{0,0,0,0},  //y=0
					{0,0,0,0},  //y=1
					{2,2,2,0},  //y=2
					{0,2,0,0}   //y=3
			},
			//rotation = 1
			{
					{0,0,0,0},
					{0,2,0,0},
					{2,2,0,0},
					{0,2,0,0}
			},
			//rotation = 2
			{
					{0,0,0,0},
					{0,2,0,0},
					{2,2,2,0},
					{0,0,0,0}
			},
			//rotation = 3
			{
					{0,0,0,0},
					{0,2,0,0},
					{0,2,2,0},
					{0,2,0,0}
			}
	};
	public static final int[][][] arrayTetrominoL = {   //rotationSteps = 4 | id = 3
			//rotation = 0
			{
					{0,0,0,0},  //y=0
					{0,0,0,0},  //y=1
					{3,3,3,0},  //y=2
					{3,0,0,0}   //y=3
			},
			//rotation = 1
			{
					{0,0,0,0},
					{3,3,0,0},
					{0,3,0,0},
					{0,3,0,0}
			},
			//rotation = 2
			{
					{0,0,0,0},
					{0,0,3,0},
					{3,3,3,0},
					{0,0,0,0}
			},
			//rotation = 3
			{
					{0,0,0,0},
					{0,3,0,0},
					{0,3,0,0},
					{0,3,3,0}
			}
	};
	public static final int[][][] arrayTetrominoJ = {   //rotationSteps = 4 | id = 4
			//rotation = 0
			{
					{0,0,0,0},  //y=0
					{4,0,0,0},  //y=1
					{4,4,4,0},  //y=2
					{0,0,0,0}   //y=3
			},
			//rotation = 1
			{
					{0,0,0,0},
					{0,4,4,0},
					{0,4,0,0},
					{0,4,0,0}
			},
			//rotation = 2
			{
					{0,0,0,0},
					{0,0,0,0},
					{4,4,4,0},
					{0,0,4,0}
			},
			//rotation = 3
			{
					{0,0,0,0},
					{0,4,0,0},
					{0,4,0,0},
					{4,4,0,0}
			}
	};
	public static final int[][][] arrayTetrominoZ = {   //rotationSteps = 2 | id = 5
			//rotation = 0
			{
					{0,0,0,0},  //y=0
					{0,0,0,0},  //y=1
					{5,5,0,0},  //y=2
					{0,5,5,0}   //y=3
			},
			//rotation = 1
			{
					{0,0,0,0},
					{0,0,5,0},
					{0,5,5,0},
					{0,5,0,0}
			}
	};
	public static final int[][][] arrayTetrominoS = {   //rotationSteps = 2 | id = 6
			//rotation = 0
			{
					{0,0,0,0},  //y=0
					{0,0,0,0},  //y=1
					{0,6,6,0},  //y=2
					{6,6,0,0}   //y=3
			},
			//rotation = 1
			{
					{0,0,0,0},
					{0,6,0,0},
					{0,6,6,0},
					{0,0,6,0}
			}
	};
	public static final int[][][] arrayTetrominoO = {   //rotationSteps = 1 | id = 7
			//rotation = 0
			{
					{0,0,0,0},  //y=0
					{0,0,0,0},  //y=1
					{0,7,7,0},  //y=2
					{0,7,7,0}   //y=3
			}
	};

	private int[][][] tetrominoMatrix;   //[rotation][y][x] | 2. Dimension für Tetromino (4x4) & 3. Dimension für Rotation
	private int posX;   //Tetromino x
	private int posY;   //Tetromino y
	private int width;  //Tetromino-Breite (normal: 4)
	private int height; //Tetromino-Höhe (normal: 4)
	private int rotation;   //aktuelle Rotationsschritt
	private int rotationSteps;  //Anzahl Rotationsschritte
	private int id; //Block Typ (0=kein Tetromino, 1 bis Anzahl verfügbarer Tetrominos-Arten)

	//Konstruktor
	public Tetromino(int[][][] matrix, int id, int rotationSteps, int width, int height) {
		this.tetrominoMatrix = matrix;
		this.id = id;
		this.rotationSteps = rotationSteps;
		this.width = width;
		this.height = height;
	}
	public Tetromino(Tetromino other) {
		this.tetrominoMatrix = other.tetrominoMatrix;
		this.id = other.id;
		this.rotationSteps = other.rotationSteps;
		this.width = other.width;
		this.height = other.height;
		this.rotation = other.rotation;
	}

	public void rotateRight() {
		//Bsp:  Es gibt 4 RotationSteps. Wenn rotation bei 2(180°), kann die Rotation auf 3(270°) gesetzt werden.
		//      Wenn rotation aber 3(270°), wird die rotation auf 0(0°) gesetzt, da man sonst bei 360° ist.
		if(rotation < rotationSteps-1) {
			rotation++;
		}
		else {
			rotation = 0;
		}
	}
	public void rotateLeft() {
		//Bsp:  Es gibt 4 RotationSteps. Wenn rotation bei 1(90°), kann die Rotation auf 0(0°) gesetzt werden.
		//      Wenn rotation aber 0(0°), muss die Rotation auf 3(270°) gesetzt werden, da man sonst negativ wird.
		if(rotation > 0) {
			rotation--;
		}
		else {
			rotation = rotationSteps-1;
		}
	}
	public void reset(int posXnew, int posYnew) {
		//auf Standardrotierung zurücksetzen und auf bestimmte Position setzen
		this.posX = posXnew;
		this.posY = posYnew;
		this.rotation = 0;
	}

	public int[][] toArray() {
		//Verwandelt den 3-dimensionalen Array in einen 2-dimensionalen Array
		//(aktuelle Rotierung wird angenommen und andere RotierungSteps-Ebenen werden weggelassen)
		int[][] tetrominoMatrixToArray = new int[height][width];

		for(int y = 0; y < height; y++) {   //Tetromino-Höhe
			for(int x = 0; x < width; x++) {    //Tetromino-Breite
				tetrominoMatrixToArray[y][x] = tetrominoMatrix[rotation][y][x];
			}
		}

		return tetrominoMatrixToArray;
	}

	@Override
	public String toString() {
		StringBuilder tetrominoMatrixString = new StringBuilder();

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tetrominoMatrixString.append(tetrominoMatrix[rotation][y][x]);
			}
			tetrominoMatrixString.append("\n");
		}
		//.toString ist die toString-Methode des StringBuilders
		return tetrominoMatrixString.toString();
	}

	//Getter
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getId() {
		return id;
	}

	//Setter
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
}