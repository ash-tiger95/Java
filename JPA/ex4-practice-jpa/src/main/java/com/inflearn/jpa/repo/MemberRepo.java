package com.inflearn.jpa.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inflearn.jpa.dto.MemberDto;
import com.inflearn.jpa.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long>{ // <Type, PK로 매핑된 타입>, 인터페이스가 인터페이스를 상속받을 때 extends
	
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
	
	// 컬렉션 파라미터 바인딩 (위치 기반 사용 x, 무조건 이름 기반)
	@Query("select m from Member m where m.username in :names")
	List<Member> findByNames(@Param("names") Collection<String> names);
	
	// 반환 타입
	List<Member> findListByUsername(String username); // 컬렉션
	// 주의! 컬렉션 조회에서 데이터가 없는 경우 null을 반환하는 것이 아니라 빈 컬렉션을 반환해준다.
	Member findMemberByUsername(String username); // 단건
	Optional<Member> findOptionalByUsername(String username); // 단건 Optional
}
