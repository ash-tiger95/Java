package ch7;

public class ch7_10_ex2_생성자super {

}

class Point10 {
	int x, y;

	Point10(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Point3D10 extends Point10 {
	int z;

	// error
	/*
	Point3D10(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	*/
	
	Point3D10(int x, int y, int z) {
		super(x, y); // 조상클래스의 생성자를 호출
		this.z = z; // 자신의 멤버 초기화
	}
}