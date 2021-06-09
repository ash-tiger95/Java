package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold3_17822_원판돌리기 {

	static int N, M, T;
	static int[][] in;
	static boolean[][] visited;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		in = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				in[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int t = 0; t < T; t++) { // T번 회전
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // x배수 원판을
			int d = Integer.parseInt(st.nextToken()); // 0: 시계방향, 1: 반시계방향으로
			int k = Integer.parseInt(st.nextToken()); // k칸 회전

			for (int i = 1; i <= N; i++) {
				if (i % x == 0) {
					if (d == 0) {
						rotateRight(i-1, k);
					} else {
						rotateLeft(i-1, k);
					}
				}
			}
			System.out.println("돌린 후");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					
					System.out.print(in[i][j]+" ");

				}
				System.out.println();
			}
			
			System.out.println("========================");
			
			
			boolean hasErase = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					
					

					if (in[i][j] != 0) {
						
						hasErase = dfs(i, j, in[i][j]);

					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					
					System.out.print(in[i][j]+" ");

				}
				System.out.println();
			}
			
			System.out.println("========================");
			
			if(!hasErase) {
				int sum = 0;
				int cnt = 0;
				for(int i=0;i<N;i++) {
					for(int j=0;j<M;j++) {
						sum+= in[i][j];
						cnt++;
					}
				}
				
				double avg = sum/cnt;
				for(int i=0;i<N;i++) {
					for(int j=0;j<M;j++) {
						if(in[i][j] > avg) {
							in[i][j]--;
						} else if(in[i][j] <avg){
							in[i][j]++;
						}
					}
				}
			}

		}
		
		int ans = 0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				ans+= in[i][j];
			}
		}
		
		System.out.println(ans);
	}

	private static boolean dfs(int sy, int sx,int value) {
		boolean hasErase = false;

		for (int d = 0; d < 4; d++) {
			int ny = sy + dirs[d][0];
			int nx = sx + dirs[d][1];

			if (ny < 0) {
				continue;
			} else if (ny >= M) {
				continue;
			}

			if (nx < 0) {
				nx = M - 1;
			} else if (nx >= M) {
				nx = 0;
			}

			if (value == in[ny][nx]) {
				in[sy][sx] = 0;
				in[ny][nx] = 0;
				dfs(ny, nx, value);
				hasErase = true;
			}

		}
		
		return hasErase;
		
	}

	private static void rotateRight(int x, int k) {
		int[] temp = in[x].clone();

		for (int i = M - 1; i >=k; i--) {
			in[x][i] = temp[i - k];
		}
		
		System.out.println(Arrays.toString(in[x]));

		for (int i = 0; i <k; i++) {
			in[x][i] = temp[M-i-1];
		}
		System.out.println(Arrays.toString(in[x]));
	}

	private static void rotateLeft(int x, int k) {
		int[] temp = in[x].clone();

		for (int i = 0; i < M - k; i++) {
			in[x][i] = in[x][i + k];
		}

		System.out.println(Arrays.toString(in[x]));
		
		for (int i = 0; i <k; i++) {
			in[x][M-i-1] = temp[i];
		}
		System.out.println(Arrays.toString(in[x]));
	}
}
