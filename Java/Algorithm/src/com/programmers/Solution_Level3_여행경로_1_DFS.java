package com.programmers;

public class Solution_Level3_여행경로_1_DFS { // 내 풀이

	static String Ans;

	private static String[] solution(String[][] tickets) {
		Ans = "";
		boolean[] used = new boolean[tickets.length];

		for (int i = 0; i < tickets.length; i++) { // 모든 표를 검사
			if(tickets[i][0].equals("ICN")) { // 시작은 항상 ICN
				used[i] = true;
				dfs(tickets, used, 1, tickets[i][1], tickets[i][0] + " " + tickets[i][1]);
				used[i] = false;
			}
		}

		return Ans.split(" "); // String[]으로 return
	}

	private static void dfs(String[][] tickets, boolean[] used, int count, String start, String ans) {
		// 기저 조건
		if (count == tickets.length) {
			if (Ans.equals("")) { // 최초 경우의 수는 그냥 대입
				Ans = ans;
			} else {
				if (Ans.compareTo(ans) > 0) { // 알파벳 빠른 순을 뽑기 위해 비교한다.
					// Ans가 더 크면(알파벳이 느리면) return 양수
					Ans = ans; 
				}
			}
			return;
		} else {
			for (int i = 0; i < tickets.length; i++) {
				if (!used[i]) { // 사용한 적 없는 티켓일 경우
					if (tickets[i][0].equals(start)) { // 시작점이 이전 목적지와 같을 경우
						used[i] = true;
						dfs(tickets, used, count + 1, tickets[i][1], ans + " " + tickets[i][1]);
						used[i] = false;
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		String[][] tickets = { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
				{ "ATL", "SFO" } };
//		String[][] tickets = {{"ICN", "JFK"},{"HND", "IAD"},{"JFK", "HND"}};
		System.out.println(solution(tickets));
	}

}
