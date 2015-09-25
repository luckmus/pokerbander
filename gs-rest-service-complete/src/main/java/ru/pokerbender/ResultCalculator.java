package ru.pokerbender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import ru.pokerbender.Result.Combination;
import ru.pokerbender.Card.CardWeight;
import ru.pokerbender.Card.SUIT;;

public class ResultCalculator {

	private static class SuitComparator implements Comparator<Card>{

		@Override
		public int compare(Card o1, Card o2) {
			// TODO Auto-generated method stub
			return o1.getSuit().compareTo(o2.getSuit());
		}
		
	}
	
	private List<Card> cards = new ArrayList();
	private List<Card> playerCards = new ArrayList();
	private List<Card> riverCards = new ArrayList();
	
	public ResultCalculator(Collection<Card> riverCards, List<Card> playerCards) {
		super();

		this.playerCards.addAll(playerCards);
		this.riverCards.addAll(riverCards);
		this.cards.addAll(playerCards);
		this.cards.addAll(riverCards);
	}

	public Set<Result> getCombinations(){
		return null;
	}
	
	protected Card getMaxCard(Collection<Card> crdsCol){
		return Collections.max(crdsCol);
	}
	
	protected Card getCardByWeigth(){
		return Collections.max(cards);
	}
	
	private List<Card> getOneSutedCards(SUIT suit){
		List<Card> res = new ArrayList();
		for (Card c:cards){
			if (c.getSuit().equals(suit)){
				res.add(c);
			}
		}
		return res;
	}
	
	private Card findCardByWeight(Collection<Card> cardCol, CardWeight weight){
		for(Card c:cardCol){
			if (c.getWeight().equals(weight)){
				return c;
			}
		}
		return null;
	}
	
	private Collection<Card> findCardsByWeight(Collection<Card> cardCol, CardWeight weight){
		Collection<Card> res = new ArrayList();
		for(Card c:cardCol){
			if (c.getWeight().equals(weight)){
				res.add(c);
			}
		}
		return res;
	}
	
	public Result findFlashRoyalBySuit(SUIT suit){
		List<Card> oneSuited = getOneSutedCards(suit);
		if (oneSuited.size()<5){
			return null;
		}
		Card maxCard = getMaxCard(oneSuited);
		if (!maxCard.getWeight().equals(CardWeight.Ace)){
			return null;
		}
		if (findCardByWeight(oneSuited, CardWeight.King)==null){
			return null;
		}
		if (findCardByWeight(oneSuited, CardWeight.Dame)==null){
			return null;
		}
		if (findCardByWeight(oneSuited, CardWeight.Jack)==null){
			return null;
		}
		if (findCardByWeight(oneSuited, CardWeight.Ten)==null){
			return null;
		}
		return new Result(Combination.FlashRoyal, getMaxCard(playerCards));
	}
	
	public Result findStreetFlash(SUIT suit){
		List<Card> oneSuited = getOneSutedCards(suit);
		if (oneSuited.size()<5){
			return null;
		}
		int lastWeight = -1;
		Collections.sort(oneSuited);
		for (Card c: oneSuited){
			if (lastWeight == -1){
				lastWeight = c.getWeight().getWeight();
				continue;
			}
			if (c.getWeight().getWeight()==lastWeight){
				continue;
			}else if ((c.getWeight().getWeight()-lastWeight) != 1){
				return null;
			}
			
			lastWeight = c.getWeight().getWeight();
		}
		Card maxCard = getMaxCard(oneSuited);
		return new Result(Combination.StreetFlash,  getMaxCard(playerCards));
	}
	
	//TODO: написать стальное колесо, младший стрит флаш
	
	private Result findCardsCount(Collection<Card> cardsCol, int neccessaryCount, Combination resCombination){
		Collection<Card> ace = findCardsByWeight(cardsCol, CardWeight.Ace);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.King);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Dame);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Jack);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Ten);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Nine);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Eight);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Seven);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Six);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Five);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Four);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Three);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.Two);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		ace = findCardsByWeight(cardsCol, CardWeight.One);
		if (ace.size()==neccessaryCount){
			return new Result(resCombination, getMaxCard(ace));
		}
		return null;
	}
	
	public Result findQuads(){
		return findCardsCount(cards, 4, Combination.Quads);
	}
	
	public Result findFullHouse(){
		Result res = findCardsCount(cards, 3, Combination.FullHouse);
		if (res==null){
			return null;
		}
		List<Card> cardsCopy = new ArrayList();
		cardsCopy.addAll(cards);
		//удалю первую тройку
		for(int i=0; i<cardsCopy.size(); i++){
			Card c = cardsCopy.get(i);
			if (c.getWeight().equals(res.getHighestCard().getWeight())){
				cardsCopy.remove(i);
				i--;
			}
		
		}
		Result res1 = findCardsCount(cardsCopy, 2, Combination.Pair);
		if (res1==null){
			return null;
		}
		return new Result(Combination.FullHouse, res.getHighestCard());
	}
	
	
	public Result findFlush(SUIT suit){
		Collection<Card> res = getOneSutedCards(suit);
		if (res.size()<5){
			return null;
		}
		return new Result(Combination.Flush, getMaxCard(res));
	}
	
	public Result findStraight(){
		int lastWeight = -1;
		int counter = 0;
		//Set<Card> cardsSorted = new TreeSet();
		//cardsSorted.addAll(cards);
		List<Card> cardsSorted = new ArrayList();
		cardsSorted.addAll(cards); 
		Collections.sort(cardsSorted);
		for (Card c: cardsSorted){
			if (lastWeight == -1){
				lastWeight = c.getWeight().getWeight();
				counter++;
				continue;
			}
			if (c.getWeight().getWeight() == lastWeight){
				continue;
			}
			if ((c.getWeight().getWeight()-lastWeight) != 1){
				counter = 0;
			}else{
				counter++;
			}
			
			lastWeight = c.getWeight().getWeight();
		}
		if (counter<5){
			return null;
		}
		return new Result(Combination.Straight, getMaxCard(cards));
	}
	
	public Result findSet(){
		return findCardsCount(cards,  3, Combination.Set);
	}
	
	public Result findDoublePair(){
		Result res = findCardsCount(cards, 2, Combination.Pair);
		if (res==null){
			return null;
		}
		List<Card> cardsCopy = new ArrayList();
		cardsCopy.addAll(cards);
		//удалю первую пару
		for(int i=0; i<cardsCopy.size(); i++){
			Card c = cardsCopy.get(i);
			if (c.getWeight().equals(res.getHighestCard().getWeight())){
				cardsCopy.remove(i);
				i--;
			}
		
		}
		Result res1 = findCardsCount(cardsCopy, 2, Combination.Pair);
		if (res1==null){
			return null;
		}
		return new Result(Combination.DoublePair, res.getHighestCard());
	}
	
	public Result findPair(){
		return findCardsCount(cards, 2, Combination.Pair);
	}
	
	public Result findKikker(){
		return new Result(Combination.Kikker, getMaxCard(playerCards));
	}
	
}
