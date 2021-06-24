package ch7;

/*
 * <오바라이딩이란?>
 * - 상속받은 조상의 메서드를 자신에 맞게 변경하는 것
 * - Override: 덮어쓰다.
 * 
 * <오버라이딩의 조건>
 * 1. 선언부(반환타입, 메서드 이름, 매개변수 목록)가 조상 클래스의 메서드와 일치해야 한다.
 * 2. 접근 제어자를 조상 클래스의 메서드보다 좁은 범위로 변경할 수 없다.
 * 3. 예외는 조상 클래스의 메서드보다 많이 선언할 수 없다.
 * 
 * <오버로딩 vs 오버라이딩>
 * 오버로딩(overloading): 기존에 없는 새로운 메서드를 정의하는 것 (new), 상속과 관계 없다.
 * 오버라이딩(override): 상속받은 메서드의 내용을 변경하는 것 (change, modify)
 * 
 */
public class ch7_07_오버라이딩 {

}

class Parent{
	void parentMethod() {}
}

class Child extends Parent{
	void parentMethod() {} // 오버라이딩
	void parentMethod(int i) {} // 오버로딩
	
	void childMethod() {} // 메서드 정의
	void childMethod(int i) {} // 오버로딩
//	void childMethod() {} // error, 중복 정의
}
