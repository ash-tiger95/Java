package ch7;

public class ch7_10_ex1_참조변수super {

	public static void main(String[] args) {
		Child10 c = new Child10();
		c.method();
		
		System.out.println();
		
		Child10_2 c2 = new Child10_2();
		c2.method();
	}
}

class Parent10 {
	int x = 10;
}

class Child10 extends Parent10 {
	int x = 20;

	void method() {
		System.out.println("x= " + x);
		System.out.println("this.x= " + this.x);
		System.out.println("super.x= " + super.x);
	}
}

class Child10_2 extends Parent10 {
	void method() {
		System.out.println("x= " + x);
		System.out.println("this.x= " + this.x); // x가 하나밖에 없으니 모두다 조상 것
		System.out.println("super.x= " + super.x);
	}
}