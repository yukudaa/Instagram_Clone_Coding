package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.subscribe.Subscribe;
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
@Table(
	uniqueConstraints = {
			@UniqueConstraint(
					name="likes_uk",
					columnNames = {"imageId","userId"}
					
					)
	}
) // 유니크
public class Likes { // N

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
	private int id;
	
	@JoinColumn(name="imageId")
	@ManyToOne
	private Image image; // 1
	
	// 오류가 터지고 나서 잡아봅시다.
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne
	private User user; // 1
	
	private LocalDateTime createDate;
	
	@PrePersist // DB에 Insert가 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
