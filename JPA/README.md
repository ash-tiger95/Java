JPA는 자바 컬렉션 같이  객체를 다루는 것



엔티티 매니저 팩토리: 고객의 요청이 올때마다 엔티티 매니저를 생성한다.

엔티티 매니저: 내부적으로 데이터베이스 커넥션을 사용해 DB와 연결한다.



영속성 컨텍스트

Entity.Manager.persist(entity): DB에 저장한다는 말이 아니라, 엔티티를 영속성 컨텍스트에 저장한다는 의미이다.

엔티티 메니저를 통해서 영속성 컨텍스트에 접근한다.

EntityManager를 만들면 1:1로 내부에 PersistenceContext가 생긴다.



엔티티의 생명주기

1. 비영속(new/transient): 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태

   Member member = new Member();

   membe.setId("member1");

   member.setUsername("회원1");

2. 영속

   EntityManager em = emf.createEntityManager();

   em.getTransaction().begin();

   // 객체를 저장한 상태(영속)

   em.persist(member);