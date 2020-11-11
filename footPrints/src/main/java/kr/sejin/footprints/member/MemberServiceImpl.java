package kr.sejin.footprints.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDAO memberDAO;
	
	//회원가입 되어있는지 확인
	@Override
	public String signUpCheck(int kakao_id) {
		return memberDAO.signUpCheck(kakao_id);
	}
	//회원가입
	@Override
	public int signUp(MemberDTO member) {
		return memberDAO.signUp(member);
	}
	//회원정보 가져오기
	@Override
	public MemberDTO getMemberInfo(String mem_id) {
		return memberDAO.getMemberInfo(mem_id);
	}
}
