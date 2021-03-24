package com.boj.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Gold5_17485_진우의달여행 {

	static int N, M, answer;
	static int[][] map;
	static int[][][] memoi;
	static int[][] dirs = { { 1, 0 }, { 1, 1 }, { 1, -1 } }; // 아래, 우대각선, 좌대각선

	static class Node {
		int y, x, d;

		public Node(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}

		@Override
		public String toString() {
			return + y + ", " + x + ", " + d + "]";
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		
		answer = Integer.MAX_VALUE;
		for (int m = 0; m < M; m++) {
			
			
			for(int k=0;k<3;k++) {
				reset();
				bfs(m,k);
			}
			
			

			

		}
		System.out.println(answer);
	}

	private static void bfs(int start, int k) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, start, k));

		while (!q.isEmpty()) {
			Node cn = q.poll();
			System.out.println(cn.toString());
			

			if (cn.y == N - 1) {
				answer = Math.min(answer, memoi[N - 1][cn.x][cn.d]);
				continue;
			}

			for (int d = 0; d < 3; d++) { // 0: 아래, 1: 우대각선, 2: 좌대각선
				if(cn.y == 0 && d == 1) {
					continue;
				}
				if(cn.y == N-1 && d == 2) {
					continue;
				}
				int ny = cn.y + dirs[d][0];
				int nx = cn.x + dirs[d][1];

				if (!boundary(ny, nx)) {
					continue;
				}

				if (cn.d == 0) { // 이전에 아래로 왔으면
					if (memoi[ny][nx][1] > memoi[cn.y][cn.x][0] + map[ny][nx]) {
						memoi[ny][nx][1] = memoi[cn.y][cn.x][0] + map[ny][nx];
						q.offer(new Node(ny, nx, 1));
					}

					if (memoi[ny][nx][2] > memoi[cn.y][cn.x][0] + map[ny][nx]) {
						memoi[ny][nx][2] = memoi[cn.y][cn.x][0] + map[ny][nx];
						q.offer(new Node(ny, nx, 2));
					}
					
				} else if (cn.d == 1) { // 이전에 우대각선
					if (memoi[ny][nx][0] > memoi[cn.y][cn.x][1] + map[ny][nx]) {
						memoi[ny][nx][0] = memoi[cn.y][cn.x][1] + map[ny][nx];
						q.offer(new Node(ny, nx, 0));
					}

					if (memoi[ny][nx][2] > memoi[cn.y][cn.x][1] + map[ny][nx]) {
						memoi[ny][nx][2] = memoi[cn.y][cn.x][1] + map[ny][nx];
						q.offer(new Node(ny, nx, 2));
					}
				} else { // 이전에 좌대각선
					if (memoi[ny][nx][0] > memoi[cn.y][cn.x][2] + map[ny][nx]) {
						memoi[ny][nx][0] = memoi[cn.y][cn.x][2] + map[ny][nx];
						q.offer(new Node(ny, nx, 0));
					}

					if (memoi[ny][nx][1] > memoi[cn.y][cn.x][2] + map[ny][nx]) {
						memoi[ny][nx][1] = memoi[cn.y][cn.x][2] + map[ny][nx];
						q.offer(new Node(ny, nx, 1));
					}
				}
				
				for(int x=0;x<3;x++) {
					System.out.println(x+" -----------");
					for (int i = 0; i < N; i++) {
						for (int j = 0; j < M; j++) {
							System.out.print(memoi[i][j][x]+" ");
						}
						System.out.println();
					}
					System.out.println("------");
				}

			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
	
	private static void reset() {
		memoi = new int[N][M][3]; // 아래, 우대각선, 좌대각선으로 올 경우
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(memoi[i][j], Integer.MAX_VALUE);

				for (int k = 0; k < 3; k++) {
					if (i == 0) {
						memoi[i][j][k] = map[i][j];
					}
					
				}
				
			}
		}
	}

}
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{    
    static int n,m;
    static int fuel[][];
    // 0: ↘
    // 1: ↙
    // 2: ↓
    static int[][][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 전에 움직인 방향으로 움직일 수 없다.

        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        fuel = new int[n][m];
        dp = new int[3][n][m];

        for(int i=0; i<n; i++) {
            String[] temp2 = br.readLine().split(" ");
            for(int j=0; j<m; j++) {
                fuel[i][j] = Integer.parseInt(temp2[j]);
            }
        }

        // initialize
        for(int i=0; i<m; i++){
            dp[0][0][i] = fuel[0][i];
            dp[1][0][i] = fuel[0][i];
            dp[2][0][i] = fuel[0][i];
        }

        // 있을 수 없는 값들 큰 값으로 초기화
        for(int i=0; i<n; i++){
            // 맨 왼쪽 끝인데 왼쪽 대각선 방향에서 오는 경우
            dp[0][i][0] = Integer.MAX_VALUE;
            // 맨 오른쪽 끝인데 오른쪽 대각선 방향에서 오는 경우
            dp[2][i][m-1] = Integer.MAX_VALUE;
        }

        for(int i=1; i<n; i++){
            for(int j=0; j<m; j++){
                // 왼쪽 방향, 오른쪽 방향 모두에서 올 수 있는 경우
                if(isValidPosition(j-1) && isValidPosition(j+1)){
                    dp[0][i][j] = Math.min(dp[1][i-1][j-1], dp[2][i-1][j-1]) + fuel[i][j];
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + fuel[i][j];
                    dp[2][i][j] = Math.min(dp[0][i-1][j+1], dp[1][i-1][j+1]) + fuel[i][j];
                }
                else if(!isValidPosition(j-1) && isValidPosition(j+1)){
                    // 오른쪽 끝에 있는 경우
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + fuel[i][j];
                    dp[2][i][j] = Math.min(dp[0][i-1][j+1], dp[1][i-1][j+1]) + fuel[i][j];
                }
                else if(isValidPosition(j-1) && !isValidPosition(j+1) ){
                    // 왼쪽 끝에 있는 경우
                    dp[0][i][j] = Math.min(dp[1][i-1][j-1], dp[2][i-1][j-1]) + fuel[i][j];
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + fuel[i][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        // 마지막 행에서 최소값 찾기
        for(int i=0; i<m; i++){
            for(int j=0; j<3; j++){
                min = min > dp[j][n-1][i] ? dp[j][n-1][i] : min;
            }
        }
        System.out.println(min);
        br.close();
    }


    public static boolean isValidPosition(int y) {
        if(y < 0 || y >= m)
            return false;
        return true;
    }
}
*/