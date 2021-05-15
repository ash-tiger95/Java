package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 기본 개념>
 * 전위 순회(preOrder): 뿌리(root)를 먼저 방문
 * 		뿌리 -> 왼쪽 자식 -> 오른쪽 자식
 * 
 * 중위 순회(inOrder): 왼쪽 하위 트리를 방문 후 뿌리(root)를 방문
 * 		왼쪽 자식 -> 뿌리 -> 오른쪽 자식
 * 
 * 후위 순회(postorder): 하위 트리 모두 방문 후 뿌리(root)를 방문
 * 		왼쪽 자식 -> 오른쪽 자식 -> 뿌리
 * 
 * @author jugia
 *
 */
public class Main_Gold4_4256_트리 {
	
	static int T,N;
	static int[] preOrder, inOrder;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken()); // 테스트 케이스 개수
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 노드의 개수, 1 <= N <= 1,000
			
			preOrder= new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				preOrder[i] = Integer.parseInt(st.nextToken());
			}
			
			inOrder = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				inOrder[i] = Integer.parseInt(st.nextToken());
			}
			
			
		} // for T
	}

	private static void postOrder(int s, int e, int r) {
		for(int i=s;i<e;i++) {
			if(inOrder[i] == preOrder[i]) {
			}
		}
	}
}
