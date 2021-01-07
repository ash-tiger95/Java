package com.boj.kruskal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main_Gold4_1197_최소스패닝트리 {
	static int[] parents; // union-find: 내가 누구 팀인지
	static int[] rank; // union-find의 효율성을 위함
	// 입력은 첫줄에 정점의 개수와 간선의 개수가 들어오고
	// 그 다음줄부터 간선의 정보가 정점1 정점2 가중치로 가선의 개수만큼 들어옴
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		int[][] edges = new int[E][3];
		for(int i=0;i<E;i++) {
			edges[i][0] = sc.nextInt()-1;
			edges[i][1] = sc.nextInt()-1;
			edges[i][2] = sc.nextInt(); // 가중치
			// E간선개수 E가 가지고있는 간선 = O(NlogN)
			// E는 최대 V*(V-1)/2
			// 크루스칼은 간선과 관련! 간선이 많으면 성능이 떨어진다.
		}
		// 간선들을 가중치 오름차순으로 정렬
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// o1 - o2 하면 underflow, overflow가 날 수 있다.
				return Integer.compare(o1[2], o2[2]);
			} // 가중치를 기준으로 정렬
			
		}); // quick sort
//		for(int i=0;i<E;i++) {
//			System.out.println(Arrays.toString(edges[i]));
//		}
		parents = new int[V];
		rank = new int[V];
		
		// 각 정점에 대해 유니온파인드 연산 준비
		for(int i=0;i<V;i++) {
			makeSet(i);
		}
		
		// 정점의 개수 -1번 반복하면서
		int cnt = 0;
		int result = 0;
		for(int i=0;i<E;i++) {
			int a = findSet(edges[i][0]);
			int b = findSet(edges[i][1]);
			// 같은 팀이라면 패스
			if(a == b)
				continue;
			
			// 간선이 연결하는 두 정점이 같은 팀이 아니라면 한 팀으로 합쳐주고 간선 선택
			union(a,b);
			
			// 간선을 선택
			result += edges[i][2];
			// 정점의 개수 -1번 반복하면 그만
			cnt++;
			if(cnt == V-1)
				break;
		}
		System.out.println(result);
	}
	
	static void makeSet(int x) { // union-find에서 팀 만들기
		parents[x] = x;
	}
	static int findSet(int x) {
		if(x == parents[x]) // 자기 자신이 부모라면 자신이 식별자
			return x;
		else {
			parents[x] = findSet(parents[x]); // 그게 아니면 내 부모가 식별자
			return parents[x];
		}
	}
	static void union(int x, int y) {
//		int px = findSet(x);
//		int py = findSet(y);
		if(rank[x] > rank[y]) {
			parents[y] = x;
			
		} else {
			parents[x]  = y;
			if(rank[x] == rank[y]) {
				rank[y]++;
			}
		}
	}
}
