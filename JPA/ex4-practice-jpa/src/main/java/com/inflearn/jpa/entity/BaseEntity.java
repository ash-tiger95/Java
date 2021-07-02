package com.inflearn.jpa.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity extends BaseTimeEntity{

//	@CreatedDate
//	@Column(updatable = false)
//	private LocalDateTime createDate;
//
//	@LastModifiedDate
//	private LocalDateTime lastModifiedDate;

	@CreatedBy
	@Column(updatable = false)
	private String createBy;

	@LastModifiedBy
	private String lastModifiedBy;
}
