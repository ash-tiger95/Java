package com.education;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_D4_7699_수지의수지맞는여행_DFS {
	static int T, R, C, Ans;
	static char[][] map;
	static Set<Character> set;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new char[R][C];
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
			}
			Ans = 1;
			set = new HashSet<>();
			set.add(map[0][0]);
			dfs(0, 0);

			System.out.println("#" + t + " " + Ans);
		} // for T
	}

	private static void dfs(int sy, int sx) {
		if (Ans < set.size()) {
			Ans = set.size();
		}
		for (int d = 0; d < 4; d++) {
			int ny = sy + dirs[d][0];
			int nx = sx + dirs[d][1];
			if (!boundary(ny, nx)) {
				continue;
			}
			if (!set.contains(map[ny][nx])) { // set에서 확인하는 것이 방문처리하는 것과 같은 효과
				set.add(map[ny][nx]);
				dfs(ny, nx);
				set.remove(map[ny][nx]);
			}
		}
	}

	private static boolean boundary(int ny, int nx) {
		return ny >= 0 && nx >= 0 && ny < R && nx < C;
	}
}
/* Set을 쓰면 시간도 많이 잡아먹고 메모리도 커진다.
 * public class Solution{
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n, m, ans;
    static char[][] arr;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tt=Integer.parseInt(br.readLine());
        for(int t=0; t<tt; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new char[n][m];
            for(int i=0; i<n; i++){
                String s = br.readLine();
                for(int j=0; j<m; j++){
                    arr[i][j] = s.charAt(j);
                }
            }
            boolean[] visit = new boolean[26];
            ans = 0;
            visit[arr[0][0]-'A'] = true;
            solve(0, 0, visit, 1);
            bw.write("#"+Integer.toString(t+1)+" "+Integer.toString(ans)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void solve(int x, int y, boolean[] visit, int num){
        ans = Math.max(ans, num);
        if(ans==26){
            return;
        }
        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx>=0&&ny>=0&&nx<n&&ny<m&&!visit[arr[nx][ny]-'A']){
                visit[arr[nx][ny]-'A'] = true;
                solve(nx, ny, visit, num+1);
                visit[arr[nx][ny]-'A'] = false;
            }
        }
    }
}
 */
