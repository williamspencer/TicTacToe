import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	// initializing variables to be used throughout the game
	static Random random = new Random();
	static int compX, compY;
	static char[][] board;
	static char currentPiece;
	static int moves;
	static boolean win;
	static Scanner scan1 = new Scanner(System.in);

	// creates TicTacToe class that initializes the blank playing board array
	public TicTacToe() {
		board = new char[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = '-';
	}

	// method that prints the current board
	public void printBoard() {
		System.out.println("\t0\t1\t2\n");
		for (int i = 0; i < 3; i++) {
			System.out.print("\n" + i);
			for (int j = 0; j < 3; j++)
				System.out.print("\t" + board[i][j]);
			System.out.println();
		}
	}

	// checks all rows for a win
	public static boolean checkRows() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != '-') {
				if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
					return true;
				}
			}
		}
		return false;
	}

	// checks all columns for a win
	public static boolean checkColumns() {
		for (int i = 0; i < 3; i++) {
			if (board[0][i] != '-') {
				if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
					return true;
				}
			}
		}
		return false;
	}

	// checks both diagonals for a win
	public static boolean checkDiag() {
		if (board[1][1] != '-') {
			return ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) == true
					|| (board[0][2] == board[1][1] && board[1][1] == board[2][0]) == true);
		}
		return false;
	}

	// returns true if a win is found in either a row, column or diagonal
	public boolean checkWinner() {
		return (checkRows() || checkColumns() || checkDiag());
	}

	// adds mark to a row/column that exists on the board and doesn't overwrite
	// a move
	public boolean placeMark(int row, int col) {
				if (board[row][col] == '-') {
					board[row][col] = currentPiece;
					return true;
				}
		System.out.println("That piece is already taken! Choose another space: ");
		changePiece();
		return false;
	}

	public static void getPCMove() {
		compX = random.nextInt(3);
		compY = random.nextInt(3);
		
		while (board[compX][compY] != '-'){
			compX = random.nextInt(3);
			compY = random.nextInt(3);
		}
	}
	// method that changes piece in-between turns
	public void changePiece() {
		if (currentPiece == 'X') {
			currentPiece = 'O';
		} else {
			currentPiece = 'X';
		}
	}

	public static int readValidInt() {
		
		while (!scan1.hasNextInt()) {
			scan1.nextLine();
			System.out.println("Please enter a valid integer: ");
		}
		int x = scan1.nextInt();
		return x;
	}

	public static int getRange(int min, int max) {

		int input = readValidInt();
		scan1.nextLine();
		while (input < min || input > max) {
			System.out.println("Please put a number within range!");
			input = readValidInt();
		}
		return input;
	}

	// main method that creates new instance of TicTacToe, starts new game
	public static void main(String[] args) {
		String user = "";

		// creates first loop and instance of game
		// as long as user agrees to another game
		do {
			win = false;
			moves = 0;
			currentPiece = 'X';
			TicTacToe game = new TicTacToe();

			// loops game until a winner is found
			while (!win) {
				int x, y;
				game.printBoard();
				if (currentPiece == 'X'){
				System.out.print("\nEnter X axis: ");
				x = getRange(0, 2);
				System.out.print("\nEnter Y axis: ");
				y = getRange(0, 2);
				game.placeMark(x, y);
				moves++;
				} else {
					System.out.println("\nComputer's move: \n");
				getPCMove();
				x = compX;
				y = compY;
				game.placeMark(x, y);
				moves++;
				}
				win = game.checkWinner();
				if (win)
					break;
				game.changePiece();
			}

			// if win or tie is found, board is printed with winner
			// asks player if the game is to be repeated or not
			game.printBoard();
			if (win) {
				System.out.println("\n" + currentPiece + " is our winner! Congratulations!");
			} else if (moves == 9) {
				System.out.println("We have a tie!");
			}
			System.out.print("Play again? (y/n): ");
			user = scan1.next();

		} while (user.equalsIgnoreCase("y"));
		System.out.println("Thank you for playing!");
		scan1.close();
	}
}
