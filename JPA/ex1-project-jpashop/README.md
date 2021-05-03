# Project Review

: smiley: Tip!

setter를 막 만들면 아무데서나 set을 할 수 있어 추적하기 어렵다. set을 최소화하는게 유지보수 하기 좋다. -> 가급적이면 생성자에서 값을 모두 setting하고 setter사용을 최소화하자.



enum은 @Enumerated해주고 반드시 EnumType은 String으로 해주자 (ORDINAL쓰면 순서가 꼬일 수 있다.)



varchar(255) 에서 255를 메타 데이터라 한다. 