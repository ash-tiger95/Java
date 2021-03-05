# Comparable vs Comparator

-> 객체 정렬 기준을 명시하는 2가지 방법이다.



## Comparable (java.lang.Comparable)

### 정의

정렬 수행 시 기본적으로 적용되는 정렬 기준이 되는 메서드를 정의하는 인터페이스

정렬 시, 이에 맞게 정렬이 수행된다.

### 방법

1. 정렬할 객체에 Comparable interface를 implements 후, compareTo() 메서드를 오버라이드

2. compareTo() 메서드

```java
@Override
public int compareTo(Point o) { // this가 나중에 들어온 값
	if (this.x > o.x) {
		return 1; // 양수이면 this가 뒤로(오름차순)
	} else if (this.x < o.x) {
		return -1; // 음수이면 this가 앞으로(오름차순)
	} else {
		if (this.y > o.y) {
			return 1;
		} else if (this.y < o.y) {
			return -1;
		} else {
			return 0;
		}
	}
}
```

3. Arrays.sort(array) 또는 Collections.sort(list)
   1. Arrays.sort(array): 배열 정렬의 경우
   2. Collections.sort(list): List Collection 정렬의 경우



## Comparator (java.util.Comparator)

### 정의

정렬 가능한 클래스(Comparable 인터페이스를 구현한 클래스)들의 기본 정렬 기준과 다르게 정렬하고 싶을 때 사용하는 인터페이스

### 방법

1. Comparator interface를 implements 후 compare() 메서드를 오버라이드
2. compare() 메서드

```java
ts = new TreeSet<String>(new Comparator<String>() {
	@Override
	public int compare(String pre, String next) { // pre가 나중에 들어온 값
		if (pre.length() != next.length()) {
				
			return pre.length() - next.length(); // 양수면 pre가 뒤로(오름차순)
		} else {
				
			return pre.compareTo(next); // 양수면 pre가 뒤로(오름차순)
		}
	}

});
```

