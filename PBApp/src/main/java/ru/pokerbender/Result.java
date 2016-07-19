package ru.pokerbender;

import java.util.Comparator;

public class Result implements Comparable<Result>{
	public enum Combination{
		FlushRoyal (10),
		StraihgtFlush (9),
		Quads (8),
		FullHouse (7),
		Flush (6),
		Straight (5),
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

	@Override
	public int compareTo(Result o) {
		int res = Integer.compare( o.combination.weigth, combination.weigth);
		if (res == 0){
			res = Integer.compare(o.highestCard.getWeight().getWeight(), highestCard.getWeight().getWeight());
		}
		return res;
	}
	
	
}
