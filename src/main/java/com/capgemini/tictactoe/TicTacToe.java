package com.capgemini.tictactoe;

import java.util.Scanner;

public class TicTacToe {
	static Scanner sc = new Scanner(System.in);
	private static char choice;

	public TicTacToe() {
		char board[] = new char[11];
		for (int i = 1; i < 11; i++)
			board[i] = ' ';
	}

	public static void main(String[] args) {
		TicTacToe ticTacToe = new TicTacToe();
	}

	private static char[] createBoard() {
		char board[] = new char[11];
		for (int i = 1; i < 11; i++)
			board[i] = ' ';
		return board;
	}

	private static char[] choiceBoard() {
		System.out.println("Choose X or O");
		choice = sc.next().charAt(0);
	}

	public static void main(String[] args) {
		TicTacToe obj = new TicTacToe();
		char[] board = obj.createBoard();

		System.out.println("Choose position for X or O");
		int pos = sc.nextInt();
		board = obj.choiceBoard();

	}

}