package com.inflearn.jpa.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.inflearn.jpa.dto.MemberDto;
import com.inflearn.jpa.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long>, MemberRepoCustom { // <Type, PK로 매핑된 타입>, 인터페이스가 인터페이스를 상속받을 때 extends

	// 1. 메서드 이름으로 쿼리 생성
	List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

	// 2. NamedQuery
	@Query(name = "Member.findByUsername")
	List<Member> findByUsername(@Param("username") String username);

	// 3-1. @Query, Repository 메소드에 쿼리 정의하기, Entity로 조회하기
	@Query("select m from Member m where m.username = :username and m.age = :age")
	List<Member> findUser(@Param("username") String username, @Param("age") int age);

	// 3-2. 값으로 조회하기
	@Query("select m.username from Member m")
	List<String> findUsernameList();

	// 3-2. DTO로 조회하기
	@Query("select new com.inflearn.jpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
	List<MemberDto> findMemberDto();

	// 4. 파라미터 바인딩: 위의 코드에서와 같이 @Param을 사용하는 문법

	// 5. 컬렉션 파라미터 바인딩
	@Query("select m from Member m where m.username in :names")
	List<Member> findByNames(@Param("names") Collection<String> names);

	// 6. 반환 타입
	// 주의! 컬렉션 조회에서 데이터가 없는 경우 null을 반환하는 것이 아니라 빈 컬렉션을 반환해준다.
	List<Member> findListByUsername(String username); // 컬렉션

	Member findMemberByUsername(String username); // 단건

	Optional<Member> findOptionalByUsername(String username); // 단건 Optional

	// [JPA 실전] 6. 페이징과 정렬
//	Page<Member> findByAge(int age, Pageable pageable);

	// [JPA 실전] 6. Slice
	Slice<Member> findByAge(int age, Pageable pageable);

	// [JPA 실전] 6. Total Count 쿼리 최적화
	@Query(value = "select m from Member m left join m.team t", 
			countQuery = "select count(m.username) from Member m")
	Page<Member> findByAge2(int age, Pageable pageable);
	
	// [JPA 실전] 7. 벌크성 수정 쿼리
	@Modifying(clearAutomatically = true) // .executeUpdate()를 실행
	@Query("update Member m set m.age = m.age + 1 where m.age >= :age")
	int bulkAgePlus(@Param("age") int age);
	
	// [JPA 실전] 8. 패치 조인
	@Query("select m from Member m left join fetch m.team")
	List<Member> findMemberFetchJoin();
	
	// [JPA 실전] 8. EntityGraph
	@Override
	@EntityGraph(attributePaths= {"team"})
	List<Member> findAll();
	
	// [JPA 실전] 8. Query에 패치조인을 추가하고 싶을 때, 이러한 방법도 가능
	@EntityGraph(attributePaths= {"team"})
	@Query("select m from Member m")
	List<Member> findMemberEntityGraph();
	
	// [JPA 실전] 8. 메서드 이름으로 쿼리 생성하는 방법에서 패치 조인을 추가하고 싶을 때
	@EntityGraph(attributePaths= {"team"})
	List<Member> findEntityGraphByUsername(@Param("username") String username);

	// [JPA 실전] 8. NamedQuery에서 EntityGraph 추가하는 방법 = @NamedEntityGraph
	@EntityGraph("Member.all")
	List<Member> findNamedQueryByUsername(@Param("username") String username);

	// [JPA 실전] 9. JPA Hint
	@QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
	Member findReadOnlyByUsername(String username);
	
	// [JPA 실전] 9. Lock
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	List<Member> findLockByUsername(String username);
}
