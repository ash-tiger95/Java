package com.test.day0327_scofe2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 이차03_패키지제조하기 {

	static int N, Q;
	static int[] parents;
	static int[][] check;
	static StringBuilder sb =null;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 총 물품 개수, 1 <= N <= 500,000
		Q = Integer.parseInt(st.nextToken()); // 관계 확인 횟수, 1 <= Q <= N
		sb = new StringBuilder();
		if (N == 1) {
			st = new StringTokenizer(br.readLine());
			System.out.println("no");
		} else {
			parents = new int[N+1]; // 모든 물품은 1이상 N이하의 정수
			Arrays.fill(parents, -1);
			
			// 물품번호는 1부터 시작
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine()); // 동일한 하위 물품이 두 번 이상 들어오는 경우는 없음을 보장한다.

				int a = Integer.parseInt(st.nextToken()); // 상위
				int b = Integer.parseInt(st.nextToken()); // 하위

				parents[b] = a; // b의 부모는 a
			}
			

			check = new int[Q][2];
			for (int i = 0; i < Q; i++) {
				st = new StringTokenizer(br.readLine()); // 두 정수는 항상 다름을 보장한다.
				check[i][0] = Integer.parseInt(st.nextToken()); // 상위
				check[i][1] = Integer.parseInt(st.nextToken()); // 하위
				// check[i][0]이 check[i][1]의 상위 물품인지 묻는 것이다.
			}

			System.out.println(Arrays.toString(parents));
			// 재귀로 부모 검사
			for (int i = 0; i < Q; i++) {
				if(parents[check[i][0]] == -1) {
					sb.append("yes").append("\n"); // 요거 없으면 실패
				}else {
					recur(check[i][0], check[i][1]);
				}
			}
			
			
			System.out.println(sb);
		}

	}

	private static void recur(int end, int start) {
		if (start == end) {
			sb.append("yes").append("\n");
			return;
		}

		if (start == -1) {
			sb.append("no").append("\n");
			return;
		}

		recur(end, parents[start]);
	}

}

/* 입력
6 6
6 4
6 5
4 1
4 2
4 3
1 4
4 1
6 5
1 6
6 3
4 3
*/
/* 출력
no
yes
yes
no
yes
yes
*/