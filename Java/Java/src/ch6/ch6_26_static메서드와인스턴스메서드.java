package ch6;

/*
 * 인스턴스 메서드
 * - 인스턴스 생성 후, '참조변수.메서드 이름()'으로 호출
 * - 인스턴스 멤버와 관련된 작업을 하는 메서드
 * 메서드 내에서 인스턴스 변수 사용가능
 * 
 * static 메서드(클래스 메서드)
 * - 객체생성없이 '클래스이름.메서드이름()'으로 호출 ex)Math.random()
 * - 인스턴스 멤버와 관련없는 작업을 하는 메서드
 * - 메서드 내에서 인스턴스 변수 사용불가
 * 
 * static을 언제 붙여야 할까?
 * - 속성(멤버 변수) 중 공통 속성에 static을 붙인다.
 * - 인스턴스 멤버를 사용하지 않는 메서드에 static을 붙인다.
 */

public class ch6_26_static메서드와인스턴스메서드 {

}

class TestClass{
	void instanceMethod() {} // 인스턴스 메서드
	static void staticMethod() {} // static 메서드
	
	void instanceMethod2() {
		instanceMethod();
		staticMethod();
	}
	
	static void staticMethod2() {
//		instanceMethod(); // error, 인스턴스메서드를 호출할 수 없다.
		// 왜? 객체가 있을지 없을지 모르기 때문에
		staticMethod(); // ok
	}
}

class TestClass2{
	int iv; // 인스턴스 변수
	static int cv; // 클래스 변수
	
	void instanceMethod() {
		System.out.println(iv); // ok
		System.out.println(cv); // ok
	}
	
	static void staticMethod() {
//		System.out.println(iv); // error, 인스턴스 변수를 사용할 수 없다.
		// 왜? static 메서드는 객체 생성없이 호출가능하는데 iv는 객체 생성 후 호출가능하기 때문
		System.out.println(cv); // ok
	}
}