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

	private static char[] createBoard() {
		char board[] = new char[11];
		for (int i = 1; i < 11; i++)
			board[i] = ' ';
		return board;
	}

	private static void choiceBoard() {
		System.out.println("Choose X or O");
		choice = sc.next().charAt(0);
	}

	public static void main(String[] args) {
		TicTacToe ticTacToe = new TicTacToe();
		char[] board = ticTacToe.createBoard();
		ticTacToe.choiceBoard();
		ticTacToe.printBoard(board);


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

}