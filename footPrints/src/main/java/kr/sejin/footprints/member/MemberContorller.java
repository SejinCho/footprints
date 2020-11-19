package kr.sejin.footprints.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberContorller {
	@Autowired
	MemberService service;
	
	//index 페이지
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
	
	//회원인지 확인
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
	
	//이동 기록 페이지
	@RequestMapping("/leaveFootprints.do")
	public ModelAndView leaveFootprints(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String mem_id = (String) req.getSession().getAttribute("mem_id");
		String wk_info_id = service.checkWalkingState(mem_id);
		System.out.println("wk_info_id:"+wk_info_id);
		List<MemberDTO> recordInfo = new ArrayList<MemberDTO>();
		
		//이동 기록 정보 가져오기
		if(wk_info_id != null) {
			recordInfo = service.walkingRecordInfo(wk_info_id);
			mav.addObject("recordInfo", recordInfo);
		}
		
		mav.addObject("mem_id", mem_id);
		mav.addObject("wk_info_id", wk_info_id);
		mav.setViewName("leaveFootprints");
		return mav;
	}
	
	//walkingInfo, walkingRecord 테이블에 insert (기록 시작버튼 클릭 시)
	@RequestMapping(value="/walkingInfoInsert.do",
			method = RequestMethod.GET,
			produces = "application/text;charset=utf-8")
	public @ResponseBody String getWalkerProfile(String mem_id, float currentLatitud, float currentLongitude) {
		System.out.println("int 데이터타입:"+currentLatitud);
		//walkingInfo 테이블에 insert
		int result1 = service.walkingInfoInsert(mem_id);
		String wk_info_id = service.checkWalkingState(mem_id);
		
		//walkingRecord 테이블에 insert
		MemberDTO member = new MemberDTO();
		member.setWk_info_id(wk_info_id);
		member.setWk_latitude(currentLatitud);
		member.setWk_longitude(currentLongitude);
		member.setWk_record_marker(1);
		int result2 = service.walkingRecordInsert(member);
		
		String state = "";
		if(result1 >=1 || result2>=2) {
			state = wk_info_id;
		}
		return state;
	}
	
	//기록 종료 버튼 클릭 시 walkingInfo waking state update& walkingRecord insert
	@RequestMapping(value="/walkingFinish.do",
			method = RequestMethod.GET,
			produces = "application/text;charset=utf-8")
	public @ResponseBody String walkingFinish(String wk_info_id, float currentLatitud, float currentLongitude) {
		MemberDTO member = new MemberDTO();
		int wk_record_marker = 3;
		String retVal = "";
		
		member.setWk_record_marker(wk_record_marker);
		member.setWk_info_id(wk_info_id);
		member.setWk_latitude(currentLatitud);
		member.setWk_longitude(currentLongitude);
		int result = service.walkingFinish(member);
		
		if(result ==1) {
			retVal = "success";
		}
		return retVal;
	}
}
