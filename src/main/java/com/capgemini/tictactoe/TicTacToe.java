package com.capgemini.tictactoe;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class TicTacToe {
	static Scanner sc = new Scanner(System.in);
	private static char choice;
	private static int pos;
	private static int position;
	private static int trueHint = 0;

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
		printBoard(board);
		char compLetter = (choice == 'O') ? 'X' : 'O';
		int posComputer = (int) (9 * (Math.random())) + 1;
		int cornerPos[] = { 1, 3, 7, 9, 5 };
		posComputer = Arrays.stream(cornerPos).filter(i -> board[i] == ' ').findFirst().getAsInt();
		if (posComputer != 1 || posComputer != 3 || posComputer != 7 || posComputer != 9 || posComputer != 5) {
			while (board[posComputer] != ' ' && !checkDraw(board)) {
				posComputer = (int) (9 * (Math.random())) + 1;
			}
			if (board[posComputer] == ' ' || trueHint != 0) {
				board[posComputer] = compLetter;
			}
		}
		if (trueHint != 0)
			board[trueHint] = compLetter;

		printBoard(board);
		if ((coin == 0)) {
			System.out.println("Choose Position");
			pos = sc.nextInt();
			board[pos] = choice;
			printBoard(board);
		}
		return board;

	}

	private static void printBoard(char[] board) {
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
		int pos;
		if (coin == 1) {
			System.out.println("Choose position");
			pos = sc.nextInt();
		} else
			pos = 0;

		board = TicTacToe.choiceBoard(board, choice, pos, coin);
		return board;
	}

	private static boolean checkWin(char[] board) {
		for (int a = 0; a < 8; a++) {
			StringBuilder line = null;
			StringBuilder winx = new StringBuilder().append(choice).append(choice).append(choice);
			StringBuilder canWinX = new StringBuilder().append(choice).append(choice);
			char compLetter = (choice == 'O') ? 'X' : 'O';
			StringBuilder canWinO = new StringBuilder().append(compLetter).append(compLetter);
			StringBuilder wino = new StringBuilder().append(compLetter).append(compLetter).append(compLetter);
			int hint = 0;
			switch (a) {
			case 0:
				line = new StringBuilder().append(board[1]).append(board[2]).append(board[3]);
				hint = 0;
				break;
			case 1:
				line = new StringBuilder().append(board[4]).append(board[5]).append(board[6]);
				hint = 1;
				break;
			case 2:
				line = new StringBuilder().append(board[7]).append(board[8]).append(board[9]);
				hint = 2;
				break;
			case 3:
				line = new StringBuilder().append(board[1]).append(board[4]).append(board[7]);
				hint = 3;
				break;
			case 4:
				line = new StringBuilder().append(board[2]).append(board[5]).append(board[8]);
				hint = 4;
				break;
			case 5:
				line = new StringBuilder().append(board[3]).append(board[6]).append(board[9]);
				hint = 5;
				break;
			case 6:
				line = new StringBuilder().append(board[1]).append(board[5]).append(board[9]);
				hint = 6;
				break;
			case 7:
				line = new StringBuilder().append(board[3]).append(board[5]).append(board[7]);
				hint = 7;
				break;
			}
			if (line.toString().equals(winx.toString()) || line.toString().equals(wino.toString())) {
				if (line.toString().equals(winx.toString()))
					System.out.println("Player Win");
				else
					System.out.println("Computer Win");
				return true;
			}
			// System.out.println(line.toString().replaceAll("\\s+", ""));
			if (line.toString().replaceAll("\\s+", "").equals(canWinO.toString())
					|| line.toString().replaceAll("\\s+", "").equals(canWinX.toString())) {
				position = line.lastIndexOf(" ");
				if (hint == 0 || hint == 1 || hint == 2)
					trueHint = 3 * hint + position + 1;
				else if (hint == 3 || hint == 4 || hint == 5)
					trueHint = 3 * position + hint - 2;
				else if (hint == 6)
					trueHint = 1 + 4 * position;
				else if (hint == 7)
					trueHint = 3 + 2 * position;
				// System.out.println("hint" + trueHint);

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
		}

		while (!checkWin(board) && !checkDraw(board) && coinwin == 0) {
			choice = 'O';
			board = ticTacToe.userChoice(board, coinwin);
		}
		if (checkDraw(board))
			System.out.println("draw");
	}
}
