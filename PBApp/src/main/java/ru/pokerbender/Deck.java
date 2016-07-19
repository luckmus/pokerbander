package ru.pokerbender;

import java.util.List;

public class Deck {
	private List<Card> cards;

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public Card getNextCard(){
		int i = (int) (Math.abs(Math.random()) * cards.size());
		Card res = cards.get(i);
		cards.remove(i);
		return res;
	}
}
