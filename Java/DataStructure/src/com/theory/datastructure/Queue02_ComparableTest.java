package com.theory.datastructure;

import java.util.Arrays;
import java.util.Comparator;

public class Queue02_ComparableTest {
	static class Student implements Comparable<Student>{
		int no, score;

		public Student(int no, int score) {
			super();
			this.no = no;
			this.score = score;
		}

		@Override
		public String toString() {
			return "Student [no=" + no + ", score=" + score + "]";
		}

		@Override
		public int compareTo(Student o) {
			
			return this.no - o.no; // 학번기준 오름차순
		}
		
		
	}
	public static void main(String[] args) {
		Student[] students = new Student[] {
				new Student(1,2),
				new Student(6,3),
				new Student(4,1),
				new Student(3,2),
				new Student(0,2)
		};
		
		Arrays.sort(students);
		for(Student student: students) {
			System.out.println(student);
		}
		System.out.println("============================");
		
		// 생성자의 compareTo를 사용하면 점수기준일 때는 점수로하고 학번 기준일 땐 학번으로하고
		// 계속 고쳐야 하는 불편함이 있음
		// Comparable 무시하고 Comparator이 실행.
		Arrays.sort(students, new Comparator<Student>() {

			@Override
			public int compare(Student o1,Student o2) {
				return o1.score - o2.score;
			}
			
			
		});
		for(Student student: students) {
			System.out.println(student);
		}
	}
}
