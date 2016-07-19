package ru.pokerbender;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import ru.pokerbender.Card.SUIT;

public class Player implements Comparable<Player>{
	private String guid = UUID.randomUUID().toString().toUpperCase();
	private String name;
	private int sum;
	private Card[] cards = new Card[2];
	public boolean dealer;
	
	public String getGuid() {
		return guid;
	}
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
	
	public Set<Result> getResultCombinations(Collection<Card> river){
		Set<Result> res = new TreeSet();
		//ResultCalculator resCalc = (ResultCalculator) Application.ac.getBean("resultCalculator");
		ResultCalculator resCalc = new  ResultCalculator(river, Arrays.asList(cards));
		
		Result r = resCalc.findPair();
		if (r!=null){ res.add(r); }		
		
		r = resCalc.findDoublePair();
		if (r!=null){ res.add(r); }
		
		r = resCalc.findSet();
		if (r!=null){ res.add(r); }
		
		r = resCalc.findStraight();
		if (r!=null){ res.add(r); }
		
		for (SUIT s:SUIT.values()){
			Result r1 = resCalc.findFlush(s);
			if (r1!=null){ res.add(r1); }		
		}
		
		r = resCalc.findFullHouse();
		if (r!=null){ res.add(r); }
		
		r = resCalc.findQuads();
		if (r!=null){ res.add(r); }
		
		
		for (SUIT s:SUIT.values()){
			Result r1 = resCalc.findStraightFlush(s);
			if (r1!=null){ res.add(r1); }		
		}
		
		for (SUIT s:SUIT.values()){
			Result r1 = resCalc.findFlushRoyalBySuit(s);
			if (r1!=null){ res.add(r1); }		
		}
		
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
