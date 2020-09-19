package com.skilldistillery.blackjack;

import java.util.Scanner;

public class BlackjackApp {

	public static void main(String[] args) {
		BlackjackApp app = new BlackjackApp();
		app.run();

	}

	private void run() {
		Scanner sc = new Scanner(System.in);
		BlackjackGame game = new BlackjackGame(sc);
		System.out.println("Welcome to Blackjack!");
		game.setupBasicGame();
		game.beginGame();
		
	}

}
