package ch7;

class MyPoint2 extends Object{
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
class Circle2 extends Object{
	MyPoint2 p = new MyPoint2();
	int r;
}

public class ch7_05_ex {

	public static void main(String[] args) {
		Circle2 c=  new Circle2();
		
		// Object클래스의 메서드 사용 가능
		System.out.println(c.toString()); 	// ch7.Circle2@7852e922 (클래스이름)@(객체의 주소값은 아니지만 비슷한 것)
		System.out.println(c); 				// ch7.Circle2@7852e922
	}
}
