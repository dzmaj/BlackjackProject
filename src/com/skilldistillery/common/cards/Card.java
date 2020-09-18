package com.skilldistillery.common.cards;

public class Card implements Comparable<Card> {
	private Suit suit;
	private Rank rank;
	public Card(Suit suit, Rank rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
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
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return rank + " of " + suit;
	}
	public Suit getSuit() {
		return suit;
	}
	public Rank getRank() {
		return rank;
	}
	
	@Override
	public int compareTo(Card otherCard) {
		if (this.rank.ordinal() > otherCard.rank.ordinal()) {
			return 1;
		} else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
			return -1;
		} else if (this.rank.ordinal() == otherCard.rank.ordinal()) {
			if (this.suit.ordinal() > otherCard.suit.ordinal()) {
				return 1;
			}
			if (this.suit.ordinal() < otherCard.suit.ordinal()) {
				return -1;
			} else if (this.suit.ordinal() == otherCard.suit.ordinal()) {
				return 0;
			}
		}
		return 0;
	}

	
	
	
	
}
