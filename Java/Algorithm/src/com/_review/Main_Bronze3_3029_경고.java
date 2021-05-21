package com._review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이) 아이디어 암기하자
 * 
 * @author jugia
 *
 */
public class Main_Bronze3_3029_경고 {

	static int[] current, bomb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		current = new int[3];
		bomb = new int[3];

		StringTokenizer st = new StringTokenizer(br.readLine(), ":");
		for (int i = 0; i < 3; i++) {
			current[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), ":");
		for (int i = 0; i < 3; i++) {
			bomb[i] = Integer.parseInt(st.nextToken());
		}

		if (current[0] == bomb[0] && current[1] == bomb[1] && current[2] == bomb[2]) {
			System.out.println("24:00:00");
		} else {
			int hour = bomb[0] - current[0];
			int minute = bomb[1] - current[1];
			int second = bomb[2] - current[2];

			if (second < 0) {
				minute--;
				second += 60;
			}

			if (minute < 0) {
				hour--;
				minute += 60;
			}

			if (hour < 0) {
				hour += 24;
			}

			System.out.printf("%02d:%02d:%02d", hour, minute, second);
		}
	}
}
