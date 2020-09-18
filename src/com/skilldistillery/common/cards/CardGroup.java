package com.skilldistillery.common.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CardGroup {
	protected List<Card> cards;
	
	public CardGroup() {
		cards = new ArrayList<>();
	}
	public CardGroup(Card cardToAdd) {
		this();
		addCard(cardToAdd);
	}
	public CardGroup(List<Card> cardsToAdd) {
		this();
		addCards(cardsToAdd);
	}

	public int checkDeckSize() {
		return cards.size();
	}
	public Card dealCard(int position) {
		return cards.remove(position);
	}
	public Card dealCard() {
		return dealCard(0);
	}
	public void shuffle() {
		Collections.shuffle(cards);
	}
	public void addCard(Card cardToAdd) {
		cards.add(cardToAdd);
	}
	public void addCards(List<Card> cardsToAdd) {
		cards.addAll(cardsToAdd);
	}
}
