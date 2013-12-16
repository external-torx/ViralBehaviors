/*
 * Copyright (c) 2013 Halloran Parry, all rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.meleemistress.core;

import java.util.Random;

/**
 * @author hparry
 * 
 */
public class Attempt1 {

	int target;

	Pair lastPair;
	int score;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Attempt1 att = new Attempt1(100);
		while (att.score != 0) {
			System.out.println(String.format(
					"Current pair: %d, %d. Current score: %d",
					att.lastPair.first, att.lastPair.second, att.score));
			att.evaluate(att.generate(att.lastPair, att.score));
		}
		System.out.println(String.format(
				"Evaluation complete. Winning combination is %d, %d",
				att.lastPair.first, att.lastPair.second));
	}

	public Attempt1(int target) {
		this.target = target;
		Random generator = new Random();
		this.lastPair = new Pair(generator.nextInt(target),
				generator.nextInt(target));
		this.score = getScore(this.lastPair);
		evaluate(lastPair);
	}

	private Pair generate(Pair pair, int score) {
		Random generator = new Random();
		int mod1 = generator.nextInt(Math.abs(score)) + 1; //add the 1 so the modifier is always greater than 0
		int mod2 = generator.nextInt(Math.abs(score)) + 1;
//		Two things going on here.
//		First, if the score is greater than one, add a modifier to both addends.
//		Otherwise add only to one. Secondly, if the score is positive then 
//		the sum of the two addends is LESS than the target so we want to add
//		modifiers. Otherwise the sum of the two addends is GREATER than the
//		target so we want to SUBTRACT modifiers.
		return Math.abs(score) > 1 ? (score > 0 ? new Pair(pair.first + mod1,
				pair.second + mod2) : new Pair(pair.first - mod1, pair.second
				- mod2))
				: (score > 0 ? new Pair(pair.first, pair.second + mod2)
						: new Pair(pair.first, pair.second - mod2));
	}

	// lower is better
	private void evaluate(Pair pair) {

		int newScore = getScore(pair);
		System.out.println(String.format(
				"Evaluating pair: %d, %d. Current score: %d", pair.first,
				pair.second, newScore));
		if (Math.abs(newScore) < Math.abs(score)) {
			lastPair = pair;
			score = newScore;
		}
	}

	private int getScore(Pair pair) {
		return target - (pair.first + pair.second);
	}

	class Pair {
		int first;
		int second;

		public Pair(int d, int e) {
			this.first = d;
			this.second = e;
		}

	}

}
