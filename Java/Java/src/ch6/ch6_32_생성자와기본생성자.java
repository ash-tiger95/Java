package ch6;

/*
 * 생성자
 * - 인스턴스가 생성될 때마다 호출되는 '인스턴스 초기화 메서드'
 * ex) Time t = new Time(); // 참조변수 t
 * t.hour = 12;
 * t.minute = 34;
 * t.second = 56;
 * 
 * - 인스턴스 생성시 수행할 작업(초기화)에 사용
 * ex) Time t = new Time(12,34,56); // 생성자 호출
 * 
 * - 이름이 클래스 이름과 같아야 한다.
 * - 리턴값이 없다. (void 안 붙임)
 * - 모든 클래스는 반드시 생성자를 가져야한다.
 * 
 * 기본생성자
 * - 매개변수가 없는 생성자
 * - 생성자가 하나도 없을 때만 ,컴파일러가 자동 추가
 * ex) Point() {} // 기본 생성자
 */
public class ch6_32_생성자와기본생성자 {

	public static void main(String[] args) {
		Data1 d1 = new Data1();
//		Data2 d2 = new Data2(); // error, 기본 생성자가 없기 때문에
		
		Car c = new Car();
		c.color = "white";
		c.gearType = "auto";
		c.door = 4;
		
		Car c2 = new Car("white","auto",4);
	}
}
