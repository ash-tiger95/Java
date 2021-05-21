package com.swea.swtest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_Test_5653_줄시세포배양 {

	static int T, N, M, K, Ans;
	static int[][] map;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static PriorityQueue<Node> inactiveCell; // 비활성화: X시간 기다리는 세포
	static Queue<Node> activeCell; // 활성화: 활성화 후 X시간동안 살아있는 세포

	static class Node implements Comparable<Node> {
		int y, x, life, time; // time == 0이 되면 다음 턴에 번식

		public Node(int y, int x, int life, int time) { // (좌표), 생명기간, K시간
			super();
			this.y = y;
			this.x = x;
			this.life = life;
			this.time = time;
		}

		@Override
		public String toString() {
			return "[y=" + y + ", x=" + x + ", life=" + life + ", time=" + time + "]";
		}

		@Override
		public int compareTo(Node o) {
			return (-1) * Integer.compare(this.life, o.life); // 생명기간으로 오름차순
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\5653.txt")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로, 1 <= N <= 50
			M = Integer.parseInt(st.nextToken()); // 가로, 1 <= N <= 50
			K = Integer.parseInt(st.nextToken()); // 배양시간, 1 <= K <= 300

			map = new int[N + K][M + K]; // 최대 길이, ex) K/2 + M + K/2
			visited = new boolean[N + K][M + K];
			inactiveCell = new PriorityQueue<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i + K / 2][j + K / 2] = Integer.parseInt(st.nextToken());
					if (map[i + K / 2][j + K / 2] != 0) {
						inactiveCell.offer(
								new Node(i + K / 2, j + K / 2, map[i + K / 2][j + K / 2], map[i + K / 2][j + K / 2]));
						visited[i + K / 2][j + K / 2] = true;
					}
				}
			}

			int kTime = 0;
			activeCell = new LinkedList<>(); // 활성화된 후 X시간동안 활성화되는 세포 모임

			while (kTime < K) {

				// 1. 활성 세포 조작
				int sizeQ = activeCell.size();

				for (int i = 0; i < sizeQ; i++) {
					Node cn = activeCell.poll();
					if (--cn.time != 0) { // 활성화가 끝나면
						activeCell.add(cn); // 죽은 세포로 변환
					}
				}

				// 2. 비활성 세포 조작
				ArrayList<Node> list = new ArrayList<>(); // PQ로 구현해서 list로 따로 세포를 저장하고 addAll()
				int size = inactiveCell.size();

				for (int s = 0; s < size; s++) {
					Node cn = inactiveCell.poll();

					if (cn.time == 0) { // 시간이 0이면 번식
						for (int d = 0; d < 4; d++) {
							int ny = cn.y + dirs[d][0];
							int nx = cn.x + dirs[d][1];

							if (visited[ny][nx]) {
								continue;
							}

							visited[ny][nx] = true;
							list.add(new Node(ny, nx, cn.life, cn.life)); // 번식 세포 추출
						}
						if (cn.life - 1 != 0) { // 활성 세포 저장 (생명길이가 1이면 이번 턴이 끝나면 사라진다.)
							activeCell.add(new Node(cn.y, cn.x, cn.life - 1, cn.life - 1));
						}
					} else { // 시간이 남았으면
						cn.time--; // k시간 감소
						list.add(cn);
					}
				}
				inactiveCell.addAll(list);

				// 3. 시간 조작
				kTime++;
			}

			// 남은 세포 더하기
			Ans = inactiveCell.size() + activeCell.size();
			sb.append(Ans).append("\n");
		}
		System.out.println(sb);
	}
}
