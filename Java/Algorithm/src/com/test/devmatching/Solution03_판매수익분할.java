package com.test.devmatching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution03_판매수익분할 { // 성공

	static HashMap<String, Integer> hm;

	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int N = enroll.length; // 총 사람 수
		hm = new HashMap<>();
		int[] answer = new int[N]; // (center 빼고 출력)

		int[] parents = new int[N]; // enroll 순서 기준으로 parents 설정
		System.out.println(Arrays.toString(enroll));
		for (int i = 0; i < N; i++) {
			hm.put(enroll[i], i);
		}

		for (int i = 0; i < N; i++) {

			if (referral[i].equals("-")) {
				parents[i] = -1;
			} else {
				parents[i] = hm.get(referral[i]);
			}
		}
		
		System.out.println(hm);
		System.out.println(Arrays.toString(parents));

		for(int i=0;i<seller.length;i++) {
			String name = seller[i];
			int price = amount[i] * 100; // 수입
			
			
			
			while(true) {
				System.out.println(name+" "+price);
				
				int parentsId = parents[hm.get(name)]; // 부모 번호 가져오기
				System.out.println(parentsId);
				
				if(parentsId == -1) {
					answer[hm.get(name)] += price - price/10;
					System.out.println(Arrays.toString(answer));
					break;
				} else {
					answer[hm.get(name)] += price - price/10; // 진짜 수입
					price = price/10;
					name = enroll[parentsId];
				}
				System.out.println(Arrays.toString(answer));
			}
		}
		

		return answer;
	}
/*
 * 

enroll	referral	seller	amount	result
["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"]	["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"]	["young", "john", "tod", "emily", "mary"]	[12, 4, 2, 5, 10]	[360, 958, 108, 0, 450, 18, 180, 1080]
["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"]	["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"]	["sam", "emily", "jaimie", "edward"]	[2, 3, 5, 4]	[0, 110, 378, 180, 270, 450, 0, 0]
 */
	public static void main(String[] args) {
		String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" }; // 각 판매원의 이름을 담은 배열
		String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" }; // 각 판매원을 다단계 조직에 참여시킨
																								// 다른 판매원의 이름
		String[] seller = { "young", "john", "tod", "emily", "mary" };
		int[] amount = { 12, 4, 2, 5, 10 };

		System.out.println(solution(enroll, referral, seller, amount));
	}
}
