package com.skilldistillery.common.cards;

public enum Suit {
	SPADES("Spades", "\u2660"),
	HEARTS("Hearts", "\u2661"),
	CLUBS("Clubs", "\u2663"),
	DIAMONDS("Diamonds", "\u2662");
	
	Suit(String n, String sym) {
		name = n;
		symbol = sym;
	}
	
	final private String name;
	final private String symbol;
	
	@Override
	public String toString() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	
}
