package com.skilldistillery.common.cards;

import java.util.List;

public abstract class Dealer {
	private Deck deck;
	private List<Player> players;
	
	public Dealer() {
		
	}
	
	public void dealCard(Player player) {
		Card cardToDeal = deck.dealCard();
		player.getHand().addCard(cardToDeal);
	}
	
}
