package com.skilldistillery.common.cards;

public abstract class Player {
	private int id;
	private String name;
	protected Hand hand;
	protected boolean human = false;
	public Player() {
	}
	
	public Player(boolean isHuman) {
		human = isHuman;
	}

	public Player(boolean isHuman, int id) {
		this(isHuman);
		this.id = id;
	}
	public Player(boolean isHuman, int id, String name) {
		this(isHuman);
		this.id = id;
		this.name = name;
	}
	
	public boolean isHuman() {
		return human;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
