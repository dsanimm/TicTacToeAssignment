package com.capgemini.tictactoe;

import java.util.Scanner;

public class TicTacToe {
	static Scanner sc = new Scanner(System.in);
	private static char choice;
	private static int pos;

	public TicTacToe() {
		char board[] = new char[11];
		for (int i = 1; i < 11; i++)
			board[i] = ' ';
	}

	private char[] createBoard() {
		char board[] = new char[11];
		for (int i = 1; i < 11; i++)
			board[i] = ' ';
		return board;
	}

	private static char[] choiceBoard(char[] board, char choice, int pos, int coin) {
		if (coin == 1)
			board[pos] = choice;
		char compLetter = (choice == 'O') ? 'X' : 'O';
		int posComputer = (int) (9 * (Math.random())) + 1;
		while (board[posComputer] != ' ' && !checkDraw(board)) {
			posComputer = (int) (9 * (Math.random())) + 1;
		}
		if (board[posComputer] == ' ') {
			board[posComputer] = compLetter;
		}
		if (coin == 0)
			board[pos] = choice;
		return board;

	}

	private void printBoard(char[] board) {
		for (int i = 1; i < 10; i++) {
			System.out.print("[" + board[i] + "]");
			if (i % 3 == 0) {
				System.out.println();
				System.out.println("---------");
			}
		}
	}

	private char[] userChoice(char[] board, int coin) {
		if (choice != 'X' && choice != 'O') {
			System.out.println("Choose X or O");
			choice = sc.next().charAt(0);
		}
		System.out.println("Choose position");
		int pos = sc.nextInt();
		if (board[pos] == ' ')
			board = TicTacToe.choiceBoard(board, choice, pos, coin);
		else
			System.out.println("Wrong Choice");
		return board;
	}

	private static boolean checkWin(char[] board) {
		for (int a = 0; a < 8; a++) {
			StringBuilder line = null;
			StringBuilder winx = new StringBuilder().append(choice).append(choice).append(choice);
			char compLetter = (choice == 'O') ? 'X' : 'O';
			StringBuilder wino = new StringBuilder().append(compLetter).append(compLetter).append(compLetter);
			switch (a) {
			case 0:
				line = new StringBuilder().append(board[1]).append(board[2]).append(board[3]);
				break;
			case 1:
				line = new StringBuilder().append(board[4]).append(board[5]).append(board[6]);
				break;
			case 2:
				line = new StringBuilder().append(board[7]).append(board[8]).append(board[9]);
				break;
			case 3:
				line = new StringBuilder().append(board[1]).append(board[4]).append(board[7]);
				break;
			case 4:
				line = new StringBuilder().append(board[2]).append(board[5]).append(board[8]);
				break;
			case 5:
				line = new StringBuilder().append(board[3]).append(board[6]).append(board[9]);
				break;
			case 6:
				line = new StringBuilder().append(board[1]).append(board[5]).append(board[9]);
				break;
			case 7:
				line = new StringBuilder().append(board[3]).append(board[5]).append(board[7]);
				break;
			}
			if (line.toString().equals(winx.toString()) || line.toString().equals(wino.toString())) {
				if (line.toString().equals(winx.toString()))
					System.out.println("Player Win");
				else
					System.out.println("Computer Win");

				return true;
			}
		}
		return false;
	}

	private static boolean checkDraw(char[] board) {
		boolean flag = true;
		for (int i = 1; i < board.length; i++) {
			if (board[i] == ' ')
				flag = false;
		}

		if (flag == true)
			System.out.println("Draw");

		return flag;
	}

	public static void main(String[] args) {
		TicTacToe ticTacToe = new TicTacToe();
		char[] board = ticTacToe.createBoard();
		ticTacToe.printBoard(board);
		System.out.println("choose heads or tails");
		String coin = sc.next();
		int randToss = (int) (2 * Math.random());
		int coinwin = 0;
		System.out.println(randToss);
		if (coin.equals("head") && randToss == 0) {
			System.out.println("heads. Player plays first");
			coinwin = 1;
		} else if (coin.equals("tail") && randToss == 0) {
			System.out.println("heads. Computer plays first");
			coinwin = 0;
		} else if (coin.equals("tail") && randToss == 1) {
			System.out.println("tails. Player plays first");
			coinwin = 1;
		} else if (coin.equals("head") && randToss == 1) {
			System.out.println("tails. Computer plays first");
			coinwin = 0;
		}
		while (!checkWin(board) && !checkDraw(board) && coinwin == 1) {

			choice = 'X';
			board = ticTacToe.userChoice(board, coinwin);
			ticTacToe.printBoard(board);
		}

		while (!checkWin(board) && !checkDraw(board) && coinwin == 0) {
			choice = 'O';
			board = ticTacToe.userChoice(board, coinwin);
			ticTacToe.printBoard(board);
		}
		if (checkDraw(board))
			System.out.println("draw");
	}
}
