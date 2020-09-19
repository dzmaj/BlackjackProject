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

	public int getNumCards() {
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
	public void addCards(CardGroup groupToAdd) {
		cards.addAll(groupToAdd.getCards());
	}
	public void clear() {
		cards.clear();
	}
	public void sortBySuit() {
		cards.sort(new CardSuitFirstComparator());
	}
	public void sortByRank() {
		cards.sort(new CardRankFirstComparator());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardGroup other = (CardGroup) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("");
		builder.append(cards);
		builder.append("");
		return builder.toString();
	}
	
	
}
