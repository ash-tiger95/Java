package com.programmers;

public class Solution_Level3_단어변환 {

	static int Ans;

	private static int solution(String begin, String target, String[] words) {
		Ans = Integer.MAX_VALUE;

		boolean[] used = new boolean[words.length];

		// words에 target이 없으면 return 0;
		boolean impossible = true;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(target)) {
				impossible = false;
				break;
			}
		}

		if (impossible) {
			return 0;
		} else { // words에 target이 있으면 dfs
			dfs(begin, target, words, used, 0);
		}

		return Ans;
	}

	// used: 단어로 변한 적 있는 경우 판별, count: 단어 변환 횟수
	private static void dfs(String begin, String target, String[] words, boolean[] used, int count) {

		if (begin.equals(target)) {
			Ans = Math.min(Ans, count);
			return;
		}

		for (int i = 0; i < words.length; i++) {
			if (!used[i]) { // 해당 단어로 변환한 적이 없는 경우
				if (isPossible(begin, words[i])) { // 변환 가능하면
					used[i] = true;
					dfs(words[i], target, words, used, count + 1);
					used[i] = false;
				}
			}
		}
	}

	private static boolean isPossible(String cur, String compare) { // 변환 가능한지 여부
		int count = 0;

		// 같은 경우의 개수를 세도 되지만, 2개이상 틀릴 시 멈추는게 효율성!
		for (int i = 0; i < cur.length(); i++) {
			if (cur.charAt(i) != compare.charAt(i)) {
				count++;
			}

			if (count >= 2) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
		System.out.println(solution(begin, target, words));
	}

}
