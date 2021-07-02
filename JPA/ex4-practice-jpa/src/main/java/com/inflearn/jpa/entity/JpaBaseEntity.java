package com.inflearn.jpa.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;

// [JPA 실전] 11. Auditing
@Getter
@MappedSuperclass
public class JpaBaseEntity {
	
	@Column(updatable = false)
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	@PrePersist // Persist 하기 전 이벤트 발생
	public void prePersist() {
		LocalDateTime now = LocalDateTime.now();
		createDate = now;
		updateDate = now;
	}
	
	@PreUpdate
	public void preUpdate() {
		updateDate = LocalDateTime.now();
	}
}
