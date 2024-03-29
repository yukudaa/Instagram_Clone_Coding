package com.cos.photogramstart.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDatails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model,@AuthenticationPrincipal PrincipalDatails principalDatails) {
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDatails.getUser().getId());
		model.addAttribute("dto",dto);
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDatails principalDatails) {
		// 1. 추천
		// System.out.println("세션 정보 : "+principalDatails.getUser());
		
		// 2. 극혐
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		PrincipalDatails mPrincipalDatails = (PrincipalDatails) auth.getPrincipal();
		// System.out.println("직접 찾은 세션 정보: " + mPrincipalDatails.getUser()); // 최악
		
		return "user/update";
	}
}
