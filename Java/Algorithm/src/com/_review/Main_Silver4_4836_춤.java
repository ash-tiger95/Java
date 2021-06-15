package com._review;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main_Silver4_4836_춤 {

	public static void main(String[] args) throws IOException {

		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringBuilder sb = new StringBuilder();

			String[] dance = br.readLine().split(" ");
			int len = dance.length;

			boolean[] rule = new boolean[5];

			// Rule2. 모든 춤은 clap stomp clpa으로 끝나야 한다.
			if (dance[len - 1].equals("clap") && dance[len - 2].equals("stomp") && dance[len - 3].equals("clap")) {
				rule[1] = true;
			}

			// Rule4. jiggle로 춤을 시작할 수 없다.
			if (!dance[0].equals("jiggle")) {
				rule[3] = true;
			}

			rule[0] = true;
			boolean hasTwirl = false;
			boolean hasHop = false;
			boolean hasDip = false;

			ArrayList<Integer> error = new ArrayList<>();

			for (int i = 0; i < len; i++) {
				// Rule1.
				if (rule[0]) {
					if (dance[i].equals("dip")) {
						// Rule5. 반드시 dip을 춰야한다.
						hasDip = true;

						if (i == 0) {
							if (dance[i + 1].equals("twirl")) {
								continue;
							} else {
								rule[0] = false;
							}
						} else if (i == 1) {
							if (dance[i - 1].equals("jiggle") || dance[i + 1].equals("twirl")) {
								continue;
							} else {
								rule[0] = false;
							}
						} else {
							if (dance[i - 1].equals("jiggle") || dance[i - 2].equals("jiggle")
									|| dance[i + 1].equals("twirl")) {
								continue;
							} else {
								rule[0] = false;
							}
						}
					}

				}

				// Rule3. 만약 twirl을 췄다면, hop도 춰야한다.
				if (dance[i].equals("twirl")) {
					hasTwirl = true;
				} else if (dance[i].equals("hop")) {
					hasHop = true;
				}

			}

			if (hasTwirl && hasHop) {
				rule[2] = true;
			} else if (!hasHop && !hasTwirl) {
				rule[2] = true;
			}

			if (hasDip) {
				rule[4] = true;
			}

			int falseCnt = 0;

			for (int i = 0; i < 5; i++) {
				if (!rule[i]) {
					falseCnt++;
				}
			}

			if (falseCnt == 0) {
				sb.append("form ok: ");
				for (String in : dance) {
					sb.append(in).append(" ");
				}
			} else if (falseCnt == 1) {
				sb.append("form error ");
				for (int i = 0; i < 5; i++) {
					if (!rule[i]) {
						sb.append(i + 1).append(": ");
					}
				}

			} else {
				sb.append("form errors ");
				for (int i = 0; i < 5; i++) {
					if (!rule[i]) {
						if(falseCnt == 1) {
							sb.append(i + 1).append(": ");
						} else {
							
							falseCnt--;
							sb.append(i + 1).append(", ");
						}
					}
				}
			}

			bw.write(sb.toString()); // 출력
			bw.flush(); // 남아있는 데이터를 모두 출력시킴

			br.close();
			bw.close();
		}
	}

}
