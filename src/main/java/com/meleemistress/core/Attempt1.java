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
		Attempt1 att = new Attempt1(10);
		att.score = att.target;
		while (att.score > 0) {
			System.out.println(String.format("Current pair: %d, %d. Current score: %d", att.lastPair.first, att.lastPair.second, att.score));
			att.evaluate(att.generate(att.lastPair, att.score));
		}
		System.out.println(String.format("Evaluation complete. Winning combination is %d, %d", att.lastPair.first, att.lastPair.second));
	}
	
	public Attempt1(int target) {
		this.target = target;
		this.lastPair = new Pair(0, 0);
		evaluate(lastPair);
	}
	
	private Pair generate(Pair pair, int score) {
		return new Pair(pair.first, pair.second + score);
	}
	
	//lower is better
	private void evaluate(Pair pair) {
		
		int newScore = target - (pair.first + pair.second);
		System.out.println(String.format("Evaluating pair: %d, %d. Current score: %d", pair.first, pair.second, newScore));
		if (Math.abs(newScore) < Math.abs(score)) {
			lastPair = pair;
			score = newScore;
		}
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
