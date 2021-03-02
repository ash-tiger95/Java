package com.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_Test_5648_원자소멸시뮬레이션_Simulation {

	static int T;
    static int N;
 
    static class Pair {
    	int x;
        int y;
        int d;
        int k;
 
        public Pair(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
 
    }
 
    static final int stand = 1000;
    static final int MAX_LEN = 4000;
 
    static int dy[] = { 1, -1, 0, 0 };
    static int dx[] = { 0, 0, -1, 1 };
    static int score[][] = new int[MAX_LEN + 2][MAX_LEN + 2];
    static Queue<Pair> q;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        T = Integer.parseInt(br.readLine().trim());
         
 
        for (int t = 1; t <= T; t++) {
            sb.append('#').append(t).append(' ');
 
            N = Integer.parseInt(br.readLine().trim());
            q = new LinkedList<Pair>();
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int x = Integer.parseInt(st.nextToken()) + stand;
                int y = Integer.parseInt(st.nextToken()) + stand;
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                q.add(new Pair(x * 2, y * 2, d, k));
                score[y * 2][x * 2] = k;
            }
            int ans = 0;
 
            while (!q.isEmpty()) {
                Pair cur = q.poll();
                int x = cur.x; // x * 2
                int y = cur.y; // y * 2
                int d = cur.d;
                int k = cur.k;
 
                if (score[y][x] != k) {
                    ans += score[y][x];
                } else {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny >= 0 && nx >= 0 && ny <= MAX_LEN+1 && nx <= MAX_LEN+1) {
                        score[ny][nx] += k;
                        q.add(new Pair(nx,ny,d,k));
                    }
                }
                score[y][x] = 0;
            }
 
            sb.append(ans).append('\n');
 
        }
        System.out.print(sb.toString());
    }

}
