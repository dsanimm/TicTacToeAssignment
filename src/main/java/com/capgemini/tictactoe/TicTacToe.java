package com.capgemini.tictactoe;

public class TicTacToe {

	public TicTacToe() {
		char board[] = new char[11];
		for (int i = 1; i < 11; i++)
			board[i] = ' ';
	}

	public static void main(String[] args) {
		TicTacToe ticTacToe = new TicTacToe();
	}
}