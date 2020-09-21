package com.skilldistillery.blackjack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.skilldistillery.common.cards.*;

public class BlackjackGame extends Game {
	private int minDeckSize = 20;
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

	// minimum requirements for game
	public void setupBasicGame() {
		System.out.println("Please enter your name: ");
		String name = sc.nextLine();
		Player p1 = new BlackjackPlayer(true, name, false);
		addPlayer(p1);
		dealer.setPlayers(playersList);
	}

	// set up basic game, then let user add more if wanted
	public void setupBigGame() {
		setupBasicGame();
		addPlayersMenu();
	}

	// prompt user to create human or ai players
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

	// let user add extra players
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
				break;
			default:
				// invalid
				System.out.println("Invalid input");
				break;
			}
		}
		// set the shoe size based on the number of players
		dealer.setPlayers(playersList);
		minDeckSize = (playersList.size() + 1) * 4;
		Deck shoe = bjDealer.getDeck();
		int shoeSize = 1;
		while (minDeckSize > shoe.getNumCards() / 4) {
			shoe.addCards(new Deck());
			shoeSize++;
			dealer.shuffleDeck();
		}
		// print how many decks in shoe
		System.out.println("Shoe has " + shoeSize + " decks");
	}

	// start the game
	public void beginGame() {
		boolean keepGoing = true;
		while (keepGoing) {
			// start a round
			beginRound();
			// ask if user wants to continue or not, loop if invalid input
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

	// begin a round
	public void beginRound() {
		// clear all the bets from previous round
		roundBets.clear();
		// check if deck needs to be reshuffled
		bjDealer.checkReshuffle(minDeckSize);
		// take initial bets from all human players
		takeAllBets();
		// deal out the initial cards to each player
		bjDealer.dealInitial();
		// have each player take their turns before the dealer, if all players bust then
		// dealer does not need to take turn
		int numBust = 0;
		for (Player player : playersList) {
			takeTurn((BlackjackPlayer) player);
			if (((BlackjackPlayer) player).getHand().isBust()) {
				numBust++;
			}
		}
		if (!(numBust == playersList.size())) {
			takeTurn(dealerPlayer);
		}
		// score the round
		scoreRound();
		// put everyone's cards into the discard pile
		dealer.discardAll();
		// show everyone's overall scores
		for (Player player : playersList) {
			System.out.println(player.getName() + " has a total score of " + scoresMap.get(player));
		}

	}

	// score a round
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

	// get initial bets from each player
	public void takeAllBets() {
		for (Player player : playersList) {
			if (player.isHuman()) {
				System.out.println(player.getName() + ", how much would you like to bet?");
				roundBets.put(player, sc.nextInt());
				sc.nextLine();
				((BlackjackPlayer)player).setHasDoubled(false);
			}
		}
	}

	// turn for a player
	public void takeTurn(BlackjackPlayer p) {
		boolean stay = false;
		// loop until player gets blackjack, busts, or stays
		while (p.canHit() && !stay) {
			// show all the cards again
			displayAllCards();
			// hitOrStay returns true if player chooses to stay
			stay = hitOrStay(p);
		}
		// get the score for the player
		scorePlayer(p);
	}

	// score a player and add to map of scores
	public void scorePlayer(BlackjackPlayer p) {
		int score = p.scoreHand();
		if (p.isDealer()) {
			dealerRoundScore = score;
			return;
		}
		roundScores.put(p, score);
		System.out.println(p.getName() + " got " + p.getScoreString());
	}

	// show all the cards, dealer has a hidden card
	public void displayAllCards() {
		for (Player player : playersList) {
			String score = ((BlackjackPlayer) player).getScoreString();
			System.out.println(player.getName() + " has " + score + " " + player.getHand());
		}
		System.out.println(dealerPlayer.getName() + " has unknown card and " + bjDealer.getFaceUpCards());
	}

	// all the options for an individual action a player can take
	public boolean hitOrStay(BlackjackPlayer p) {
		boolean stay = false;
		// display options for human players
		if (p.isHuman()) {
			System.out.println(p.getName() + ", what would you like to do?");
			boolean keepGoing = true;
			while (keepGoing) {
				// can only double down once
				if (!p.hasDoubled()) {
					System.out.println("Options: hit, stay, double");
				} else {
					System.out.println("Options: hit or stay");
				}
				String opt = sc.nextLine().toLowerCase();
				switch (opt) {
				case "hit":
					bjDealer.hitPlayer(p);
					keepGoing = false;
					// cannot double if you already hit
					p.setHasDoubled(true);
					break;
				case "stay":
					keepGoing = false;
					stay = true;
					break;
				case "double":
					// can only double down once
					if (!p.hasDoubled()) {
						int newBet = roundBets.get(p) * 2;
						System.out.println(p.getName() + " doubled down! (new bet is: " + newBet + ")");
						roundBets.put(p, newBet);
						p.setHasDoubled(true);
						bjDealer.hitPlayer(p);
						keepGoing = false;
					} else {
						System.out.println("Cannot double again!");
					}
					break;
				default:
					System.out.println("Invalid input");
					break;
				}
			}
		}
		// dealer and non-human players must follow rules
		else if (p.isDealer() || !(p.isHuman())) {
			int score = p.scoreHand();
			// blackjack
			if (score == 21) {
				System.out.println(p.getName() + ": Blackjack!");
			} else if (score >= 17) {
				// hit on soft 17, otherwise stay
				if (p.getHand().isSoft() && score == 17 && p.getHand().getBaseHandValue() == 17) {
					System.out.println(p.getName() + ": hitting (soft)");
					bjDealer.hitPlayer(p);
				}
				System.out.println(p.getName() + ": Stay");
				stay = true;
			}
			// otherwise hit
			else {
				bjDealer.hitPlayer(p);
			}
		}
		// return whether or not player has decided to stay
		return stay;
	}

}
