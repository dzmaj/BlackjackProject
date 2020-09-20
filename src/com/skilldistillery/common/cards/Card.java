package com.skilldistillery.common.cards;

public class Card implements Comparable<Card> {
	private Suit suit;
	private Rank rank;
	private int uCard;
	public Card(Suit suit, Rank rank) {
		super();
		this.suit = suit;
		this.rank = rank;
		this.uCard = setUCard();
	}
	private int setUCard() {
		Integer codePoint = 0x1f0a0;
		if (suit == Suit.SPADES) {
		} else if (suit == Suit.HEARTS) {
			codePoint += 0x10;
		} else if (suit == Suit.DIAMONDS) {
			codePoint += 0x20;
		} else if (suit == Suit.CLUBS) {
			codePoint += 0x30;
		}
		if (rank == Rank.ACE) {
			codePoint += 1;
		} else {
			codePoint += rank.ordinal() + 2;
		}
		
		return codePoint;
	}
	public String getCardSymString() {
		return String.valueOf(Character.toChars(uCard));
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
		return getCardSymString() + " " + rank + " of " + suit + suit.getSymbol();
	}
	public Suit getSuit() {
		return suit;
	}
	public Rank getRank() {
		return rank;
	}
	public int getRankValue() {
		return rank.getValue();
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
	public int getUCard() {
		return uCard;
	}


	
	
	
	
}
