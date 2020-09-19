package com.skilldistillery.blackjack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.skilldistillery.common.cards.*;


public class BlackjackGame extends Game {
	private int minDeckSize = 32;
	
	private BlackjackDealer bjDealer;
	private Map<Player, Integer> roundScores;
	private BlackjackPlayer dealerPlayer;
	private int dealerRoundScore;

	public BlackjackGame(Scanner scanner) {
		super(scanner);
		bjDealer = new BlackjackDealer();
		roundScores = new HashMap<>();
		dealer = bjDealer;
		dealerPlayer = bjDealer.getDealerAsPlayer();
	}
	
	
	
	public void setupBasicGame() {
		System.out.println("Please enter your name: ");
		String name = sc.nextLine();
		Player p1 = new BlackjackPlayer(true, 0, name, false);
		addPlayer(p1);
		dealer.setPlayers(playersList);
		
	}
	
	public void beginGame() {
		boolean keepGoing = true;
		while (keepGoing) {
			beginRound();
			boolean validInput = false;
			while (!validInput) {
				System.out.println("Continue (c) or Quit (q)?");
				String opt = sc.nextLine().toLowerCase();
				switch (opt) {
				case "c":
					keepGoing = true;
					validInput = true;
					break;
				case "q":
					keepGoing = false;
					validInput = true;
					break;
				default:
					System.out.println("Invalid input");
					validInput = false;
					break;
				}
			}
		}
	}
	
	public void beginRound() {
		bjDealer.checkReshuffle(minDeckSize);
		bjDealer.dealInitial();
//		displayAllCards();
		for (Player player : playersList) {
			takeTurn((BlackjackPlayer)player);
		}
		takeTurn(dealerPlayer);
		scoreRound();
		dealer.discardAll();
		bjDealer.checkReshuffle(minDeckSize);
		

	}
	public void scoreRound() {
		System.out.println("Dealer has " + dealerPlayer.getHand());
		for (Player player : playersList) {
			int bet = 1;
			int playerScore = roundScores.get(player);
			if (playerScore > dealerRoundScore) {
				System.out.println(player.getName() + " won!");
				addScore(player, bet);
			} else if (playerScore == dealerRoundScore) {
				System.out.println(player.getName() + " and dealer tied!");
			} else {
				System.out.println(player.getName() + " lost to dealer!");
				addScore(player, (bet * -1));
			}
		}
	}
	public void takeTurn(BlackjackPlayer p) {
		boolean stay = false;
		while (p.canHit() && !stay) {
			displayAllCards();
			stay = hitOrStay(p);
		}
		scorePlayer(p);
	}
	public void scorePlayer(BlackjackPlayer p) {
		int score = p.getHand().getHandValue();
		if (score > 21) {
			score = -1;
		}
		if (p.isDealer()) {
			dealerRoundScore = score;
			return;
		}
		roundScores.put(p, score);
		System.out.println(p.getName() + " got " + p.getScoreString());
	}
	
	public void displayAllCards() {
		for (Player player : playersList) {
			String score = ((BlackjackPlayer) player).getScoreString();
			System.out.println(player.getName() + " has " + score + " " + player.getHand());
		}
		System.out.println(dealerPlayer.getName() + " has unknown card and " + bjDealer.getFaceUpCards());
	}
	
	
	
	
//	public String getPlayerInput(BlackjackPlayer p) {
//		System.out.println(p.getName() + ", what would you like to do?");
//		return sc.nextLine().toLowerCase();
//	}
	
	public boolean hitOrStay(BlackjackPlayer p) {
		boolean stay = false;
		if (p.isHuman()) {
			System.out.println(p.getName() + ", what would you like to do?");
			boolean keepGoing = true;
			while (keepGoing) {
				System.out.println("Options: hit or stay");
				String opt = sc.nextLine().toLowerCase();
				switch (opt) {
				case "hit":
					bjDealer.hitPlayer(p);
					keepGoing = false;
					break;
				case "stay":
					keepGoing = false;
					stay = true;
					break;
				default:
					System.out.println("Invalid input");
					break;
				}
			}
		} else if (p.isDealer() || !(p.isHuman())) {
			int score = p.scoreHand();
			if (score == 21) {
				System.out.println(p.getName() + ": Blackjack!");
			} else if (score >= 17) {
				System.out.println(p.getName() + ": Stay");
				stay = true;
			} else {
				bjDealer.hitPlayer(p);
			}
		}
		return stay;
	}
	
}
