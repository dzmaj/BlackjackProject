package com.skilldistillery.blackjack;

import com.skilldistillery.common.cards.*;

public class BlackjackDealer extends Dealer {
	private BlackjackPlayer dealerAsPlayer;
	

	public BlackjackDealer() {
		super();
		deck = new Deck();
		discardPile = new Pile();
		dealerAsPlayer = new BlackjackPlayer(false, 0, "Dealer", true);
		shuffleDeck();
	}

	public BlackjackPlayer getDealerAsPlayer() {
		return dealerAsPlayer;
	}

	public void dealInitial() {
		for (int i = 0; i < 2; i++) {
			for (Player player : players) {
				if (player == null) {
					continue;
				}
				dealCard(player);
			}
			dealCard(dealerAsPlayer);
		}
	}
	
	public boolean hitPlayer(BlackjackPlayer p) {
		System.out.println(p.getName() + ": Hits");
		dealCard(p);
		return false;
	}

	public boolean checkReshuffle(int minSize) {
		if (deck.getNumCards() < minSize) {
			System.out.println("Dealer is returning discards to deck...");
			returnDiscards();
			System.out.println("Dealer is shuffling deck...");
			shuffleDeck();
			return true;
		}
		return false;
	}
	
	public CardGroup getFaceUpCards() {
		Hand hand = dealerAsPlayer.getHand();
		Pile h = new Pile();
		h.addCards(hand);
		h.dealCard(0);
		return h;
	}
	
	@Override
	public void discardAll() {
		System.out.println("Dealer is discarding all cards");
		super.discardAll();
		discard(dealerAsPlayer);
	}

}
