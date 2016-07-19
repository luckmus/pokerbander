package ru.pokerbender;

public class Card implements Comparable<Card>{
	public enum SUIT{
		Hearts,
		Diamonds,
		Clubs,
		Spades
	}
	
	public enum CardWeight{
		Ace (14),
		King (13),
		Dame (12),
		Jack (11),
		Ten (10),
		Nine (9),
		Eight (8), 
		Seven (7),
		Six (6),
		Five (5),
		Four (4),
		Three (3),
		Two (2),
		One (1);
		
		private int weight;

		private CardWeight(int weight) {
			this.weight = weight;
		}

		public int getWeight() {
			return weight;
		}
	
		
	}
	
	private SUIT suit;
	private CardWeight weight;
	
	public SUIT getSuit() {
		return suit;
	}
	public void setSuit(SUIT suit) {
		this.suit = suit;
	}
	public CardWeight getWeight() {
		return weight;
	}
	public void setWeight(CardWeight weight) {
		this.weight = weight;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		if (suit != other.suit)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	@Override
	public int compareTo(Card o) {
		return Integer.compare(weight.weight, o.weight.weight);
	}
	@Override
	public String toString() {
		return "Card [suit=" + suit + ", weight=" + weight + "]";
	}
	
	
	
}
