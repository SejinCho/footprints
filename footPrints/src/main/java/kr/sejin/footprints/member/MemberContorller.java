package kr.sejin.footprints.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberContorller {
	@Autowired
	MemberService service;
	
	@RequestMapping("/index.do")
	public ModelAndView main(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		MemberDTO memberDto = new MemberDTO();
		int result = 0;
		String mem_id = (String) req.getSession().getAttribute("mem_id");
		if(mem_id!=null) {
			result = 1;
			memberDto = service.getMemberInfo(mem_id);
		}
		mav.addObject("memberDto", memberDto);
		mav.addObject("result", result);
		mav.setViewName("index");
		return mav;
		
	}
	
	@RequestMapping("/signUpCheck.do")
	public ModelAndView signUpCheck(int kakao_id, String kakao_nickname, String gender
			, String kakao_profile_image, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		int result = 0;
		String mem_id = service.signUpCheck(kakao_id);
		if(mem_id == null) {
			MemberDTO member = new MemberDTO();
			member.setKakao_id(kakao_id);
			member.setKakao_nickname(kakao_nickname);
			member.setGender(gender);
			member.setKakao_profile_image(kakao_profile_image);
			//신규 회원인 경우 db에 insert
			result = service.signUp(member);
			//mem_id 다시 가져오기
			mem_id = service.signUpCheck(kakao_id);
		}
		//세션에 mem_id 저장
		req.getSession().setAttribute("mem_id", mem_id);
		result = 1 ;
		mav.addObject("result", result);
		mav.setViewName("redirect:/index.do");
		return mav;
	}
}
