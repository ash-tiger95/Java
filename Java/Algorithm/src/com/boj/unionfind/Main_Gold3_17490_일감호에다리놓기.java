package com.boj.unionfind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_Gold3_17490_일감호에다리놓기 {

	static int N, M;
	static long K;
	static int[] S, parents;
	static int[][] construction;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 강의동 수, 3 ≤ N ≤ 1,000,000
		M = Integer.parseInt(st.nextToken()); // 공사구간 수, 0 ≤ M ≤ N
		K = Long.parseLong(st.nextToken()); // 건덕이가 가진 돌의 수, 0 ≤ K ≤ 5,000,000,000

		st = new StringTokenizer(br.readLine());
		S = new int[N]; // 강의동에서 와우도까지 놓아야하는 돌의 개수
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		construction = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			// 이런 조건 빠트리지 말자.
			if (a < b) {
				construction[i][0] = a;
				construction[i][1] = b;
			} else {
				if (a == N - 1 && b == 0) {
					construction[i][0] = a;
					construction[i][1] = b;
				} else {
					construction[i][0] = b;
					construction[i][1] = a;
				}

			}

		}

		if (M <= 1) {
			System.out.println("YES");
		} else {
			// 1. construction을 순서대로 배열 (오름차순)
			Arrays.sort(construction, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[0], o2[0]);
				}
			});

			// 2. makeSet
			makeSet();

			// 3. union
			int idx = 0;
			for (int i = 0; i < N - 1; i++) {
				if (idx < M) {
					if (construction[idx][0] == i) { // 공사중일 경우 parents[i] = i;
						idx++;
					} else {
						// construction는 현재 좌표와 다음 좌표의 연결 여부이므로 현재 좌표가 공사중이 아닌 경우 연결
						union(i, i + 1);
					}
				} else {
					union(i, i + 1);
				}

			}
			
//			System.out.println(Arrays.toString(parents));

			// N-1번째랑 0번째 좌표를 연결하는지 판별 및 연결
			if (construction[M - 1][0] != N - 1) {
				int temp = parents[N - 1];
				for (int i = N - 1; i >= 0; i--) {
					if (find(i) == temp) {
						parents[i] = 0;
					}
				}
			}

//			System.out.println(Arrays.toString(parents));

			long min = 0;

			if (parents[0] == parents[N - 1]) { // 0번과 N-1번이 연결되는 경우
				int p = -1; // 현재 parents 번호
				int v = 0; // 같은 parents[i] 중 S[i]의 최소값
				boolean isFirst = true;
				
				for (int i = 0; i < N; i++) {
					if (parents[i] == 0) { // parents[i] == 0은 밑에서 따로 처리
						if (!isFirst) {
							min += v;
							break;
						}
						continue;
					}

					if (p != parents[i]) { // parents[i]가 바뀌는 순간
						min += v;
						p = parents[i];
						v = S[i];
					} else {
						v = Math.min(S[i], v);
					}
					
					isFirst = false;
				}

				// --------------
				// parents[i] == 0인 좌표들의 min을 구한다.
				v = Integer.MAX_VALUE;
				for (int i = 0; i < N; i++) {
					if (parents[i] == 0) {
						v = Math.min(v, S[i]);
					} else {
						break;
					}
				}

				for (int i = N - 1; i >= 0; i--) {
					if (parents[i] == 0) {
						v = Math.min(v, S[i]);
					} else {
						break;
					}
				}

				min += v;
			} else { // 0번과 N-1번이 연결되지 않은 경우
				int p = -1;
				int v = 0;
				for (int i = 0; i < N; i++) {
					if (p != parents[i]) {
						min += v;
						p = parents[i];
						v = S[i];
					} else {
						v = Math.min(S[i], v);
					}

					if (i == N - 1) {
						min += v;
					}
				}
			}

//			System.out.println(min);

			if (K >= min) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}

	}

	private static void makeSet() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}

		return;
	}

	private static int find(int x) {
		if (parents[x] == x) {
			return x;
		} else {
			return parents[x] = find(parents[x]);
		}
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return;
		} else {
			if (a < b) {
				parents[b] = a;
			} else {
				parents[a] = b;
			}

			return;
		}
	}
}
