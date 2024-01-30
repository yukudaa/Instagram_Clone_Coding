package com.cos.photogramstart.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity // DB에 테이블을 생성
@Data
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 전체 생성자
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
	private int id;
	
	@Column(length=100,nullable=false)
	private String content;
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@JoinColumn(name="imageId")
	@ManyToOne(fetch=FetchType.EAGER)
	private Image image;
	
	private LocalDateTime createDate;
	@PrePersist // DB에 Insert가 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
