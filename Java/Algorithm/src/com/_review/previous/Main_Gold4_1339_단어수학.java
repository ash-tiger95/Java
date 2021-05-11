package com._review.previous;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_Gold4_1339_단어수학 {

	static int N;
	static long Ans;
	static String[] input;
	static PriorityQueue<Node> pq;
	static HashMap<Character, Integer> hm;

	static class Node implements Comparable<Node> {
		char alpha;
		int idx;

		public Node(char alpha, int idx) {
			super();
			this.alpha = alpha;
			this.idx = idx;
		}

		@Override
		public int compareTo(Node o) {
			return (-1) * Integer.compare(this.idx, o.idx);
		}

		@Override
		public String toString() {
			return "[alpha=" + alpha + ", idx=" + idx + "]\n";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 단어 개수, 1 <= N <= 10	
		input = new String[N];
		pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			String a = br.readLine();
			input[i] = a;
			int len = a.length(); // 자릿수
			for (int j = 0; j < a.length(); j++) {
				pq.add(new Node(a.charAt(j), len--));
			}
		}

		System.out.println(pq);
		
		hm = new HashMap<>();
		int max = 9;
		while (!pq.isEmpty()) {
			Node cn = pq.poll();

			if (!hm.containsKey(cn.alpha)) {
				hm.put(cn.alpha, max--);
			}
		}
		System.out.println(hm);

		Ans = 0;
		for (int i = 0; i < N; i++) {

			int len = input[i].length();

			for (int j = 0; j < input[i].length(); j++) {
				Ans += hm.get(input[i].charAt(j)) * Math.pow(10, len - 1);
				len--;
			}
		}

		System.out.println(Ans);
		*/
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String [] arr = new String[n];
        int [] alpha = new int[26];
        for(int i=0; i<n; i++){
            arr[i] = sc.next();
        }


        for(int i=0; i<n; i++){
            int temp = (int)Math.pow(10,arr[i].length()-1);
            for(int j=0; j<arr[i].length(); j++){
                alpha[(int)arr[i].charAt(j)-65]+=temp;
                temp /=10;
            }
        }
        
        System.out.println(Arrays.toString(alpha));

        Arrays.sort(alpha);
        int index = 9;
        int sum =0;
        for(int i=alpha.length-1; i>=0; i--){
            if(alpha[i] == 0){
                break;
            }
            sum+= alpha[i]*index;
            index--;
        }
        System.out.println(sum);

	}

}
