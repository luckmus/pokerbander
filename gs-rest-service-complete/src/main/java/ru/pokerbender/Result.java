package ru.pokerbender;

import java.util.Comparator;

public class Result {
	public enum Combination{
		FlashRoyal (10),
		StreetFlash (9),
		Kare (8),
		FullHouse (7),
		Flash (6),
		Street (5),
		Set (4),
		DoublePair (3),
		Pair (2),
		Kikker (1);
		
		private int weigth;

		private Combination(int weigth) {
			this.weigth = weigth;
		}
		
	}
	
	public static class CombinationComparator implements Comparator<Combination>{

		@Override
		public int compare(Combination o1, Combination o2) {
			// TODO Auto-generated method stub
			return Integer.compare(o1.weigth, o2.weigth);
		}
		
	}
	
	
	private Combination combination;
	private Card highestCard;
	
	
	
	public Combination getCombination() {
		return combination;
	}
	
	public Result(Combination combination, Card highestCard) {
		super();
		this.combination = combination;
		this.highestCard = highestCard;
	}

	
	
	public Result() {
		super();
	}

	public void setCombination(Combination combination) {
		this.combination = combination;
	}
	public Card getHighestCard() {
		return highestCard;
	}
	public void setHighestCard(Card highestCard) {
		this.highestCard = highestCard;
	}

	@Override
	public String toString() {
		return "Result [combination=" + combination + ", highestCard=" + highestCard + "]";
	}
	
	
}
