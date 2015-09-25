package ru.pokerbender;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Player implements Comparable<Player>{
	private String guid = UUID.randomUUID().toString().toUpperCase();
	private String name;
	private int sum;
	private Card[] cards = new Card[2];
	public boolean dealer;
	
	public boolean isDealer() {
		return dealer;
	}
	public void setDealer(boolean dealer) {
		this.dealer = dealer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSum() {
		return sum;
	}
	public synchronized void setSum(int sum) {
		this.sum = sum;
	}
	public Card[] getCards() {
		return cards;
	}
	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	public void addCard(Card card){
		cards[cards[0]==null?0:1] = card;
	}
	
	public int skip(){
		return 0;
	}
	
	public int pickUp(int value){
		return decSum(value);
	}
	
	public int support(int value){
		return decSum(value);
	}
	
	private synchronized int decSum(int value){
		if (value>sum){
			throw new IllegalArgumentException("Недостаточно денег");
		}
		sum -= value;
		return value;
	}
	
	public synchronized void incSum(int value){
		sum += value;
	}
	
	public synchronized int allIn(){
		int res = sum;
		sum = 0;
		return res;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guid == null) ? 0 : guid.hashCode());
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
		if (guid == null) {
			if (other.guid != null)
				return false;
		} else if (!guid.equals(other.guid))
			return false;
		return true;
	}
	@Override
	public int compareTo(Player arg0) {
		return guid.compareTo(arg0.guid);
	}
	
}
