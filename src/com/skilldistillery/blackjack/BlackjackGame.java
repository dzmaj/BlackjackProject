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
	private Map<Player, Integer> roundBets;

	public BlackjackGame(Scanner scanner) {
		super(scanner);
		bjDealer = new BlackjackDealer();
		roundScores = new HashMap<>();
		roundBets = new HashMap<>();
		dealer = bjDealer;
		dealerPlayer = bjDealer.getDealerAsPlayer();
	}

	public void setupBasicGame() {
		System.out.println("Please enter your name: ");
		String name = sc.nextLine();
		Player p1 = new BlackjackPlayer(true, name, false);
		addPlayer(p1);
		dealer.setPlayers(playersList);
	}

	public void setupBigGame() {
		setupBasicGame();
		addPlayersMenu();
	}
	
	public void playerCreator() {
		System.out.println("Enter the player's name: ");
		String name = sc.nextLine();
		System.out.println("Is this player human? (y/n)");
		boolean human = false;
		String opt = sc.nextLine();
		switch (opt) {
		case "y":
			human = true;
			break;
		case "n":
			human = false;
			break;
		default:
			System.out.println("Invalid input... assuming human");
			human = true;
			break;
		}
		addPlayer(new BlackjackPlayer(human, name, false));
		
		
	}

	public void addPlayersMenu() {
		boolean keepAdding = true;
		while (keepAdding) {
			System.out.println("Would you like to add another player? (y/n)");
			String opt = sc.nextLine().toLowerCase();
			switch (opt) {
			case "y":
				playerCreator();
				break;
			case "n":
				keepAdding = false;
				// done
			default:
				// invalid
				System.out.println("Invalid input");
				break;
			}
		}
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
		roundBets.clear();
		bjDealer.checkReshuffle(minDeckSize);
		bjDealer.dealInitial();

//		displayAllCards();
		for (Player player : playersList) {
			takeTurn((BlackjackPlayer) player);
		}
		takeTurn(dealerPlayer);
		scoreRound();
		dealer.discardAll();
		bjDealer.checkReshuffle(minDeckSize);
		for (Player player : playersList) {
			System.out.println(player.getName() + " has a total score of " + scoresMap.get(player));
		}

	}

	public void scoreRound() {
		System.out.println("Dealer has " + dealerPlayer.getScoreString() + " " + dealerPlayer.getHand());
		for (Player player : playersList) {
			int bet = 0;
			if (roundBets.containsKey(player)) {
				bet = roundBets.get(player);
			}
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
		if (p.isHuman()) {
			System.out.println("How much would you like to bet?");
			roundBets.put(p, sc.nextInt());
			sc.nextLine();
		}
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
				System.out.println("Options: hit, stay, bet");
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
				case "bet":
					System.out.println("How much would you like add to your bet?");
					int newBet = roundBets.get(p) + sc.nextInt();
					sc.nextLine();
					roundBets.put(p, newBet);
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
