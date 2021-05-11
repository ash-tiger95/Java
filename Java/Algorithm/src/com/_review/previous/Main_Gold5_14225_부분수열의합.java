package com._review.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Gold5_14225_부분수열의합 {

	static int N;
	static int[] input;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 입력 개수, 1 <= N <= 20
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		input = new int[N];
		visited = new boolean[2000001]; // 최대 개수
		for(int i=0;i<N;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
	
		Arrays.sort(input);
		dfs(0,0);
		
		int answer = 0;
		while(true) {
			if(visited[answer]) {
				answer++;
			}else {
				break;
			}
		}
		
		System.out.println(answer);
	}
	
	static void dfs(int cnt,int sum){
        if(cnt == N){
            visited[sum] = true;
        }
        else{
            dfs(cnt+1, sum+input[cnt]);
            dfs(cnt+1, sum);
        }
    }

}
