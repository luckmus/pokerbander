package ru.pokerbender;

import static org.junit.Assert.*;
//import hello.ApplicationTests.ac;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.Ignore;

import org.junit.Test;

import ru.pokerbender.Card;
import ru.pokerbender.Player;
import ru.pokerbender.Result;
import ru.pokerbender.Result.Combination;
import ru.pokerbender.ResultCalculator;

public class PlayerTest {

	@Test@Ignore
	public void testGetResultCombinationsDoublePair() {
		Player p = (Player) ApplicationTests.ac.getBean("player1");
		Set<Result> res = p.getResultCombinations((Collection<Card>) ApplicationTests.ac.getBean("river1"));
		assert(res.size()==1);
		assert(res.iterator().next().getCombination() == Combination.DoublePair);
	}
	
	@Test
	public void testGetResultCombinationsFullHouse() {
		Player p = (Player) ApplicationTests.ac.getBean("player2");
		Set<Result> res = p.getResultCombinations((Collection<Card>) ApplicationTests.ac.getBean("river2"));
		System.out.println(res);
	}
	
	@Test
	public void testGetResultCombinationsaight() {
		Player p = (Player) ApplicationTests.ac.getBean("player2");
		Collection<Card> col = (Collection<Card>) ApplicationTests.ac.getBean("river3");
		Set<Result> res = p.getResultCombinations(col);
		System.out.println(res);
		assertTrue(res.size()==2);
		Iterator<Result> iter = res.iterator(); 
		assertTrue(iter.next().getCombination() == Combination.Straight);
		assertTrue(iter.next().getCombination() == Combination.Set);
		
	}

}
