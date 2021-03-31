package com.programmers.test.kakao;

import java.util.HashMap;
import java.util.List;

public class Solution_Level2_순위검색 {
	
	static HashMap<String, Integer> hm;

	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];

		hm = new HashMap<>();

		for (int i = 0; i < info.length; i++) {
			String[] values = info[i].split(" ");
			String in = "";
			for (int j = 0; j < 5; j++) {
				if (j == 4) {
					in += values[j];
				} else {
					in += values[j].charAt(0) + "";
				}
			}
			hm.put(in, hm.getOrDefault(in, 0) + 1);
		}
		
		System.out.println(hm);
		

		int idx = 0;
		for (int i = 0; i < query.length; i++) {
			String[] values = query[i].split(" and ");

			String str = "";
			int score = 0;
			for (int j = 0; j < 4; j++) {
				if (j == 3) {
					String[] v = values[3].split(" ");
					str += v[0].charAt(0) + "";
					score = Integer.parseInt(v[1]);
				} else {
					str += values[j].charAt(0) + "";
				}
			}

			for (String key : hm.keySet()) {

				String check = key.substring(0, 4);
				int count = 0;
				for (int k = 0; k < 4; k++) {
					if (str.charAt(k) == '-') {
						count++;
					} else {
						if (str.charAt(k) == check.charAt(k)) {
							count++;
						} else {
							break;
						}
					}
				}

//				System.out.println(key+ " "+hm.get(key)+" "+check + " "+ str+" "+count);

				if (count == 4) {
//					System.out.println(Integer.parseInt(key.substring(4))+" "+score);
					if (Integer.parseInt(key.substring(4)) >= score) {
//						System.out.println("true");
						answer[idx]++;
//						System.out.println(Arrays.toString(answer));
					}
				}

			}
			idx++;

		}
//		System.out.println(Arrays.toString(answer));
		// test

		return answer;
	}
	

	public static void main(String[] args) {
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };

		System.out.println(solution(info, query));
	}


}
