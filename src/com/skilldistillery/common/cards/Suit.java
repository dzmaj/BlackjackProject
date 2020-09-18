package com.skilldistillery.common.cards;

public enum Suit {
	SPADES("Spades"),
	HEARTS("Hearts"),
	CLUBS("Clubs"),
	DIAMONDS("Diamonds");
	
	Suit(String n) {
		name = n;
	}
	
	final private String name;
	
	@Override
	public String toString() {
		return name;
	}
	
}
