package com.skilldistillery.common.cards;

import java.util.List;

public abstract class Dealer {
	protected Deck deck;
	protected List<Player> players;
	protected Pile discardPile;
	
	public Dealer() {
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public Card dealCard(Player player) {
		Card cardToDeal = deck.dealCard();
		player.getHand().addCard(cardToDeal);
		return cardToDeal;
	}
	public void dealCard(Player player, int numCards) {
		for (int i = 0; i < numCards; i++) {
			dealCard(player);
		}
	}
	public void discard(Hand h) {
		discardPile.addCards(h);
		h.clear();
	}
	public void discard(Player p) {
		discard(p.getHand());
	}
	public void discardAll() {
		for (Player player : players) {
			discard(player);
		}
	}
	
	public void returnDiscards() {
		deck.addCards(discardPile);
		discardPile.clear();
	}
	
	public void resetDeck() {
		discardAll();
		returnDiscards();
	}
	
	public void shuffleDeck() {
		deck.shuffle();
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	
}
