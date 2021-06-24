package ch7;

class MyPoint{
	int x;
	int y;
}

// 방법1. 상속 관계
/*
class Circle extends MyPoint{
	int r;
}
*/

// 방법2. 포함 관계
class Circle{
	MyPoint p = new MyPoint();
	int r;
}

public class ch7_03_ex {

	public static void main(String[] args) {
		Circle c=  new Circle();
		
		// 방법1. 상속 관계
		/*
		c.x = 1;
		c.y = 2;
		c.r = 3;
		System.out.println("x: " + c.x);
		System.out.println("y: " + c.x);
		System.out.println("r: " + c.x);
		*/
		
		// 방법2. 포함 관계
		c.p.x=1;
		c.p.y=2;
		c.r=3;
		System.out.println("x: " + c.p.x);
		System.out.println("y: " + c.p.y);
		System.out.println("r: " + c.r);
		
	}
}
