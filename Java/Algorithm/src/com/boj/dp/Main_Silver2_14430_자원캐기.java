package com.boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제) 오른쪽, 아래쪽으로만 이동해서 (R,C)의 최댓값 구하기 
 * 
 * 풀이) dp[][] 배열을 선언해서 [i-1][j], [i][j-1]의 최댓값을 구하면서 점차적으로 증가시킨다.
 * 
 * 포인트) map을 입력받을 때 (0,0)부터가 아니라 (1,1)부터 입력받아 -1을 따로 검사 안 하는 것!
 * 
 * @author jugia
 *
 */
public class Main_Silver2_14430_자원캐기 {

	static int R, C;
	static int[][] map, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R + 1][C + 1]; // ★. -1을 검사하지 안하기 위해
		dp = new int[R + 1][C + 1];
		for (int i = 1; i < R + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < C + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < R + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + map[i][j];
			}
		}

		System.out.println(dp[R][C]);
		
		br.close();
	}
}
