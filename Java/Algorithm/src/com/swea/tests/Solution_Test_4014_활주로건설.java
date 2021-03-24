package com.swea.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_Test_4014_활주로건설 {
	static int T, N, X, Ans;
	static int[][] map;
	static ArrayList<Node> list;
	static StringBuilder sb = new StringBuilder();

	static class Node {
		int height, len;

		public Node(int height, int len) {
			super();
			this.height = height;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Node [height=" + height + ", len=" + len + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src\\com\\swea\\testcase\\4014.txt")));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			Ans = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			// 가로 검사
			for (int i = 0; i < N; i++) {
				int idx = 0;
				System.out.println("a");
				list = new ArrayList<>();
				list.add(new Node(map[i][0], 1)); // 높이, 길이
				for (int j = 1; j < N; j++) {
					Node cn = list.get(idx); // 이전 정보
					if (cn.height == map[i][j]) {
						list.remove(idx);
						list.add(new Node(cn.height, cn.len + 1));
					} else {
						list.add(new Node(map[i][j], 1));
						idx++;
					}
				}
				
				
				int size = list.size();
				if(size >1) {
					for(int s=0;s<size-1;s++) {
						if(list.get(s).height-list.get(s+1).height == 1) {
							if(list.get(s+1).len >= 2) {
								Ans++;
							} else {
								break;
							}
						} else if(list.get(s).height-list.get(s+1).height == -11) {
							if(list.get(s).len >= 2) {
								Ans++;
							} else {
								break;
							}
						}
					}
				}else {
					Ans++;
				}
				
				
			}
			
			

			sb.append(Ans).append("\n");
		} // for T
		System.out.println(sb);
	}

}
