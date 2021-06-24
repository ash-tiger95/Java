package ch6;

public class ch6_36_ex {

	String color;
	String gearType;
	int door;

	ch6_36_ex() { // 다른 생성자 호출 시, 첫 줄에서만 가능
		this("white", "auto", 4); // Car2(String color, String gearType, int door) 생성자를 호출한다.
	}

	ch6_36_ex(String color) {
		this(color, "auto", 4);
	}
	
	/*
	Car2(String color){
		door = 5; // error1, 첫줄에 this를 안써서
		Car2(color, "auto", 4); // error2, this를 쓰지 않아서
	}
	 */
	
	ch6_36_ex(String color, String gearType, int door) {
		this.color = color;
		this.gearType = gearType;
		this.door = door;
	}
}
