package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 걸려있는 모든 생성자 생성 // final 필드를 DI 할때 사용
@Controller // 1. IoC 2. 파일을 리턴하는 컨트롤러
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final AuthService authService;

//	public AuthController(AuthService authService) {
//		this.authService = authService; // 의존성 주입
//	}

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "/auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "/auth/signup";
	}

	// 예상: 회원가입버튼 -> /auth/siginup -> /auth/signin
	// 결과: 회원가입버튼 -> 아무것도 안됨 (CSRF 토큰이 있기 때문) (정상적인 사용자인지 구분하기 위함)
	@PostMapping("/auth/signup") // 회원가입이 성공하면 로그인 페이지로
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key =
																					// value(x-www-form-urlencoded)
		log.info(signupDto.toString());

		// User <- SignupDto
		User user = signupDto.toEntity();
		log.info(user.toString());

		User userEntity = authService.회원가입(user);
		// System.out.println(userEntity);
		return "/auth/signin";

	}
}
