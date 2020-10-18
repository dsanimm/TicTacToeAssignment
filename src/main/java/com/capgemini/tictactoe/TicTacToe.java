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
		// computer plays here
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

		if (coinwin == 1) {

			choice = 'X';
			board = ticTacToe.userChoice(board,coinwin);
			ticTacToe.printBoard(board);
		}

		else {
			choice = 'O';
			board = ticTacToe.userChoice(board,coinwin);
			ticTacToe.printBoard(board);
		}

	}

}