package com.boj.search.binarysearch;

import java.util.Arrays;
import java.util.Scanner;

public class Main_Silver1_2110_공유기설치 {
	/* static int N,C;
	static int[] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 집의 개수, 2 <= N <= 200,000
		C = Integer.parseInt(st.nextToken()); // 공유기 개수, 2 <= C <= N
		
		for(int i=0;i<N;i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(map);
		
		
	}
	
	private static void binarySearch() {
		int left=1;// 가능한 최소 거리
		int right = map[N-1]-map[0];
		int d = 0; // 간격
		int mid;
		
		while(left <= right) {
			mid = (left+right)/2; // 기준
			int start = map[0];
			int cnt = 1;
			
			// 간격 d를 기준으로 공유기 설치
			for(int i=1;i<N;i++) {
				d = map[i]-start;
				if(mid <= d) {
					++cnt;
					start = map[i];
				}
			}
		}
	}*/
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //도현이의 집
        int C = sc.nextInt(); // 공유기 개수
        int[] house = new int[N];
        int answer = 0;
        for (int i = 0; i < N; i++) {
            house[i] = sc.nextInt();
        }
        Arrays.sort(house);
        int left = 1; //최소 길이
        int right = house[N - 1] - house[0];// 최대 길이
        while (left <= right) {
            int mid = (left + right) / 2; //공유기 거리 기준
            System.out.println(left +" "+right+" mid "+mid);
            
            int prevHouse = house[0]; //첫 위치에 설치
            int count = 1; //공유기 설치 개수
            System.out.println("prevhouse: "+prevHouse+ " count "+count );
            
            for (int i = 0; i < N; i++) {
                int distance = house[i] - prevHouse;
                System.out.println("distance: "+distance);
                if (distance >= mid) { //거리차가 기준보다 이상되야 설치 가능
                    count++;
                    prevHouse = house[i];
                    System.out.println("설치: " + prevHouse);
                }
            }
            
            System.out.println("총 설치 개수: "+count);
            if (count >= C) { //공유기 설치가 더 많이 됬으니 간격을 넓혀서 줄여야함
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
