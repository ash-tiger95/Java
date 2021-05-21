package com._review.swe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static int[][] map;
    static int N;
    static int X;
    static int ans;
     
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for(int test_case = 1; test_case <= T; test_case++)
        {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            X = Integer.parseInt(input[1]);
            map = new int[N][N];
            ans = 0;
             
            for(int i=0; i<N; i++) {
                input = br.readLine().split(" ");
                for(int j=0; j<N; j++)
                    map[i][j] = Integer.parseInt(input[j]);
            }
             
            for(int i=0; i<N; i++)
                dfs(0, i, 0, 1, false);
             
            for(int i=0; i<N; i++)
                dfs(i, 0, 1, 1, false);
           
            System.out.println("#"+test_case+" "+ans);
        }
    }
     
    public static void dfs(int x, int y, int idx, int cnt, boolean flag) {
        int nx = x+dx[idx];
        int ny = y+dy[idx];
             
        if(nx==N || ny==N) {
        	System.out.println("Return: "+x+","+y+" cnt: "+cnt+" flag: "+flag);
            if(flag && cnt>=X || !flag) {
            	System.out.println("++Ans");
            	ans++;
            }
            return;
        }
         
        if(map[x][y] == map[nx][ny]) {
        	System.out.println("Start: "+x+","+y+" "+nx+","+ny+" cnt: "+cnt+" flag: "+flag);
        	dfs(nx, ny, idx, cnt+1, flag);
        }
         
        else {
            if(Math.abs(map[x][y] - map[nx][ny])>=2) {
            	System.out.println("End: "+x+","+y+" "+nx+","+ny+" cnt: "+cnt+" flag: "+flag);
            	return;
            }
             
            if(map[x][y]<map[nx][ny] && !flag && cnt>=X) {
            	System.out.println("DFS1: "+x+","+y+" "+nx+","+ny+" cnt: "+cnt+" flag: "+flag);
            	dfs(nx, ny, idx, 1, false);
            }
             
            else if(map[x][y]<map[nx][ny] && flag && cnt>=2*X) {
            	System.out.println("DFS2: "+x+","+y+" "+nx+","+ny+" cnt: "+cnt+" flag: "+flag);
            	dfs(nx, ny, idx, 1, false);
            }
             
            else if(map[x][y]>map[nx][ny] && flag && cnt>=X) {
            	System.out.println("DFS3: "+x+","+y+" "+nx+","+ny+" cnt: "+cnt+" flag: "+flag);
            	dfs(nx, ny, idx, 1, true);
            }
             
            else if(map[x][y]>map[nx][ny] && !flag) {
            	System.out.println("DFS4: "+x+","+y+" "+nx+","+ny+" cnt: "+cnt+" flag: "+flag);
            	dfs(nx, ny, idx, 1, true);
            }
        }
    }
}
