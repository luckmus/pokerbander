/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.pokerbender;

import static org.junit.Assert.assertNotNull;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.pokerbender.ResultCalculator;
import ru.pokerbender.Card.CardWeight;
import ru.pokerbender.Card.SUIT;
import ru.pokerbender.Result;
import ru.pokerbender.Result.Combination;


@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class ApplicationTests {
	public static ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"testConfig.xml"});

	@Value("${local.server.port}")
	private int port = 0;

	@Before
	public void init() throws Exception {
		//ac = new ClassPathXmlApplicationContext(new String[] {"testConfig.xml"});
	}

 
	
	@Test
	public void testPair() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("pairTest1");
		Result res = rc.findPair();
		System.out.println(res);
		assert(res.getCombination()==Combination.Pair);
	}
	
	@Test
	public void testDoublePair1() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("doublePairTest1");
		Result res = rc.findDoublePair();
		assertNotNull(res);
		assert(res.getCombination()==Combination.DoublePair);
		assert(res.getHighestCard().getWeight()==CardWeight.Three);
		System.out.println(res);
	}
	
	@Test
	public void testDoublePair2() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("quadsTest1");
		Result res = rc.findDoublePair();
		assert(res==null);
	}
	
	@Test
	public void testFullHouse() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("fullHouseTest1");
		Result res = rc.findFullHouse();
		assertNotNull(res);
		assert(res.getCombination()==Combination.FullHouse);
		System.out.println(res);
	}
	
	@Test
	public void testKikker() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("kikkerTest1");
		Result res = rc.findKikker();
		assertNotNull(res);
		assert(res.getCombination()==Combination.Kikker);
		assert(res.getHighestCard().getWeight()==CardWeight.Four);
		System.out.println(res);
	}
	
	@Test
	public void testSet() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("setTest1");
		Result res = rc.findSet();
		assertNotNull(res);
		assert(res.getCombination()==Combination.Set);
		System.out.println(res);
	}
	
	@Test
	public void testStraight() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("streetTest1");
		Result res = rc.findStraight();
		assertNotNull(res);
		assert(res.getCombination()==Combination.Straight);
		System.out.println(res);
	}
	
	@Test
	public void testFlus1() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("flushTest1");
		Result res = rc.findFlush(SUIT.Hearts);
		assertNotNull(res);
		assert(res.getCombination()==Combination.Flush);
		System.out.println(res);
	}
	
	@Test
	public void testFlush2() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("flushTest2");
		Result res = rc.findFlush(SUIT.Hearts);
		assert(res==null);
		rc= (ResultCalculator) ac.getBean("flushTest2");
		res = rc.findFlush(SUIT.Clubs);
		assert(res.getCombination()==Combination.Flush);
		System.out.println(res);
	}
	
	@Test
	public void testQuads() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("quadsTest1");
		Result res = rc.findQuads();
		assertNotNull(res);
		assert(res.getCombination()==Combination.Quads);
		System.out.println(res);
	}
	
	@Test
	public void testStreetFlush1() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("straightFlushTest1");
		Result res = rc.findStraightFlush(SUIT.Hearts);
		assertNotNull(res);
		assert(res.getCombination()==Combination.StraihgtFlush);
		assert(res.getHighestCard().getSuit()==SUIT.Hearts);
		System.out.println(res);
	}
	
	@Test
	public void testStreetFlush2() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("straightFlushTest2");
		Result res = rc.findStraightFlush(SUIT.Hearts);
		assert(res==null);
	}
	
	@Test
	public void testRoyalFlush1() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("royalFlushTest1");
		Result res = rc.findFlushRoyalBySuit(SUIT.Hearts);
		assertNotNull(res);
		assert(res.getCombination()==Combination.FlushRoyal);
		assert(res.getHighestCard().getSuit()==SUIT.Hearts);
		System.out.println(res);
	}

	@Test
	public void testRoyalFlush2() {
		ResultCalculator rc= (ResultCalculator) ac.getBean("royalFlushTest2");
		Result res = rc.findFlushRoyalBySuit(SUIT.Hearts);
		assert(res==null);

		rc= (ResultCalculator) ac.getBean("royalFlushTest2");
		res = rc.findPair();
		System.out.println(res);
		assert(res.getCombination()==Combination.Pair);
	}

}