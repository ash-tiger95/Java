package com.theory.datastructure;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Queue03_PriorityQueue {
	static class Student implements Comparable<Student> {
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
//		PriorityQueue<Student> queue = new PriorityQueue<Student>();
		PriorityQueue<Student> queue = new PriorityQueue<Student>(new Comparator<Student>() {

			@Override
			public int compare(Student o1,Student o2) {
				return o1.no - o2.no;
			}
			
			
		});
		
		queue.offer(new Student(1,2));
		queue.offer(new Student(6,3));
		queue.offer(new Student(4,1));
		queue.offer(new Student(3,2));
		queue.offer(new Student(0,2));
		
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
}
