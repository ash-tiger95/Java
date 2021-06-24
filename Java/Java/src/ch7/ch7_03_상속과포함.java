package ch7;

/*
 * 클래스의 관계: 1. 상속 2. 포함
 * 
 * <포함(composite)이란?>
 * - 클래스의 멤버로 참조변수를 선언하는 것
 * 
 * ex)
 * class Circle {int x, int y, int z}
 * class Point {int x, int y}
 * 
 * class Circle { // 포함 관계
 * 		Point c = new Point(); 	// Point c: 클래스의 멤버로 참조변수(다른 클래스 타입의 변수)를 선언하고
 * 								// new Point(): 타입의 객체를 만들어 주는 것
 * 		int r;
 * }
 * 
 * - 작은 단위의 클래스를 만들고, 이 들을 조합해서 클래스를 만든다.
 * ex)
 * class Car{
 * 		Engine e = new Engine();
 * 		Door[] d = new Door[4];
 * }
 * 
 * <클래스 간의 관계 결정하기>
 * 상속관계: ~은 ~이다. (is-a)
 * 포함관계: ~은 ~을 가지고 있다. (has-a)
 * 
 * ex) 문장을 만들어서 알맞은 방식을 사용한다.
 * Circle은 Point이다.
 * Circle은 Point을 가지고 있다.
 */
public class ch7_03_상속과포함 {

}
